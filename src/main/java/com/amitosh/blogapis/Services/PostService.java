package com.amitosh.blogapis.Services;

import com.amitosh.blogapis.Dtos.PostDto;
import com.amitosh.blogapis.Enitities.Post;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Long user_id, Long category_id);
    PostDto updatePost(PostDto postDto, Long id);
    void deletePost(Long id);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);

    // Get all post by category id
    List<PostDto> getAllPostByCategory(Long category_id);

    // Get all post by user id
    List<PostDto> getAllPostByUser(Long user_id);
}
