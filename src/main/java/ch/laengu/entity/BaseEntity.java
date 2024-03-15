package ch.laengu.entity;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public class BaseEntity {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id; // Use Long Wrapper for null

    @Schema(readOnly = true)
    private LocalDateTime createdAt;

    @Schema(readOnly = true)
    private LocalDateTime updatedAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
