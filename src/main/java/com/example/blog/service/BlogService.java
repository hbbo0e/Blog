package com.example.blog.service;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.repository.BlogRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {
  private final BlogRepository blogRepository;

  public Article save(AddArticleRequest request){
    return blogRepository.save(request.toEntity());
  }
  public List<Article> findAll(){
    return blogRepository.findAll();
  }

  // findById 에서 orElseThrow 를 하지 않으면 에러가 나오던데 왜그런걸까
  public Article findById(long id){
    return blogRepository.findById(id).orElseThrow(()
        -> new IllegalArgumentException("not found : " + id));
  }

  public void delete(long id){
    blogRepository.deleteById(id);
  }

}
