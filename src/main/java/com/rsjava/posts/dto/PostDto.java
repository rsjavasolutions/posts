package com.rsjava.posts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;

}
