
package com.example.Lab10.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterDTO {
    private @Size(
            min = 3,
            max = 15
    ) @NotBlank String firstName;
    private @Size(
            min = 3,
            max = 15
    ) @NotBlank String lastName;
    private @NotBlank @Email String email;
    private @Size(
            min = 5,
            max = 15
    ) @NotBlank String password;

    public RegisterDTO() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }
}
