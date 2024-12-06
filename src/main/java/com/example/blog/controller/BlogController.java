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

@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

  private final BlogService blogService;
  private final BlogRepository blogRepository;

  @PostMapping("/v1/articles")
  public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
    // @RequestBody 로 요청 본문 값 매핑
    Article savedArticle = blogService.save(request);
    System.out.println("----- controller -----" + request.getTitle());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(savedArticle);
  }
}
