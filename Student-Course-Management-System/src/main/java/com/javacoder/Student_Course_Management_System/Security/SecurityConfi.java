package com.javacoder.Student_Course_Management_System.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.javacoder.Student_Course_Management_System.Filter.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfi {



  @Autowired
  JwtFilter filter;

  @Autowired
  JwtAuthentcationEntrypoint jwtAuthentcationEntrypoint;



  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http){
    http 
      .csrf(crsf -> crsf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/h2-console/**").permitAll()
        .requestMatchers("/user/login").permitAll()
        .anyRequest().authenticated()
      )
      .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthentcationEntrypoint))
      .headers(headers -> headers.frameOptions(frame -> frame.disable()))
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);


      return http.build();
  }


  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration confi){
    return confi.getAuthenticationManager();
  }


}
