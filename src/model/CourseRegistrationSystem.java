package model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

import core.AppState;

public final class CourseRegistrationSystem implements Serializable {
	public List<CourseApplication> applications = new ArrayList<>();

	public void addApplication(CourseApplication application) {
		applications.add(application);
	}

	public List<CourseApplication> getUncheckedApplications() {
		return applications.stream()
				.filter(application -> !application.getIsChecked())
				.collect(Collectors.toList());
	}

	public List<CourseApplication> getApplicationOfStudent(Student student) {
		return applications.stream()
				.filter(application -> application.student == student)
				.collect(Collectors.toList());
	}

	public List<Course> getAvailableCourses(Student student) {
		List<CourseApplication> studentApplications = getApplicationOfStudent(student);

		List<Course> appliedCourses = studentApplications.stream()
				.filter(application -> !application.isChecked || (application.isChecked && application.isApproved))
				.map(CourseApplication::getCourse)
				.collect(Collectors.toList());

		return AppState.getInstance().getCourses().stream()
				.filter(course -> !appliedCourses.contains(course))
				.collect(Collectors.toList());
	}
}
