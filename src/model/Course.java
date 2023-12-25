package model;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class Course implements Serializable {
    private String courseCode;
    private String courseName;
    private String courseId;
    private Vector<Teacher> instructors;
    public Vector<Student> enrolledStudents;
    private int maxCapacity;
    private CourseType courseType;

    public Course(String courseId, String name, int maxCapacity, CourseType courseType) {
        this.courseId = courseId;
        this.courseName = name;
        this.maxCapacity = maxCapacity;
        this.courseType = courseType;
        this.instructors = new Vector<>();
        this.enrolledStudents = new Vector<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return courseName;
    }

    public List<Teacher> getInstructors() {
        return instructors;
    }

    public void addInstructor(Teacher instructor) {
        instructors.add(instructor);
    }

    public Vector<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(Student student) {
        if (enrolledStudents.size() < maxCapacity) {
            enrolledStudents.add(student);
        } else {
            System.out.println("Course is at full capacity.");
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    @Override
    public String toString() {
        return courseId + ": " + courseName;
    }
}
