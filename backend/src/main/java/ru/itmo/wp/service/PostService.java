package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.PostCreationForm;
import ru.itmo.wp.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;

    public PostService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post findById(Long id) {
        return id == null ? null : postRepository.findById(id).orElse(null);
    }

    public void create(PostCreationForm postCreationForm) {
        Post post = new Post();
        post.setTitle(postCreationForm.getTitle().trim());
        post.setText(postCreationForm.getText().trim());
        User user = userService.findById(postCreationForm.getUserId());
        userService.writePost(user, post);
    }
}
