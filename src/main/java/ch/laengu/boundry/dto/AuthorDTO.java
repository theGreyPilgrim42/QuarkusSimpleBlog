package ch.laengu.boundry.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthorDTO {
    // Attributes
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    // Constructors
    public AuthorDTO() {}

    public AuthorDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Methods
    @Override
    public String toString() {
        return "AuthorDTO [firstName=" + firstName + ", lastName=" + lastName + "]";
    }    
}
