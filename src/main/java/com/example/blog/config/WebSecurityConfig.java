//package com.example.blog.config;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//import com.example.blog.service.UserDetailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//
///**
// * oauth2 와 jwt 를 함께 사용하려면 기존 스프링 시큐리티를 구현하며 작성한 설정이 아니라 다른 설정을 사용해야 함
// * oauth2, jwt 에 맞게 설정 파일 수정 위하여 주석 처리 -> WebOAuthSecurityConfig 작성
// */
//@RequiredArgsConstructor
//@Configuration
//public class WebSecurityConfig {
//
//  private final UserDetailService userService;
//
//  @Bean
//  public WebSecurityCustomizer configure(){
//    return (web) -> web.ignoring()
//        .requestMatchers(toH2Console())
//        .requestMatchers("/static/**");
//  }
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//    return http
//        .authorizeRequests()
//        .requestMatchers("/login", "/signup", "/user").permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .formLogin()
//        .loginPage("/login")
//        .defaultSuccessUrl("/articles")
//        .and()
//        .logout()
//        .logoutSuccessUrl("/login")
//        .invalidateHttpSession(true)
//        .and()
//        .csrf().disable()
//        .build();
//  }
//
//  @Bean
//  public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception{
//    return http
//        .getSharedObject(AuthenticationManagerBuilder.class)
//        .userDetailsService(userService)
//        .passwordEncoder(bCryptPasswordEncoder)
//        .and()
//        .build();
//  }
//
//  @Bean
//  public BCryptPasswordEncoder bCryptPasswordEncoder(){
//    return new BCryptPasswordEncoder();
//  }
//}
