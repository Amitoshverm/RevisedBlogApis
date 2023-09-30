package com.amitosh.blogapis.Dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private Long id;
    @NotNull
    private String categoryTitle;
    @NotNull
    @Size(min = 10)
    private String categoryDescription;
}
