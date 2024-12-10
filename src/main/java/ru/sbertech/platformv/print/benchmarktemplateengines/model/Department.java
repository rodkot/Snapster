package ru.sbertech.platformv.print.benchmarktemplateengines.model;

import java.util.List;

class Department {
    private String name;
    private int employeeCount;
    private double budget;
    private List<Project> projects;

    public Department(String name, int employeeCount, double budget, List<Project> projects) {
        this.name = name;
        this.employeeCount = employeeCount;
        this.budget = budget;
        this.projects = projects;
    }

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getEmployeeCount() { return employeeCount; }
    public void setEmployeeCount(int employeeCount) { this.employeeCount = employeeCount; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }

    @Override
    public String toString() {
        return "Department{name='" + name + "', employeeCount=" + employeeCount + "}";
    }
}
