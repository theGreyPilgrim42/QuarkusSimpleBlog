package ch.laengu.boundry.resource;

import java.util.List;

import ch.laengu.control.service.AuthorService;
import ch.laengu.entity.Author;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/author")
public class AuthorResource {
    
    @Inject
    AuthorService authorService;

    @GET
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @GET
    @Path("{id}")
    public Author getAuthor(Long id) {
        return authorService.getAuthor(id);
    }

    @POST
    public List<Author> addAuthor(Author author) {
        authorService.addAuthor(author);
        return authorService.getAuthors();
    }

    @DELETE
    @Path("{id}")
    public void deleteAuthor(Long id) {
        authorService.deleteAuthor(id);
    }
}
