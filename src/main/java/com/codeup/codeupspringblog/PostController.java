package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        model.addAttribute("allPosts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable int id, Model model) {
        Post test = new Post("Italy", "Oh boy... the pasta was tight");
        model.addAttribute("post", test);
        model.addAttribute("id", id);
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewPostForm() {
        return "shows form to create new post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "creates post";
    }

}


//    method	url	description
//        GET	/posts	posts index page
//        GET	/posts/{id}	view an individual post
//        GET	/posts/create	view the form for creating a post
//        POST	/posts/create	create a new post