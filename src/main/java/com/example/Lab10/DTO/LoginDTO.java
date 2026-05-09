
package com.example.Lab10.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDTO {
    private @Size(
            min = 3,
            max = 15
    ) @NotBlank String firstName;
    private @Size(
            min = 3,
            max = 15
    ) @NotBlank String lastName;
    private @Email @NotBlank String email;
    private @Size(
            min = 5,
            max = 15
    ) @NotBlank String password;

    public LoginDTO() {
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
