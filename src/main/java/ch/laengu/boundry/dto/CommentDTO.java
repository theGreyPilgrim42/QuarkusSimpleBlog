package ch.laengu.boundry.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentDTO {
    // Attributes
    @NotBlank
    private String text;

    // Constructors
    public CommentDTO() {}

    public CommentDTO(String text) {
        this.text = text;
    }

    // Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Methods
    @Override
    public String toString() {
        return "CommentDTO [text=" + text + "]";
    }
}
