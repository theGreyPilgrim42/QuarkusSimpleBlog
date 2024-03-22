package ch.laengu.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
@Schema(name = "Comment", description = "POJO that represents a blog entry.")
public class Comment extends BaseEntity {
    // Attributes
    @Schema(required = true)
    @Size(max = 250)
    private String text;

    @Schema(readOnly = true)
    private boolean isValid;

    @Schema(required = true)
    @ManyToOne
    private Author author;

    // Constructors
    public Comment() {
        this.isValid = false;
    }

    public Comment(String text) {
        this.text = text;
        this.isValid = false;
    }

    // Getters and setters
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

    // Methods
    @Override
    public String toString() {
        return "Comment [id=" + getId() + ", text=" + text + ", isValid=" + isValid + ", createdAt=" + getCreatedAt()
                + ", updatedAt=" + getUpdatedAt() + "]";
    }
}
