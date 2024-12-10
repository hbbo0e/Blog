package com.example.blog.controller;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.dto.ArticleResponse;
import com.example.blog.dto.UpdateArticleRequest;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping("/articles")
  public ResponseEntity<List<ArticleResponse>> findAllArticles(){
    List<ArticleResponse> articles = blogService.findAll()
        .stream()
        .map(ArticleResponse::new)
        .toList();

    return ResponseEntity.ok()
        .body(articles);
  }

  @GetMapping("/article/{id}")
  public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
     Article article = blogService.findById(id);

     return ResponseEntity.ok()
         .body(new ArticleResponse(article));
  }

  @DeleteMapping("/article/{id}")
  public ResponseEntity<Void> deleteArticle(@PathVariable long id){
    blogService.delete(id);

    return ResponseEntity.ok()
        .build();
  }

  @PutMapping("/article/{id}")
  public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody
      UpdateArticleRequest request){
    Article updatedArticle = blogService.update(id, request);

    return ResponseEntity.ok()
        .body(updatedArticle);
  }
}
