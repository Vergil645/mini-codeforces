package ru.itmo.wp.form;

import javax.validation.constraints.*;

public class UserCredentials {
    @NotNull
    @NotBlank
    @Size(min = 2, max = 24)
    @Pattern(regexp = "[a-zA-Z]{2,24}", message = "Expected Latin letters")
    private String login;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 60)
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
