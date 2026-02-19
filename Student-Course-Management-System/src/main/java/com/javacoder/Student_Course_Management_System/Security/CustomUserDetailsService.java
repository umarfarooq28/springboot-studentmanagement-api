package com.javacoder.Student_Course_Management_System.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.javacoder.Student_Course_Management_System.Entity.UserInfo;
import com.javacoder.Student_Course_Management_System.Repository.UserInfoRepository;


@Component
public class CustomUserDetailsService implements UserDetailsService{

  @Autowired
  UserInfoRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


    UserInfo userInfo = repository.findByName(username).orElseThrow(() -> new RuntimeException("User not found"));

    return new CustomUserdetails(userInfo);




  }









}
