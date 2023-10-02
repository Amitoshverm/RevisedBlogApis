package com.amitosh.blogapis.Repositories;

import com.amitosh.blogapis.Enitities.Category;
import com.amitosh.blogapis.Enitities.Post;
import com.amitosh.blogapis.Enitities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser( User user);

    List<Post> findByCategory( Category category);
}
