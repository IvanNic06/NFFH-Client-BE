package com.Microservice.Customer.model;

import com.Microservice.Customer.entities.Customer;

public class SignupResponse {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public SignupResponse(Customer customer) {
        this.success = (customer != null);
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
