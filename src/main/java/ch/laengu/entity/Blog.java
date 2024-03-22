package ch.laengu.entity;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
@Schema(name = "Blog", description = "POJO that represents a blog entry.")
public class Blog extends BaseEntity {
    // Attributes
    @Schema(required = true, example = "New Blog")
    private String title;

    @Schema(required = true, example = "This is my first blog post.")
    @Size(max = 11_475) // Based on 2250 words with an average of 5.1 letters per word
    @Column(length = 11_475)
    private String content;

    @Schema(readOnly = true)
    private boolean isValid;

    @OneToMany
    @JoinColumn(name = "blog_id")
    private List<Comment> comments = new ArrayList<>();

    @Schema(required = true)
    @ManyToOne
    private Author author;

    // Constructors
    public Blog() {
        this.isValid = false;
    }

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
        this.isValid = false;
    }

    // Getters and Setters
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

    public List<Comment> getComments() {
        return this.comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
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
        return "Blog [id=" + getId() + ", title=" + title + ", content=" + content + ", isValid=" + isValid + ", createdAt="
                + getCreatedAt() + ", updatedAt=" + getUpdatedAt() + "]";
    }
}
