package com.rsjava.posts.dto.mapper;

import com.rsjava.posts.dto.PostDto;
import com.rsjava.posts.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    private PostDto mapToPostDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setTitle(post.getTitle());
        postDto.setCreated(post.getCreated());
        return postDto;
    }

    public List<PostDto> mapToListPostDto(List<Post> postList){
        return postList.stream()
                .map(post -> mapToPostDto(post))
                .collect(Collectors.toList());
    }
}
