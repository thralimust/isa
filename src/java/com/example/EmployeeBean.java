package com.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class EmployeeBean {

    private String name;
    private String gender;
    private String department;
    private List<Employee> employees;

    // Getters and Setters
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    // Database operations
    public void addEmployee() {
        try {
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ass  ?autoReconnect=true&useSSL=false", "root", "9129");
            String sql = "INSERT INTO employees (name, gender, department ) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setString(3, department);
            statement.executeUpdate();
            loadEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEmployees() {
        try {
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ass  ?autoReconnect=true&useSSL=false", "root", "9129");
            String sql = "SELECT * FROM employees";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            employees = new ArrayList();
            while (resultSet.next()) {
                Employee emp = new Employee();
                emp.setId(resultSet.getInt("id"));
                emp.setName(resultSet.getString("name"));
                emp.setGender(resultSet.getString("gender"));
                emp.setDepartment(resultSet.getString("department"));
                employees.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
