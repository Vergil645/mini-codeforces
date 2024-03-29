package ru.itmo.wp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        indexes = @Index(columnList = "creationTime"),
        uniqueConstraints = @UniqueConstraint(columnNames = "login")
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 24)
    @Pattern(regexp = "[a-zA-Z]{2,24}")
    private String login;

    private boolean admin;

    @CreationTimestamp
    private Date creationTime;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy("creationTime desc")
    private List<Post> posts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setUser(this);
    }

    public void addComment(Comment comment) {
        comment.setUser(this);
    }
}
