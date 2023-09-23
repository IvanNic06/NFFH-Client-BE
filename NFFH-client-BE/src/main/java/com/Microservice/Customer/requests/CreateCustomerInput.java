package com.tericcabrel.taskman.requests;

import com.tericcabrel.taskman.entities.Customer;

import java.util.Date;

public record CreateCustomerInput(String email, String password) {
    public Customer toTask() {
        Customer task = new Customer();

        task.setEmail(email)
            .setPassword(password);

        return task;
    }
}
