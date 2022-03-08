package ru.itmo.wp.form;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostCreationForm {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 10000)
    @Lob
    private String text;

    private long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
