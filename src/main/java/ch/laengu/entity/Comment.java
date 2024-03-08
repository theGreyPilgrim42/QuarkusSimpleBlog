package ch.laengu.entity;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
@Schema(name = "Comment", description = "POJO that represents a blog entry.")
public class Comment {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @Schema(required = true)
    private String text;

    @Schema(readOnly = true)
    private boolean isValid;

    @Schema(required = true)
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    @Schema(readOnly = true)
    private LocalDateTime createdAt;

    @Schema(readOnly = true)
    private LocalDateTime updatedAt;

    // Constructors
    public Comment() {
        this.isValid = false;
    }

    public Comment(String text) {
        this.text = text;
        this.isValid = false;
    }

    // Sets createdAt before the new entity is persisted
    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Sets updatedAt before the entity is updated
    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Methods
    @Override
    public String toString() {
        return "Comment [id=" + id + ", text=" + text + ", isValid=" + isValid + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + "]";
    }
}
