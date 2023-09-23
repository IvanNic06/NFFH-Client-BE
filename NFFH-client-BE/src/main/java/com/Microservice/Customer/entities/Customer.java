package com.tericcabrel.taskman.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Table(name = "customers")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(name="email",unique = true, length = 200, nullable = false)
    private String Email;
    
    @Column(name = "password")
    private String Password;

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public Customer setId(Integer id) {
        this.id = id;
        return this;
    }

    public Customer setEmail(String name) {
        this.Email = name;
        return this;
    }

    public Customer setPassword(String description) {
        this.Password = description;
        return this;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
