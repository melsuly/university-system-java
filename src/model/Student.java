package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.researcher.ResearcherStudent;

public class Student extends User {
	private List<Course> enrolledCourses;
	private int id;
	public static HashMap<Course, Mark> courses;
	{
		courses = new HashMap<Course, Mark>();
	}

	public Student(String email, String password, String name, int id) {
		super(email, password, name);
		this.id = id;
		enrolledCourses = new ArrayList<>();
	}

	public void enrollInCourse(Course course) {
		enrolledCourses.add(course);
	}

	public List<Course> getEnrolledCourses() {
		return this.enrolledCourses;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return this.id == other.id;
	}

	public String toString() {
		return super.toString() + " ID: " + this.id;
	}

	public ResearcherStudent toResearcherStudent() {
		return new ResearcherStudent(this.getEmail(), this.getPassword(), this.getName(), this.getId());
	}
}
