package com.Microservice.Customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Microservice.Customer.entities.Customer;
import com.Microservice.Customer.model.SignupResponse;
import com.Microservice.Customer.requests.CreateCustomerInput;
import com.Microservice.Customer.services.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    public final CustomerService taskService;

    public CustomerController(CustomerService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(path = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SignupResponse> createTask(@RequestBody CreateCustomerInput createTaskInput) {
        
        SignupResponse response = new SignupResponse(taskService.create(createTaskInput.toTask()));

        return new ResponseEntity<SignupResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> allTasks() {
        List<Customer> tasks = taskService.findAll();

        return new ResponseEntity<>(tasks, HttpStatus.MULTI_STATUS);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> oneTask(@PathVariable int id) {
        Optional<Customer> optionalTask = taskService.findById(id);

        if (optionalTask.isPresent()) {
            return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
