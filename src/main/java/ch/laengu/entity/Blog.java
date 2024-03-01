package ch.laengu.entity;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
@Schema(name = "Blog", description = "POJO that represents a blog entry.")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id; // Use Long Wrapper for null

    @Schema(required = true, example = "New Blog")
    private String title;

    @Schema(required = true, example = "This is my first blog post.")
    private String content;

    @Schema(readOnly = true)
    private boolean isValid;

    @Schema(readOnly = true)
    private LocalDateTime createdAt;

    @Schema(readOnly = true)
    private LocalDateTime updatedAt;

    public Blog() {
        this.isValid = false;
    }

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isValid() {
        return isValid;
    }

    @JsonIgnore
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", isValid=" + isValid + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}
