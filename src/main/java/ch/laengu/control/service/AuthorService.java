package ch.laengu.control.service;

import java.util.List;

import ch.laengu.control.repository.AuthorRepository;
import ch.laengu.entity.Author;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Dependent
public class AuthorService {
    
    @Inject
    AuthorRepository authorRepository;

    public Author getAuthor(Long id) {
        Author author = authorRepository.findById(id);
        return author;
    }

    public List<Author> getAuthors() {
        return authorRepository.listAll();
    }

    @Transactional
    public Author addAuthor(Author author) {
        authorRepository.persist(author);
        return author;
    }

    @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
