package com.javacoder.Student_Course_Management_System.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.javacoder.Student_Course_Management_System.Entity.UserInfo;

public class CustomUserdetails implements UserDetails {

  private String name;

  private String password;

  private List<GrantedAuthority> authorities;


  public CustomUserdetails(UserInfo userinfo){
    this.name = userinfo.getName();
    this.password = userinfo.getPassword();
    this.authorities = new ArrayList<>();
    this.authorities.add(new SimpleGrantedAuthority(userinfo.getRoles()));

  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;

  }

  @Override
  public String getPassword() {
    return password;
    
  }

  @Override
  public String getUsername() {
    return name;
  
  }

}
