package com.arturofilio.codebin.models.requests;

public class UserDetailsRequestModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetailsRequestModel firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public UserDetailsRequestModel lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public UserDetailsRequestModel email(String email) {
        setEmail(email);
        return this;
    }

    public UserDetailsRequestModel password(String password) {
        setPassword(password);
        return this;
    }

    
     
}
