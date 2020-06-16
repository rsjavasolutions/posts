package com.rsjava.posts.controller;

import com.rsjava.posts.model.Post;
import com.rsjava.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("posts/{id}")
    public Post getAllPosts(@PathVariable Long id){
        return postService.findPostById(id);
    }
}
