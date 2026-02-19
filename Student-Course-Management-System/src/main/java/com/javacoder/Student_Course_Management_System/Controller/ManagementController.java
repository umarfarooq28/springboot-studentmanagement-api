package com.javacoder.Student_Course_Management_System.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javacoder.Student_Course_Management_System.Dto.UpdateEnrollDto;
import com.javacoder.Student_Course_Management_System.Entity.Enrollment;
import com.javacoder.Student_Course_Management_System.Entity.Student;
import com.javacoder.Student_Course_Management_System.Service.Studentservice;



@RestController
public class ManagementController {

  @Autowired
  Studentservice service;


  @PostMapping("/student")
  public ResponseEntity<Object> addstudent(@RequestBody Student student){

    return service.addstudent(student);

  }

  @GetMapping("/students/filter")
  public ResponseEntity<Object> getstudentbyfilter(@RequestParam String department, @RequestParam Double score){
    return service.getstudentfilter(department,score);
  }

  @PostMapping("/enroll")
  public ResponseEntity<Object> enrollstudentintocourse(@RequestBody Enrollment enrollment){
    return service.enrollstudentintocourse(enrollment);
  }

  @PutMapping("/enroll")
  public ResponseEntity<Object> updateenroll(@RequestParam Integer enrollId ,@RequestBody UpdateEnrollDto updateEnrollDto){

    return service.updateenroll(enrollId, updateEnrollDto);


  }

  @GetMapping("/student/{id}/courses")
  public ResponseEntity<Object> getStudent(@PathVariable("id") Integer stu_id){
    return service.getstudent(stu_id);
  }


  @DeleteMapping("/enroll")
  public ResponseEntity<Object> deleteenrollment(@RequestParam Integer studentId, @RequestParam Integer courseId){
    return service.deleteenrollment(studentId,courseId);
  }



}
