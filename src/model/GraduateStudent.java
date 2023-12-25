package model;

import model.researcher.ResearcherStudent;

public class GraduateStudent extends ResearcherStudent {

    private GraduateStudentType type;

    public GraduateStudent(String email, String password, String name, int id, GraduateStudentType type) {
        super(email, password, name, id);
        this.type = type;
    }

}
