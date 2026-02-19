package com.javacoder.Student_Course_Management_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacoder.Student_Course_Management_System.Entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

  

}
