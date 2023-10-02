package com.amitosh.blogapis.Services;

import com.amitosh.blogapis.Dtos.PostDto;
import com.amitosh.blogapis.Enitities.Category;
import com.amitosh.blogapis.Enitities.Post;
import com.amitosh.blogapis.Enitities.User;
import com.amitosh.blogapis.Repositories.CategoryRepository;
import com.amitosh.blogapis.Repositories.PostRepository;
import com.amitosh.blogapis.Repositories.UserRepository;
import com.amitosh.blogapis.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PostDto createPost(PostDto postDto, Long user_id, Long category_id) {
        User user = userRepository.findById(user_id).
                orElseThrow(()-> new ResourceNotFoundException("user", "id", user_id));

        Category category = categoryRepository.findById(category_id).
                orElseThrow(()-> new ResourceNotFoundException("category", "id", category_id));

        Post post = PostDtoToPost(postDto);
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = postRepository.save(post);
        return PostToPostDto(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImage(postDto.getImage());

        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(modelMapper.map(post, PostDto.class));
        }
        return postDtos;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostByCategory(Long category_id) {
        Category category = categoryRepository.findById(category_id).
                orElseThrow(()-> new ResourceNotFoundException("category", "id", category_id));

        List<Post> posts = postRepository.findByCategory(category);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(modelMapper.map(post, PostDto.class));
        }
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByUser(Long user_id) {
        User user = userRepository.findById(user_id).
                orElseThrow(()-> new ResourceNotFoundException("user", "id", user_id));

        List<Post> posts = postRepository.findByUser(user);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(modelMapper.map(post, PostDto.class));
        }
        return postDtos;
    }

    public Post PostDtoToPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);

//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        post.setImage(postDto.getImage());
//        post.setAddDate(new Date());
        return post;
    }
    public PostDto PostToPostDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);

//        postDto.setTitle(post.getTitle());
//        postDto.setContent(post.getContent());
//        postDto.setImage(post.getImage());
//        postDto.setAddedDate(new Date());
        return postDto;
    }
}
