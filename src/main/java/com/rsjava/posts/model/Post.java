package com.rsjava.posts.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Post extends EntityBase {

    @NonNull
    private String title;
    @NonNull
    private String content;
    @NonNull
    private LocalDateTime created;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment){
        commentList.add(comment);
        comment.setPost(this);
    }
}
