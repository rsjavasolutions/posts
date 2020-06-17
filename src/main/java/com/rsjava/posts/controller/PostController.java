package com.rsjava.posts.controller;

import com.rsjava.posts.model.Post;
import com.rsjava.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("posts")
    public List<Post> getAllPosts(@RequestParam (required = false) int page){
        return postService.getAllPosts(page);
    }

    @GetMapping("posts/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.findPostById(id);
    }
}
