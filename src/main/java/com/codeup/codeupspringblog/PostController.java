package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String getAllPosts(){
        return "retrieves all blog posts";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPostById(@PathVariable int id){
        return "retrieves single blog post by the path variable passed: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewPostForm(){
        return "shows form to create new post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "creates post";
    }

}


//    method	url	description
//        GET	/posts	posts index page
//        GET	/posts/{id}	view an individual post
//        GET	/posts/create	view the form for creating a post
//        POST	/posts/create	create a new post