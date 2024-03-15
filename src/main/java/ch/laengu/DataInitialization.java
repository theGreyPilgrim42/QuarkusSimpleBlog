package ch.laengu;

import ch.laengu.control.AuthorRepository;
import ch.laengu.control.BlogRepository;
import ch.laengu.control.CommentRepository;
import ch.laengu.entity.Author;
import ch.laengu.entity.Blog;
import ch.laengu.entity.Comment;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DataInitialization {

    @Inject
    AuthorRepository authorRepository;

    @Inject
    BlogRepository blogRepository;

    @Inject
    CommentRepository commentRepository;
    
    @Transactional
    public void init(@Observes StartupEvent event) {
        // Add authors
        Author author1 = new Author("John", "Doe");
        Author author2 = new Author("Jane", "Doe");
        authorRepository.persist(author1, author2);

        // Add comments
        Comment comment1 = new Comment("This is a great post. Keep up the good work.");
        Comment comment2 = new Comment("I feel the same way.");
        commentRepository.persist(comment1, comment2);
        comment1.setAuthor(author1);
        comment2.setAuthor(author2);

        // Add blogs
        Blog blog1 = new Blog("This is the first Blog", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut.");
        Blog blog2 = new Blog("This is the second Blog.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut.");
        blogRepository.persist(blog1, blog2);
        blog1.setAuthor(author2);
        blog2.setAuthor(author1);
        blog1.addComment(comment1);
        blog2.addComment(comment2);
        blogRepository.persist(blog1, blog2);
    }
}
