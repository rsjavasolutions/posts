package com.rsjava.posts.repository;

import com.rsjava.posts.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //tworzę join zapytanie żeby uniknąć problemu N+1
    //left jon ponieważ chcemy wszystkie posty nawet te które nie mają komentarzy
    @Query("SELECT p FROM Post p left join fetch p.commentList")
    List<Post> findAllPosts();
}
