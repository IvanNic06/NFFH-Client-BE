package com.Microservice.Customer.model;

public class LoginResponse {
    
    private boolean success;
    private String password;
    

    
    public LoginResponse(boolean success, String password) {
        this.success = success;
        this.password = password;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String isPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "LoginResponse [success=" + success + ", password=" + password + "]";
    }
    
        
    

    

    

}
