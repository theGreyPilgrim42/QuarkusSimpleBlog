package ch.laengu.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.Entity;

@Entity
@Schema(name = "Author", description = "POJO that represents an author.")
public class Author extends BaseEntity {
    // Attributes
    private String firstName;
    private String lastName;

    // Constructors
    public Author() {}

    public Author(String firstName, String lastName) {
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
        return "Author [id=" + getId() + ", firstName=" + firstName + ", lastName=" + lastName + ", createdAt=" + getCreatedAt()
                + ", updatedAt=" + getUpdatedAt() + "]";
    }
}
