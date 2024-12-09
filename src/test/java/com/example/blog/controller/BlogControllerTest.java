package com.example.blog.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  BlogRepository blogRepository;

  @BeforeEach
  public void mockMvcSetUp(){
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .build();
    blogRepository.deleteAll();
  }

  @DisplayName("addArticle: 블로그 글 추가에 성공한다")
  @Test
  public void addArticle() throws Exception {
    // given
    final String url = "/api/articles";
    final String title = "title";
    final String content = "content";
    final AddArticleRequest userRequest = new AddArticleRequest(title, content);
    final String requestBody = objectMapper.writeValueAsString(userRequest);

    // when
    ResultActions result = mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestBody));

    // then
    result.andExpect(status().isCreated());

    List<Article> articles = blogRepository.findAll();

    assertThat(articles.size()).isEqualTo(1);
    assertThat(articles.get(0).getTitle()).isEqualTo(title);
    assertThat(articles.get(0).getContent()).isEqualTo(content);
  }
}