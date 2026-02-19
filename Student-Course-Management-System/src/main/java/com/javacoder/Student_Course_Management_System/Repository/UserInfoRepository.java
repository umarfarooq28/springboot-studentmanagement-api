package com.javacoder.Student_Course_Management_System.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacoder.Student_Course_Management_System.Entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{


  Optional<UserInfo> findByName(String name);

  boolean existsByName(String name);

}
