package com.tericcabrel.taskman.services;

import com.tericcabrel.taskman.entities.Customer;
import com.tericcabrel.taskman.repositories.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository taskRepository) {
        this.customerRepository = taskRepository;
    }

    public Customer create(Customer task) {
        return customerRepository.save(task);
    }

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);

        return customers;
    }

    public Optional<Customer> findById(int id) {
        return customerRepository.findById(id);
    }

    public Customer update(Customer taskToUpdate) {
        return customerRepository.save(taskToUpdate);
    }

    public void delete(int id) {
        customerRepository.deleteById(id);
    }
}
