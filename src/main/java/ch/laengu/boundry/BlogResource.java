package ch.laengu.boundry;

import java.util.List;

import ch.laengu.control.BlogService;
import ch.laengu.entity.Blog;
import jakarta.inject.Inject;
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
        this.blogService.addBlog(blog);
    }

    @DELETE
    public Response deleteBlogById(Long id) {
        if (!this.blogService.removeBlog(id)) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).build();
    }
}
