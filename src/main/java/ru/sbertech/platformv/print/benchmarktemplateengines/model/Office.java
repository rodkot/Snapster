package ru.sbertech.platformv.print.benchmarktemplateengines.model;

import java.util.List;

class Office {
    private String name;
    private String location;
    private List<Department> departments;
    private List<String> resources;

    public Office(String name, String location, List<Department> departments, List<String> resources) {
        this.name = name;
        this.location = location;
        this.departments = departments;
        this.resources = resources;
    }

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<Department> getDepartments() { return departments; }
    public void setDepartments(List<Department> departments) { this.departments = departments; }

    public List<String> getResources() { return resources; }
    public void setResources(List<String> resources) { this.resources = resources; }

    @Override
    public String toString() {
        return "Office{name='" + name + "', location='" + location + "'}";
    }
}
