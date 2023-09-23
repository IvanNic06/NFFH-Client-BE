package com.Microservice.Customer.requests;

import java.util.Date;

import com.Microservice.Customer.entities.Customer;

public record CreateCustomerInput(String email, String password) {
    public Customer toTask() {
        Customer task = new Customer();

        task.setEmail(email)
            .setPassword(password);

        return task;
    }
}
