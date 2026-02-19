package com.javacoder.Student_Course_Management_System.Entity;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer enrollmentId;


  @ManyToOne
  @JoinColumn(name = "studentId")
  @JsonIgnore
  @JsonProperty("Student_Id")
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "studentId")
  @JsonIdentityReference(alwaysAsId = true)
  private Student student;


  @ManyToOne
  @JoinColumn(name = "courseId")
  @JsonProperty("Course_Id")

  @JsonIgnore
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "courseId")
  @JsonIdentityReference(alwaysAsId = true)
  private Course course;

  private String status;

}
