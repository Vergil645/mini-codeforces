package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.RegisterForm;
import ru.itmo.wp.form.validator.RegisterFormValidator;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    private final RegisterFormValidator registerFormValidator;

    public UserController(UserService userService, JwtService jwtService,
                          RegisterFormValidator registerFormValidator) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.registerFormValidator = registerFormValidator;
    }

    @InitBinder("registerForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(registerFormValidator);
    }

    @GetMapping("users/auth")
    public User findUserByJwt(@RequestParam String jwt) {
        return jwtService.find(jwt);
    }

    @GetMapping("users")
    public List<User> findUsers() {
        return userService.findAll();
    }

    @PostMapping("users")
    public void register(@RequestBody @Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        userService.register(registerForm);
    }
}
