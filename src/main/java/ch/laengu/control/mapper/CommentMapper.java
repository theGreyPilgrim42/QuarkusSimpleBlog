package ch.laengu.control.mapper;

import ch.laengu.boundry.dto.CommentDTO;
import ch.laengu.entity.Comment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public class CommentMapper {
    @Valid
    public Comment toValidComment(CommentDTO commentDto) {
        return new Comment(commentDto.getText());
    }

    public CommentDTO fromComment(Comment comment) {
        return new CommentDTO(comment.getText());
    }
}
