package ch.laengu.control.mapper;

import ch.laengu.boundry.dto.AuthorDTO;
import ch.laengu.entity.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public class AuthorMapper {
    @Valid
    public Author toValidAuthor(AuthorDTO authorDto) {
        return new Author(authorDto.getFirstName(), authorDto.getLastName());
    }

    public AuthorDTO fromAuthor(Author author) {
        return new AuthorDTO(author.getFirstName(), author.getLastName());
    }
}
