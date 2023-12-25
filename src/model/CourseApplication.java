package model; 

public class CourseApplication {
	Student student;
	Course course;
	boolean isApproved;
	boolean isChecked;
	
	public CourseApplication(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.isApproved = false;
        this.isChecked = false;
    }
	
	public boolean getIsChecked() {
		return isChecked;
	}
	
	public boolean getIsApproved() {
		return isApproved;
	}
	
	public Course getCourse() {
		return this.course;
	}
	
	public void makeApproved() {
		isChecked = true;
		isApproved = true;
		
		student.enrollInCourse(course);
		course.enrollStudent(student);
	}
	
	public void makeRejected() {
		isChecked = true;
		isApproved = false;
	}
	
	@Override
    public String toString() {
        return "Student: " + student.getEmail() +
               ", Course: " + course.getName();
    }
}
