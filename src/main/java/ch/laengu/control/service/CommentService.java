package ch.laengu.control.service;

import java.util.List;

import ch.laengu.control.repository.BlogRepository;
import ch.laengu.control.repository.CommentRepository;
import ch.laengu.entity.Blog;
import ch.laengu.entity.Comment;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Dependent
public class CommentService {
    // TODO: Add method to delete and update Comment

    @Inject
    BlogRepository blogRepository;

    @Inject
    CommentRepository commentRepository;

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getCommentsByBlog(Long id) {
        Blog blog = blogRepository.findById(id);
        return blog.getComments();
    }

    @Transactional
    public void addCommentToBlog(Long id, Comment comment) {
        Blog blog = blogRepository.findById(id);
        commentRepository.persist(comment);
        blog.addComment(comment);
        blogRepository.persist(blog);
    }
}
