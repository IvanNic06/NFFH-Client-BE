package com.tericcabrel.taskman.controllers;

import com.tericcabrel.taskman.entities.Customer;
import com.tericcabrel.taskman.requests.CreateCustomerInput;
import com.tericcabrel.taskman.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    public final CustomerService taskService;

    public CustomerController(CustomerService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createTask(@RequestBody CreateCustomerInput createTaskInput) {
        Customer taskCreated = taskService.create(createTaskInput.toTask());

        return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> allTasks() {
        List<Customer> tasks = taskService.findAll();

        return new ResponseEntity<>(tasks, HttpStatus.OK);
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
