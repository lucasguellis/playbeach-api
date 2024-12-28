package com.pbio.playbeach.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.EphemeralKey;
import com.stripe.model.PaymentIntent;
import com.stripe.net.RequestOptions;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    public PaymentIntent createPaymentIntent(Integer amount, Customer customer, Long categoryId) throws StripeException {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount((long) amount)
                        .setCurrency("brl")
                        .setCustomer(customer.getId())
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .putMetadata("categoryId", categoryId.toString())
                        .build();

        return PaymentIntent.create(params);
    }

    public String getEphemeralKey (Customer customer) throws StripeException {
        RequestOptions requestOptions = (new RequestOptions.RequestOptionsBuilder())
                .build();
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("customer", customer.getId());
//        options.put("stripe-version", requestOptions.getStripeVersion());
        options.put("stripe-version", "2024-11-20.acacia");
        EphemeralKey key = EphemeralKey.create(options, requestOptions);
        return key.getId();
    }

    public boolean isPaymentValid(String paymentIntentId) {
        return true;
    }
}
