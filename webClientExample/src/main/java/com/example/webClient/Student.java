package com.example.webClient;

public class Student {
	private Integer studentRoll;
	private String studentName;
	private String mobile;
	
	public Student(){}
	
	public Student(Integer roll, String name, String mobile) {
		this.studentRoll=roll; this.studentName=name; this.mobile=mobile;
	}
	
	public Integer getStudentRoll() {
		return studentRoll;
	}
	public void setStudentRoll(Integer studentRoll) {
		this.studentRoll = studentRoll;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
