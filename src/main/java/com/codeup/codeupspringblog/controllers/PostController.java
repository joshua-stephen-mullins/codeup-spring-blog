package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.*;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        model.addAttribute("allPosts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable long id, Model model) {
        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewPostEditById(@PathVariable long id, Model model) {
        if (((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId().equals(postDao.findById(id).get().getPoster().getId())) {
            model.addAttribute("post", postDao.findById(id).get());
            return "/posts/edit";
        }
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPostById(@PathVariable long id, @ModelAttribute Post post) {
        post.setPoster((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String viewPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        post.setPoster((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        postDao.save(post);
//        emailService.prepareAndSend(post, post.getTitle(), "Created New Ad");
        return "redirect:/posts";
    }
}
