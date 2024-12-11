package com.example.blog.controller;

import com.example.blog.dto.ArticleListViewResponse;
import com.example.blog.service.BlogService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

  private final BlogService blogService;

  @GetMapping("/articles")
  public String getArticles(Model model){
    List<ArticleListViewResponse> articles = blogService.findAll().stream()
        .map(ArticleListViewResponse::new)
        .toList();
    model.addAttribute("articles", articles); // 블로그 글 리스트 저장

    return "articleList"; // articleList.html 이라는 뷰 조회
  }
}
