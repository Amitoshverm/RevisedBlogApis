package com.amitosh.blogapis.Controller;

import com.amitosh.blogapis.Dtos.PostDto;
import com.amitosh.blogapis.Services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/users/{user_id}/categories/{category_id}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                           @PathVariable Long user_id,
                                           @PathVariable Long category_id){

        return new ResponseEntity<>(postService.createPost(postDto, user_id, category_id), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long id) {
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("post deleted with id "+id, HttpStatus.OK);
    }
    @GetMapping("posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.FOUND);
    }

    @GetMapping("/{categoryId}/category")
    public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(postService.getAllPostByCategory(categoryId), HttpStatus.FOUND);
    }

    @GetMapping("/{userId}/user")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(postService.getAllPostByUser(userId), HttpStatus.FOUND);
    }

}
