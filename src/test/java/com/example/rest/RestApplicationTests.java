package com.example.rest;

import com.example.rest.article.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApplicationTests {

	@Autowired
	private ArticleService articleService;

//	@Test
//	void contextLoads() {
//		articleService.create("test1", "test1", 100, "대전");
//		articleService.create("test2", "test2", 100, "대전");
//		articleService.create("test3", "test3", 100, "대전");
//	}

}
