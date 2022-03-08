package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.PostCreationForm;
import ru.itmo.wp.service.UserService;

@Component
public class PostCreationFormValidator implements Validator {
    private final UserService userService;

    public PostCreationFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PostCreationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PostCreationForm postCreationForm = (PostCreationForm) target;
            if (userService.findById(postCreationForm.getUserId()) == null) {
                errors.reject("invalid-userId", "No such user");
            }
        }
    }
}
