package com.Microservice.Customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Microservice.Customer.entities.Customer;
import com.Microservice.Customer.model.LoginInput;
import com.Microservice.Customer.model.LoginResponse;
import com.Microservice.Customer.model.SignupResponse;
import com.Microservice.Customer.requests.CreateCustomerInput;
import com.Microservice.Customer.services.CustomerService;
import com.Microservice.Customer.verifyToken.VerifyHandler;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    public final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SignupResponse> createTask(@RequestBody CreateCustomerInput createTaskInput) {
        
        SignupResponse response = new SignupResponse(customerService.create(createTaskInput.toTask()));
        return new ResponseEntity<SignupResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> allTasks() {
        List<Customer> tasks = customerService.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.MULTI_STATUS);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> oneTask(
        @PathVariable int id,
        @RequestHeader("token") String token
        ) {

        boolean error = false;
        VerifyHandler handler = new VerifyHandler(this.customerService);
        handler.verify(token);

        if(!handler.isSuccess())
            error = true;
        if((!error) || handler.getRole().equals("admin")) {
            Optional<Customer> optionalTask = customerService.findById(id);
            if (optionalTask.isPresent()) {
            return new ResponseEntity<Customer>(optionalTask.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<Customer>(new Customer(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/customer/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginInput email){
        
        String mail = email.ToStringEmail();
        Customer customer = customerService.findByEmail(mail);
        LoginResponse response = new LoginResponse(customer.getPassword()); 
        return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);

    }
}
