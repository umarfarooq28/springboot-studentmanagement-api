package com.javacoder.Student_Course_Management_System.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacoder.Student_Course_Management_System.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

  List<Student> findByDepartmentContainingIgnoreCaseAndScoreGreaterThan(String department, double score);


  boolean existsByName(String name);

}
