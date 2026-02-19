package com.javacoder.Student_Course_Management_System.Dto;

public class UpdateEnrollDto {


  private String status;

  

  public UpdateEnrollDto() {
  }

  public UpdateEnrollDto(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  

}
