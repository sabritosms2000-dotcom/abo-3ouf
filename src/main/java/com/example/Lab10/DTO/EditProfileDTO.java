

package com.example.Lab10.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EditProfileDTO {
    private @Size(
            min = 3,
            max = 15
    ) @NotBlank String firstName;
    private @Size(
            min = 3,
            max = 15
    ) @NotBlank String lastName;

    public EditProfileDTO() {
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
}
