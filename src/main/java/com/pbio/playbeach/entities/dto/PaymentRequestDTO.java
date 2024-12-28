package com.pbio.playbeach.entities.dto;

public class PaymentRequestDTO {
    Long categoryId;
    String customerName;
    String customerEmail;

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

}