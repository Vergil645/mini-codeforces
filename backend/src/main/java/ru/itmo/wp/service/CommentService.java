package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final PostService postService;

    public CommentService(PostService postService) {
        this.postService = postService;
    }

    public List<Comment> findAllByPostId(Long postId) {
        Post post = postService.findById(postId);
        if (post != null) {
            return post.getComments();
        } else {
            return new ArrayList<>();
        }
    }
}
