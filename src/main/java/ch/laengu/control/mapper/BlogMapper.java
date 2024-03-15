package ch.laengu.control.mapper;

import ch.laengu.boundry.dto.BlogDTO;
import ch.laengu.entity.Blog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public class BlogMapper {
    @Valid
    public Blog toValidBlog(BlogDTO blogDto) {
        return new Blog(blogDto.getTitle(), blogDto.getContent());
    }

    public BlogDTO fromBlog(Blog blog) {
        return new BlogDTO(blog.getTitle(), blog.getContent());
    }
}
