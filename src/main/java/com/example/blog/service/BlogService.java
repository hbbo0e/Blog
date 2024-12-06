package com.example.blog.service;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final 이 붙거나 @NotNull 이 붙은 필드의 생성자 추가
@Service
public class BlogService {
  private final BlogRepository blogRepository;

  public Article save(AddArticleRequest request){
    System.out.println("----- service -----" + request.toEntity());
    return blogRepository.save(request.toEntity());
  }
}
