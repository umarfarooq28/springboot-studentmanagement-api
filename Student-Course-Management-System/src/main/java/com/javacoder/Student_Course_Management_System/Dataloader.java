package com.javacoder.Student_Course_Management_System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.javacoder.Student_Course_Management_System.Entity.UserInfo;
import com.javacoder.Student_Course_Management_System.Repository.UserInfoRepository;

/*@Component
public class Dataloader implements ApplicationRunner{


  @Autowired UserInfoRepository ur;

  @Autowired PasswordEncoder encoder;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    ur.save(new UserInfo(null,"Officer","officer@tcs.com", encoder.encode("pass1"), "OFFICER"));
    ur.save(new UserInfo(null,"UserA","usera@tcs.com", encoder.encode("pass2"), "USER"));
    ur.save(new UserInfo(null,"UserB","userb@tcs.com", encoder.encode("pass3"), "USER"));
   
  }

}*/
