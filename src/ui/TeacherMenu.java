package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import model.*;

public class TeacherMenu extends MainMenu {

    public static Map<String, Course> coursesMap = new HashMap<>();
    private Map<String, Map<String, Mark>> courseStudentMarks = new HashMap<>();

    @Override
    public void showSpecificMenu() throws IOException {
        while (true) {
            System.out.println("Teacher menu...");
            System.out.println("");
            System.out.println("1. Course information");
            System.out.println("");
            System.out.println("2. My courses");
            System.out.println("");
            System.out.println("3. Put mark");
            System.out.println("");
            showCommonOptions();
            String choice = reader.readLine();
            if (handleCommonChoice(choice)) {
                break;
            }
            switch (choice.trim()) {
                case "1":
                    System.out.println("");
                    System.out.println("Press Enter to go back");
                    System.out.println("");

                    reader.readLine();
                    break;
                case "2":
                    // MyCourses();
                    break;
                case "3":
                    putMark();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }

    }

    public void putMark() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Display courses
        System.out.println("Available Courses:");
        int courseNumber = 1;
        for (Course course : coursesMap.values()) {
            System.out.println(courseNumber + ". " + course.getName());
            courseNumber++;
        }

        // Choose a course
        System.out.println("Choose a course by entering its number:");
        int chosenCourseNumber = Integer.parseInt(reader.readLine());

        if (chosenCourseNumber < 1 || chosenCourseNumber > coursesMap.size()) {
            System.out.println("Invalid course number. Please try again.");
            return;
        }

        Course chosenCourse = null;
        int currentCourseNumber = 1;
        for (Course course : coursesMap.values()) {
            if (currentCourseNumber == chosenCourseNumber) {
                chosenCourse = course;
                break;
            }
            currentCourseNumber++;
        }

        // Display students in the chosen course
        System.out.println("Enrolled students in " + chosenCourse.getName() + ":");
        Map<String, Mark> studentMarks = courseStudentMarks.get(chosenCourse.getCourseCode());
        int studentNumber = 1;
        for (Map.Entry<String, Mark> entry : studentMarks.entrySet()) {
            System.out.println(studentNumber + ". " + entry.getKey());
            studentNumber++;
        }

        // Choose a student
        System.out.println("Choose a student by entering the student's number:");
        int chosenStudentNumber = Integer.parseInt(reader.readLine());

        if (chosenStudentNumber < 1 || chosenStudentNumber > studentMarks.size()) {
            System.out.println("Invalid student number. Please try again.");
            return;
        }

        String chosenStudentId = null;
        int currentStudentNumber = 1;
        for (String studentId : studentMarks.keySet()) {
            if (currentStudentNumber == chosenStudentNumber) {
                chosenStudentId = studentId;
                break;
            }
            currentStudentNumber++;
        }

        // Input marks for the chosen student
        Mark mark = studentMarks.get(chosenStudentId);
        if (mark == null) {
            mark = new Mark();
        }

        System.out.println("Enter attestation 1 mark:");
        mark.setAttestation1(Integer.parseInt(reader.readLine()));

        System.out.println("Enter attestation 2 mark:");
        mark.setAttestation2(Integer.parseInt(reader.readLine()));

        System.out.println("Enter final exam mark:");
        mark.setFinalMark(Integer.parseInt(reader.readLine()));

        // Update the marks in the map
        studentMarks.put(chosenStudentId, mark);

        System.out.println("Mark added successfully for student ID: " + chosenStudentId);
    }
}