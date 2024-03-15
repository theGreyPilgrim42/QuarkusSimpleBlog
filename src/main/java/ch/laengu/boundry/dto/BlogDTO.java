package ch.laengu.boundry.dto;

import jakarta.validation.constraints.NotBlank;

public class BlogDTO {
    // Attributes
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    // Constructors
    public BlogDTO() {}

    public BlogDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters and setters
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

    // Methods
    @Override
    public String toString() {
        return "BlogDTO [title=" + title + ", content=" + content + "]";
    }    
}
