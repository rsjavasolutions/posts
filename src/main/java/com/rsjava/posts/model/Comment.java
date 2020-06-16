package com.rsjava.posts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Comment extends EntityBase {

    @NonNull
    private String content;
    @NonNull
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

}
