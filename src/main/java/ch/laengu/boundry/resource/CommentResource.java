package ch.laengu.boundry.resource;

import java.util.List;

import ch.laengu.boundry.dto.CommentDTO;
import ch.laengu.control.mapper.CommentMapper;
import ch.laengu.control.service.AuthorService;
import ch.laengu.control.service.BlogService;
import ch.laengu.control.service.CommentService;
import ch.laengu.entity.Author;
import ch.laengu.entity.Blog;
import ch.laengu.entity.Comment;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/blog/{id}/comment")
public class CommentResource {
    // TODO: Add method to delete and update a comment

    @Inject
    AuthorService authorService;

    @Inject
    BlogService blogService;
    
    @Inject
    CommentMapper commentMapper;

    @Inject
    CommentService commentService;

    @GET
    public List<Comment> getComments(@PathParam("id") Long id) {
        return commentService.getCommentsByBlog(id);
    }

    @Path("{commentId}")
    @GET
    public Comment getComment(@PathParam("id") Long id, @PathParam("commentId") Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @Path("{authorId}")
    @POST
    public List<Comment> addComment(CommentDTO commentDto, @PathParam("id") Long id, @PathParam("authorId") Long authorId) {
        Author author = authorService.getAuthor(authorId);
        Comment comment = commentMapper.toValidComment(commentDto);
        comment.setAuthor(author);
        commentService.addCommentToBlog(id, comment);
        Blog blog = blogService.getBlog(id);
        return blog.getComments();
    }
}
