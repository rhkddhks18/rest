package com.example.rest.article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ArticleCreateDto {
    private String subject;
    private String content;
    private int price;
    private String area;
    private String category;
}
