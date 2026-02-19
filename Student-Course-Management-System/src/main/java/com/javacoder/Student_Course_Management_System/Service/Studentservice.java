package com.javacoder.Student_Course_Management_System.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.javacoder.Student_Course_Management_System.Dto.UpdateEnrollDto;
import com.javacoder.Student_Course_Management_System.Entity.Course;
import com.javacoder.Student_Course_Management_System.Entity.Enrollment;
import com.javacoder.Student_Course_Management_System.Entity.Student;
import com.javacoder.Student_Course_Management_System.Repository.CourseRepository;
import com.javacoder.Student_Course_Management_System.Repository.Enrollmentrepository;
import com.javacoder.Student_Course_Management_System.Repository.StudentRepository;

@Service
public class Studentservice {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private Enrollmentrepository enrollmentrepository;

  public ResponseEntity<Object> addstudent(Student student) {

      if(!StringUtils.hasText(student.getName())){
        return ResponseEntity.badRequest().body("Name is required");
      }

      studentRepository.save(student);

      return ResponseEntity.status(HttpStatus.CREATED).body(student);

    

    
  }

  public ResponseEntity<Object> getstudentfilter(String department, Double score) {

    if(StringUtils.hasText(department) ||  score == null){

      return ResponseEntity.badRequest().body("Invalid filter");
    }

    List<Student> students = studentRepository.findByDepartmentContainingIgnoreCaseAndScoreGreaterThan(department, score);

    if(students.isEmpty()){
      return ResponseEntity.status(404).body("Student not found");
    }

    return ResponseEntity.ok(students);
  }

  public ResponseEntity<Object> enrollstudentintocourse(Enrollment enrollment) {

    try{

      studentRepository.findById(enrollment.getStudent().getStudentId()).orElseThrow(() -> new RuntimeException("Student not found"));

      courseRepository.findById(enrollment.getCourse().getCourseId()).orElseThrow(() -> new RuntimeException("Course not found"));

      if(enrollmentrepository.existsByStudentStudentIdAndCourseCourseId(enrollment.getStudent().getStudentId(), enrollment.getCourse().getCourseId())){
        return ResponseEntity.status(409).body("enrolled already");
      }

      enrollmentrepository.save(enrollment);

      return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);

    }catch(RuntimeException ex){
      if(ex.getMessage() != null && (ex.getMessage().startsWith("Student not") || ex.getMessage().startsWith("Course not"))){
        return ResponseEntity.status(404).body(ex.getMessage());
      }
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
  }

  public ResponseEntity<Object> updateenroll(Integer enrollId, UpdateEnrollDto updateEnrollDto) {

    try{

      Enrollment enrollment = enrollmentrepository.findById(enrollId).orElseThrow(() -> new RuntimeException("Enrollment not found"));

      enrollment.setStatus(updateEnrollDto.getStatus());

      enrollmentrepository.save(enrollment);

      return ResponseEntity.ok(enrollment);
    }catch(RuntimeException ex){
      if(ex.getMessage() != null && ex.getMessage().startsWith("Enrollment not found")){
        return ResponseEntity.status(404).body(ex.getMessage());
      }
      return ResponseEntity.badRequest().body(ex.getMessage());
    }

  }

  public ResponseEntity<Object> getstudent(Integer stu_id) {
    try{
      List<Enrollment> enrollments = enrollmentrepository.findByStudentStudentId(stu_id);

      List<Course> courses = new ArrayList<>();

      if(enrollments.isEmpty()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enrollment not found");
      }
      for (Enrollment enrollment : enrollments){

        courses.add(enrollment.getCourse());

      }

      return ResponseEntity.ok(courses);

  }catch(RuntimeException ex){
      if(ex.getMessage() != null && ex.getMessage().startsWith("Enrollment not found")){
        return ResponseEntity.status(404).body(ex.getMessage());
      }
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  public ResponseEntity<Object> deleteenrollment(Integer studentId, Integer courseId) {


    if(!enrollmentrepository.existsByStudentStudentIdAndCourseCourseId(studentId, courseId)){
      return ResponseEntity.status(404).body("Enrollmentnotfound");
    }



   

      enrollmentrepository.deleteByStudentStudentIdAndCourseCourseId(studentId, courseId);

      return ResponseEntity.status(204).build();
    

  }

    


  

}
