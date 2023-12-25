package ui;

import java.io.IOException;
import java.util.List;

import authentication.Session;
import core.AppState;
import model.Course;
import model.CourseApplication;
import model.Student;

public final class StudentMenu extends MainMenu {
	@Override
	public void showSpecificMenu() throws IOException {
		while (true) {
			System.out.println(LanguageManager.getInstance().translate("student_menu"));
			System.out.println("");
			System.out.println("1. " + LanguageManager.getInstance().translate("view_courses"));
			System.out.println("2. " + LanguageManager.getInstance().translate("register_course"));
			System.out.println("3. " + LanguageManager.getInstance().translate("view_applications"));
			System.out.println("");

			showCommonOptions();

			String choice = reader.readLine();

			if (handleCommonChoice(choice)) {
				break;
			}

			switch (choice.trim()) {
				case "1":
					showCourses();
					break;
				case "2":
					showRegisterCourse();
					break;
				case "3":
					showMyApplications();
					break;
				default:
					System.out.println(LanguageManager.getInstance().translate("invalid_choice"));
			}
		}
	}

	private void showCourses() throws IOException {

		Student student = (Student) Session.getCurrentUser();

		List<Course> enrolledCourses = student.getEnrolledCourses();

		if (enrolledCourses.isEmpty()) {
			System.out.println("No courses enrolled.");
		} else {
			System.out.println("Enrolled Courses:");
			for (Course course : enrolledCourses) {
				System.out.println(course.getName() + " - " + course.getCourseId());
			}
		}

		System.out.println("");
		System.out.println(LanguageManager.getInstance().translate("press_enter_to_go_back"));
		System.out.println("");

		reader.readLine();
	}

	private void showMyApplications() throws IOException {
		Student student = (Student) Session.getCurrentUser();
		List<CourseApplication> applications = AppState.getInstance().courseRegistrationSystem.getApplicationOfStudent(student);

		if (applications.isEmpty()) {
			System.out.println("No applications!");

			System.out.println("");
			System.out.println(LanguageManager.getInstance().translate("press_enter_to_go_back"));
			System.out.println("");

			reader.readLine();
		} else {
			for (int i = 0; i < applications.size(); i++) {
				CourseApplication application = applications.get(i);

				String status;

				if (!application.getIsChecked()) {
					status = LanguageManager.getInstance().translate("not_checked") + "!";
				} else if (application.getIsApproved()) {
					status = LanguageManager.getInstance().translate("approved") + "!";
				} else {
					status = LanguageManager.getInstance().translate("rejected") + "!";
				}

				System.out.println(i + 1 + ". " + application.toString() + ". "
						+ LanguageManager.getInstance().translate("status") + ": " + status);
			}

			System.out.println("");
			System.out.println(LanguageManager.getInstance().translate("press_enter_to_go_back"));
			System.out.println("");

			reader.readLine();
		}
	}

	private void showRegisterCourse() throws IOException {
		Student student = (Student) Session.getCurrentUser();
		List<Course> courses = AppState.getInstance().courseRegistrationSystem.getAvailableCourses(student);

		if (courses.isEmpty()) {
			System.out.println(LanguageManager.getInstance().translate("no_courses_available"));

			System.out.println("");
			System.out.println(LanguageManager.getInstance().translate("press_enter_to_go_back"));
			System.out.println("");

			reader.readLine();
		} else {
			for (int i = 0; i < courses.size(); i++) {
				Course course = courses.get(i);

				System.out.println(i + 1 + ". " + course.toString());
			}
			System.out.println(courses.size() + 1 + ". " + " Return back");
			System.out.println("");
			System.out.print("Type number of course: ");

			while (true) {
				int index = Integer.parseInt(reader.readLine());
				if (index == courses.size() + 1) {
					break;
				} else if (index > courses.size() || index < 1) {
					System.out.println("Invalid number.");
				} else {
					Course selectedCourse = courses.get(index - 1);
					CourseApplication application = new CourseApplication(student, selectedCourse);

					AppState.getInstance().courseRegistrationSystem.addApplication(application);

					System.out.println(LanguageManager.getInstance().translate("application_sent") + "!");

					break;
				}
			}
		}

	}
}
