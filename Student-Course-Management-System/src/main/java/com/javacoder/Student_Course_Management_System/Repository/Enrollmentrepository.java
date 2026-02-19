package com.javacoder.Student_Course_Management_System.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacoder.Student_Course_Management_System.Entity.Enrollment;

@Repository
public interface Enrollmentrepository extends JpaRepository<Enrollment, Integer>{

  
  Boolean existsByStudentStudentIdAndCourseCourseId(Integer studentid, Integer courseid);


  void deleteByStudentStudentIdAndCourseCourseId(Integer studentid, Integer courseid);


  List<Enrollment> findByStudentStudentId(Integer studentid);




}
