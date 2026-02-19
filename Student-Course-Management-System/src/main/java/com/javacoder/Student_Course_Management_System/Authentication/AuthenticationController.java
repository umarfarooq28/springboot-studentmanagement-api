package com.javacoder.Student_Course_Management_System.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javacoder.Student_Course_Management_System.Repository.UserInfoRepository;
import com.javacoder.Student_Course_Management_System.Service.Jwtservice;

@RestController
public class AuthenticationController {

  @Autowired
  UserInfoRepository userInfoRepository;


  @Autowired
  AuthenticationManager authenticationManager;


  @Autowired
  Jwtservice jwtservice;


  @PostMapping("/user/login")
  public ResponseEntity<Object> loginpage(@RequestBody Authrequest authrequest){

    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));

    String role = userInfoRepository.findByName(authrequest.getUsername()).get().getRoles();

    String token = jwtservice.generatetoken(authrequest.getUsername(), role);

    AuthResponse authResponse = new AuthResponse(authrequest.getUsername(), token);

    return ResponseEntity.ok(authResponse);

  }




}
