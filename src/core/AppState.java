package core;

import java.io.*;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import model.*;
import model.researcher.IResearcher;
import model.researcher.ResearcherStudent;
import ui.LanguageManager;

/**
 * AppState is a Singleton class that manages the application's state,
 * including users and courses. It provides functionality to serialize
 * and deserialize the state to and from a file.
 * 
 * @author Dias Nurgaliev
 */
public class AppState implements Serializable {
    private static AppState instance;
    private static String filename = "AppState.ser";

    private Vector<User> users = new Vector<User>();
    private Vector<Course> courses = new Vector<Course>();
    private Language currentLanguage = null;
    public CourseRegistrationSystem courseRegistrationSystem = new CourseRegistrationSystem();

    /**
     * Private constructor for AppState. It initializes the state with
     * hardcoded users and courses.
     */
    private AppState() {
        // hardcoded users
        users.add(new Student("dias@uni.com", "123", "Dias", 1188));
        users.add(new Teacher("pakita@uni.com", "123", "Pakita"));
        users.add(new Manager("maqsat@uni.com", "123", "Maqsat", ManagerType.DEPARTMENT, Department.SITE));

        ResearcherStudent rs = new ResearcherStudent("nurasyl@uni.com", "123", "Nurasyl", 123);

        Vector<IResearcher> authors = new Vector<IResearcher>();

        authors.add(rs);

        Date date = new Date();

        rs.addResearchPaper(new ResearchPaper(100, "Hello, World!", authors, "Test Journal", 123, date, "doi"));
        rs.addResearchPaper(new ResearchPaper(100, "Hello, World 2!", authors, "Test Journal 2", 127, date, "doi"));

        users.add(rs);

        rs.printPapers(new Comparator<ResearchPaper>() {
            @Override
            public int compare(ResearchPaper o1, ResearchPaper o2) {
                return o1.getPages() > o2.getPages() ? 1 : -1;
            }
        });

        System.out.println(rs.calculateHIndex());

        // hardcoded courses
        courses.add(new Course("CS101", "Introduction to Programming", 3, CourseType.MAJOR));
        courses.add(new Course("CS102", "Object Oriented Programming", 3, CourseType.MINOR));
        courses.add(new Course("CS103", "Data Structures and Algorithms", 3, CourseType.ELECTIVE));

    }

    /**
     * Returns the singleton instance of AppState. If the instance is not
     * already created, it tries to deserialize it from a file, or creates
     * a new one if deserialization fails.
     * 
     * @return The singleton instance of AppState.
     */
    public static AppState getInstance() {
        if (instance == null) {
            try {
                instance = deserialize();
            } catch (IOException | ClassNotFoundException e) {
                instance = new AppState();
                instance.serialize();
            }
        }
        return instance;
    }

    /**
     * Gets the list of users in the application state.
     * 
     * @return Vector of users.
     */
    public Vector<User> getUsers() {
        return users;
    }

    /**
     * Gets the list of courses in the application state.
     * 
     * @return Vector of courses.
     */
    public Vector<Course> getCourses() {
        return courses;
    }

    /**
     * Adds a user to the application state.
     * 
     * @param user The user to add.
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Adds a course to the application state.
     * 
     * @param course The course to add.
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Gets the current language of the application.
     * 
     * @return The current language.
     */
    public Language getCurrentLanguage() {
        return currentLanguage;
    }

    /**
     * Gets the current language of the application.
     * 
     * @return The current language.
     */
    public Locale getCurrentLocale() {
        return new Locale(currentLanguage.getCode(), currentLanguage.getRegion());
    }

    /**
     * Sets the current language of the application.
     * 
     * @param language The language to set.
     */
    public void setCurrentLanguage(Language language) {
        currentLanguage = language;
        LanguageManager.getInstance().changeLocale(getCurrentLocale());
    }

    /**
     * Serializes the current state of the AppState instance to a file.
     */
    public void serialize() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the AppState instance from a file.
     * 
     * @return The deserialized AppState instance.
     * @throws IOException            If there is an I/O error during reading from
     *                                the file.
     * @throws ClassNotFoundException If the class of a serialized object cannot be
     *                                found.
     */
    private static AppState deserialize() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (AppState) in.readObject();
        }
    }
}
