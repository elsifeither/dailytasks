package com.example.dailytasks.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class ContactDTO {

    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @NotEmpty
    @Email
    @Size(min = 2, max = 25)
    private String email;

    @NotEmpty
    @Size(min = 1, max = 1000)
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
