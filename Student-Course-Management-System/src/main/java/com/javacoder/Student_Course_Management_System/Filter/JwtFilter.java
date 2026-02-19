package com.javacoder.Student_Course_Management_System.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.javacoder.Student_Course_Management_System.Security.CustomUserDetailsService;
import com.javacoder.Student_Course_Management_System.Service.Jwtservice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

  @Autowired
  Jwtservice jwtservice;

  @Autowired
  CustomUserDetailsService customUserDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

        String token = gettokenfromrequest(request);

        try {
          if(StringUtils.hasText(token) && jwtservice.validatetoken(token)){

            String username = jwtservice.extractemail(token);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          }
        } catch (Exception e) {
            logger.error("Invaild Jwt token", e);
        }

        filterChain.doFilter(request, response);
  }

  private String gettokenfromrequest(HttpServletRequest request) {

    String bearer_token = request.getHeader("Authorization");

    if(StringUtils.hasText(bearer_token) && bearer_token.startsWith("Bearer ")){
      return bearer_token.substring(7, bearer_token.length());
    }

    return null;
  }

}
