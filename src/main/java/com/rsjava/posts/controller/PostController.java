package com.rsjava.posts.controller;

import com.rsjava.posts.dto.PostDto;
import com.rsjava.posts.dto.mapper.PostMapper;
import com.rsjava.posts.model.Post;
import com.rsjava.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    private PostService postService;
    private PostMapper postMapper;

    @Autowired
    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping("posts")
    public List<PostDto> getAllPosts(@RequestParam (required = false) int page){
        return postMapper.mapToListPostDto(postService.getAllPosts(page));
    }

    @GetMapping("posts/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.findPostById(id);
    }
}
