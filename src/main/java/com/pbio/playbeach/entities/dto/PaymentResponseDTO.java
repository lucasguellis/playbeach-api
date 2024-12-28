package com.pbio.playbeach.entities.dto;

import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;

public record PaymentResponseDTO(String paymentIntentId, String ephemeralKey, String customerId) {
    public PaymentResponseDTO(PaymentIntent paymentIntent, String ephemeralKey, Customer customer) {
        this(paymentIntent.getClientSecret(), ephemeralKey, customer.getId());
    }
}
