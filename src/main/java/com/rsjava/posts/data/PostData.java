package com.rsjava.posts.data;

import com.rsjava.posts.model.Comment;
import com.rsjava.posts.model.Post;
import com.rsjava.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class PostData implements CommandLineRunner {

    private PostService postService;

    @Autowired
    public PostData(PostService postService) {
        this.postService = postService;
    }

    @Override
    public void run(String... args) throws Exception {

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            Post post = new Post(
                    "Post number: " + (i + 1), "Some content", LocalDateTime.now().minusDays(i)
            );
            for (int j = 0; j < random.nextInt(10) + 1; j++) {
                post.addComment(
                        new Comment("Some comment: " + random.nextInt(123) + 1, LocalDateTime.now())
                );
            }
            postService.save(post);
        }
    }
}
