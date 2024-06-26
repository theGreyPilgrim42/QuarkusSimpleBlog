package ch.laengu.control.service;

import java.util.List;

import ch.laengu.control.repository.BlogRepository;
import ch.laengu.entity.Blog;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Dependent
public class BlogService {
    @Inject
    BlogRepository blogRepository;

    public List<Blog> getBlogs() {
        var blogs = blogRepository.listAll();
        Log.info("Returning " + blogs.size() + " blogs");
        return blogs;
    }

    public Blog getBlog(Long id) {
        Blog blog = blogRepository.findById(id);
        Log.info("Returning blog with id " + id);
        return blog;
    }

    @Transactional
    public Blog addBlog(Blog blog) {
        Log.info("Adding blog " + blog.getTitle());
        blogRepository.persist(blog);
        return blog;
    }

    @Transactional
    public boolean deleteBlog(Long id) {
        /**
         * Returns false if the Blog with the id could not be found
         */
        return blogRepository.deleteById(id);
    }
}
