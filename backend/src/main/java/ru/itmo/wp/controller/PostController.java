package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.PostCreationForm;
import ru.itmo.wp.form.validator.PostCreationFormValidator;
import ru.itmo.wp.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class PostController {
    private final PostService postService;

    private final PostCreationFormValidator postCreationFormValidator;

    public PostController(PostService postService, PostCreationFormValidator postCreationFormValidator) {
        this.postService = postService;
        this.postCreationFormValidator = postCreationFormValidator;
    }

    @InitBinder("postCreationForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(postCreationFormValidator);
    }

    @GetMapping("posts")
    public List<Post> findPosts() {
        return postService.findAll();
    }

    @PostMapping("posts")
    public void writePost(@RequestBody @Valid PostCreationForm postCreationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        postService.create(postCreationForm);
    }
}
