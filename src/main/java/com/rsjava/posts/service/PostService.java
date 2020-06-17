package com.rsjava.posts.service;

import com.rsjava.posts.model.Post;
import com.rsjava.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;
    private static final int PAGE_SIZE = 10;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts(int pageNumber) {
        if (pageNumber < 0 ){
            pageNumber = 0;
        }
        return postRepository.findAllPosts(PageRequest.of(pageNumber,PAGE_SIZE));
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post findPostById(Long id){
        return postRepository.findById(id)
                .orElseThrow();
    }
}
