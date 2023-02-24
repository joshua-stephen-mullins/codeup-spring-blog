package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import com.codeup.codeupspringblog.services.PostDaoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class PostController {

    private final PostDaoService postService;
    private final EmailService emailService;

    public PostController(PostDaoService postService, EmailService emailService) {
        this.postService = postService;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        model.addAttribute("allPosts", postService.getAllPosts());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable long id, Model model) {
        model.addAttribute("post", postService.findPostById(id));
        return "/posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewPostEditById(@PathVariable long id, Model model) {
        if (((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId().equals(postService.findPostById(id).getPoster().getId())) {
            model.addAttribute("post", postService.findPostById(id));
            return "/posts/edit";
        }
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPostById(@PathVariable long id, @ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/posts/create")
    public String viewPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        postService.savePost(post);
        emailService.sendTextEmail(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postService.deletePostById(id);
        return "redirect:/posts";
    }

}
