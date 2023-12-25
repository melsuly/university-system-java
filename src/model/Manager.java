package model;

public class Manager extends Employee {
    private Department department;
    private ManagerType managerType;

    public Manager(String email, String password, String name, ManagerType managerType, Department department) {
        super(email, password, name);
        this.department = department;
        this.managerType = managerType;
    }
}
