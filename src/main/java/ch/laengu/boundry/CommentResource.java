package ch.laengu.boundry;

import java.util.List;

import ch.laengu.control.BlogService;
import ch.laengu.control.CommentService;
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
    BlogService blogService;
    
    @Inject
    CommentService commentService;

    @GET
    public List<Comment> getComments(@PathParam("id") Long id) {
        return commentService.getCommentsByBlog(id);
    }

    @POST
    public List<Comment> addComment(@PathParam("id") Long id, Comment comment) {
        commentService.addCommentToBlog(id, comment);
        Blog blog = blogService.getBlog(id);
        return blog.getComments();
    }
}
