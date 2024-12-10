package ru.sbertech.platformv.print.benchmarktemplateengines.model;

import java.util.List;

class Employee {
    private String name;
    private String position;
    private Department department;
    private double salary;
    private int experience;
    private List<Project> projects;

    public Employee(String name, String position, Department department, double salary, int experience, List<Project> projects) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.experience = experience;
        this.projects = projects;
    }

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', position='" + position + "', salary=" + salary + "}";
    }
}