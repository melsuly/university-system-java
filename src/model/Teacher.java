package model;

import java.util.List;

public class Teacher extends Employee {

	private List<Course> teachingCourses;
	
	public Teacher(String email, String password, String name) {
		super(email, password, name);
	}
	
	public void sendComplaintAboutStudent(Student student, String message, UrgencyLevel level) {
        
    }
}
