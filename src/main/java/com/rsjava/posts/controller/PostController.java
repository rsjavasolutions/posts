package com.rsjava.posts.controller;

import com.rsjava.posts.dto.PostDto;
import com.rsjava.posts.dto.mapper.PostMapper;
import com.rsjava.posts.model.Post;
import com.rsjava.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
    public List<PostDto> getAllPosts(@RequestParam (required = false) Integer page, Sort.Direction sort){
        return postMapper.mapToListPostDto(postService.getAllPosts(page, sort));
    }

    @GetMapping("posts/comments")
    public List<Post> getAllPostsWithComments(@RequestParam (required = false) Integer page, Sort.Direction sort){
        return postService.getAllPostsWithComments(page, sort);
    }

    @GetMapping("posts/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.findPostById(id);
    }

    @PostMapping("posts/")
    public Post addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id){
        return postService.deletePostById(id);
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post){
        return postService.updatePostById(id, post);
    }
}
