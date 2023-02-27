package com.codeup.codeupspringblog.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Posts must have a title")
    @Size(min = 3, message = "A title must be at least 3 characters.")
    @Column(nullable = false, length = 100)
    private String title;

    @NotBlank(message = "Posts must have a body")
    @Size(min = 10, message = "A title must be at least 10 characters.")
    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn (name = "poster_id")
    private User poster;

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.poster = user;
    }

    public Post() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }
}
