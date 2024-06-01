package com.example;

public class Employee {

    private int id;
    private String name;
    private String gender;
    private Department department;

    // Getters and Setters
    public Employee(int id, String name, String gender, Department department) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
