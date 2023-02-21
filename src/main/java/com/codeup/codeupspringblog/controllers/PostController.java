package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.*;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
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
        Post post = postDao.getById(id);
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewPostEditById(@PathVariable long id, Model model, @ModelAttribute Post post) {
        Post originalPost = postDao.getById(id);
        model.addAttribute("post", post);
        model.addAttribute("originalPost", originalPost);
        return "/posts/edit";
    }

    @PostMapping("/posts/edit")
    public String editPostById(@RequestParam(name="id") int id, @ModelAttribute Post post) {
        post.setId(id);
        postDao.save(post);
        return "/posts/show";
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
