package com.example.blog.controller;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BlogController {

  private final BlogService blogService;
  private final BlogRepository blogRepository;

  @PostMapping("/articles")
  public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
    Article savedArticle = blogService.save(request);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(savedArticle);
  }
}
