package com.pbio.playbeach.controllers;

import com.google.gson.JsonSyntaxException;
import com.pbio.playbeach.entities.Category;
import com.pbio.playbeach.entities.dto.PaymentRequestDTO;
import com.pbio.playbeach.entities.dto.PaymentResponseDTO;
import com.pbio.playbeach.repositories.TmpUserCategoryRepository;
import com.pbio.playbeach.services.CategoryService;
import com.pbio.playbeach.services.PaymentService;
import com.pbio.playbeach.util.CustomerUtil;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/checkout")
public class PaymentController {
    private final TmpUserCategoryRepository tmpUserCategoryRepository;

    @Value("${stripe.publishable-key}")
    private String PUBLISHABLE_KEY;

    @Value("${stripe.secret-key}")
    private String SECRET_KEY;

    @Value("${stripe.webhook-secret}")
    private String WEBHOOK_SECRET;

    private final CategoryService categoryService;
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(CategoryService categoryService, PaymentService paymentService, TmpUserCategoryRepository tmpUserCategoryRepository) {
        this.categoryService = categoryService;
        this.paymentService = paymentService;
        this.tmpUserCategoryRepository = tmpUserCategoryRepository;
    }

    @PostMapping("/")
    PaymentResponseDTO integratedCheckout(@RequestBody PaymentRequestDTO paymentRequestDTO) throws Exception {

        Stripe.apiKey = SECRET_KEY;

        // Start by finding existing customer or creating a new one if needed
        Customer customer = CustomerUtil.findOrCreateCustomer(paymentRequestDTO.getCustomerEmail(), paymentRequestDTO.getCustomerName());

        Category category = categoryService.getCategoryById(
                paymentRequestDTO.getCategoryId()
        );

        if (category == null) {
            throw new Exception("Category doesn't exist");
        }

        // Create a PaymentIntent and send its client secret to the client
        PaymentIntent paymentIntent = paymentService.createPaymentIntent(category.getPrice(), customer, category.getId());

        String ephemeralKey = paymentService.getEphemeralKey(customer);

        // Send the client secret from the payment intent to the client
        return new PaymentResponseDTO(paymentIntent, ephemeralKey, customer);
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(HttpServletRequest request) {
        String payload;
        try {
            payload = new BufferedReader(request.getReader()).lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            return ResponseEntity.status(400).body("Invalid payload");
        }

        String sigHeader = request.getHeader("Stripe-Signature");
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, WEBHOOK_SECRET);
        } catch (JsonSyntaxException e) {
            return ResponseEntity.status(400).body("Invalid payload");
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(400).body("Invalid signature");
        }

        StripeObject stripeObject = event
            .getDataObjectDeserializer()
            .getObject()
            .orElse(null);

        switch (stripeObject) {
            case null -> {
                return ResponseEntity.status(400).body("Invalid event object");
            }
            case PaymentIntent intent -> handlePaymentIntent(event, intent);
            case Charge charge -> handleCharge(event, charge);
            default -> {
                return ResponseEntity.status(400).body("Unhandled event object type");
            }
        }

        return ResponseEntity.ok("OK");
    }

    private void handlePaymentIntent(Event event, PaymentIntent intent) {
        switch (event.getType()) {
            case "payment_intent.succeeded":
                System.out.println("PaymentIntent succeeded: " + intent.getId());
                categoryService.subscribeUser("l@gmail.com", Long.valueOf(intent.getMetadata().get("categoryId")));
                break;

            case "payment_intent.payment_failed":
                System.out.println("PaymentIntent failed: " + intent.getId());
                break;

            default:
                System.out.println("Unhandled PaymentIntent event: " + event.getType());
                break;
        }
    }

    private void handleCharge(Event event, Charge charge) {
        switch (event.getType()) {
            case "charge.succeeded":
                System.out.println("Charge succeeded: " + charge.getId());
                categoryService.subscribeUser("l@gmail.com", Long.valueOf(charge.getMetadata().get("categoryId")));
                break;

            case "charge.failed":
                System.out.println("Charge failed: " + charge.getId());
                break;

            default:
                System.out.println("Unhandled Charge event: " + event.getType());
                break;
        }
    }

}
