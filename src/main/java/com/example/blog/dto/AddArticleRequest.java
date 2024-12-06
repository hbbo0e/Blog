package com.example.blog.dto;


import com.example.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 근데 이거는 파라미터가 없는 생성자잖아? title 이랑 content 랑 어차피 null 이면 안 되는데 이게 있을 필요가 잇을가? 굳이? 아 jpa 가 사용해서 해서 그런가?
@AllArgsConstructor
@Getter
public class AddArticleRequest {

  private String title;
  private String content;

  // 생성자를 통해서 객체를 생성 하는 것
  public Article toEntity(){
    return Article.builder()
        .title(title)
        .content(content)
        .build();
  }
}
