package ch.laengu.boundry.resource;

import java.net.URI;
import java.util.List;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import ch.laengu.boundry.dto.BlogDTO;
import ch.laengu.control.mapper.BlogMapper;
import ch.laengu.control.service.AuthorService;
import ch.laengu.control.service.BlogService;
import ch.laengu.entity.Author;
import ch.laengu.entity.Blog;
import ch.laengu.entity.Message;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Response.Status;

@Path("/blog")
public class BlogResource {
    
    @Inject
    AuthorService authorService;

    @Inject
    BlogMapper blogMapper;

    @Inject
    BlogService blogService;

    @Channel("new-blog")
    Emitter<Message> emitter;

    @GET
    public List<Blog> getBlogs() {
        return blogService.getBlogs();
    }

    @Path("{id}")
    @GET
    public Blog getBlogById(@PathParam("id") Long id) {
        return blogService.getBlog(id);
    }

    @Path("{authorId}")
    @POST
    public Response addBlog(@PathParam("authorId") Long authorId, BlogDTO blogDto, @Context UriInfo uriInfo) {
        Log.debug("Trying to add a new blog");
        Author author = authorService.getAuthor(authorId);
        Blog blog = blogMapper.toValidBlog(blogDto);
        blog.setAuthor(author);
        Blog newBlog = blogService.addBlog(blog);
        emitter.send(new Message(newBlog.getId(), newBlog.getContent()));
        return Response.created(URI.create("/blog/" + Long.toString(newBlog.getId()))).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteBlogById(Long id) {
        if (!blogService.deleteBlog(id)) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).build();
    }

    @Incoming("validated-blog")
    @Transactional
    public void sink(Message message) {
        Log.debug("Received on topic validated-blog: " + message.toString());
        Long id = message.getId();
        if (id == null) {
            Log.warn("Message ID is null");
            return;
        }
        Blog blog = blogService.getBlog(id);

        // Guard clause
        if (blog == null) {
            return;
        }

        blog.setValid(message.isValid());
    }
}
