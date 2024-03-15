package ch.laengu.boundry.resource;

import java.util.List;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import ch.laengu.control.service.BlogService;
import ch.laengu.entity.Blog;
import ch.laengu.entity.TextMessage;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/blog")
public class BlogResource {
    
    @Inject
    BlogService blogService;

    @Channel("new-blog")
    Emitter<TextMessage> emitter;

    @GET
    public List<Blog> getBlogs() {
        return blogService.getBlogs();
    }

    @Path("{id}")
    @GET
    public Blog getBlogById(Long id) {
        return this.blogService.getBlog(id);
    }

    @POST
    public void addBlog(@Valid Blog blog) {
        Blog newBlog = this.blogService.addBlog(blog);
        emitter.send(new TextMessage(newBlog.getId(), newBlog.getContent()));
    }

    @DELETE
    @Path("{id}")
    public Response deleteBlogById(Long id) {
        if (!this.blogService.deleteBlog(id)) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).build();
    }

    @Incoming("validated-text")
    @Transactional
    public void sink(TextMessage textMessage) {
        Log.info("Received on topic validated-text: " + textMessage.toString());
        Long id = textMessage.getId();
        if (id == null) {
            Log.warn("Text Message ID is null");
            return;
        }
        Blog blog = this.blogService.getBlog(id);

        // Guard clause
        if (blog == null) {
            return;
        }

        blog.setValid(textMessage.isValid());
    }
}
