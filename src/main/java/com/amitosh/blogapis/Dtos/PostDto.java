package com.amitosh.blogapis.Dtos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private String title;
    private String content;
    private String image;
    private CategoryDto category;
    private UserDto user;
}
