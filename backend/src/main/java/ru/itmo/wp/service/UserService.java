package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.RegisterForm;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public User findById(Long id) {
        return id == null ? null : userRepository.findById(id).orElse(null);
    }

    public User findByLogin(String login) {
        return login == null ? null : userRepository.findByLogin(login).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByIdDesc();
    }

    public void writePost(User user, Post post) {
        user.addPost(post);
        userRepository.save(user);
    }

    public void register(RegisterForm registerForm) {
        User user = new User();
        user.setLogin(registerForm.getLogin().trim());
        user.setName(registerForm.getName().trim());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), registerForm.getLogin(), registerForm.getPassword());
    }
}
