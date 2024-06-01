package com.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

@ManagedBean
@RequestScoped
public class EmployeeBean {

    private String name;
    private String gender;
   
    private int departmentId;
    private List<Employee> employees;
    private List<Department> departments;
/////////////////////
    
        @PostConstruct
    public void init() {
        loadDepartment();
        loadEmployees();
    }
        public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    
        public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
    // Getters and Setters
        public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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





    // Database operations
    public void addEmployee() {
        try {
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ass  ?autoReconnect=true&useSSL=false", "root", "9129");
            String sql = "INSERT INTO employees (name, gender, department_id ) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
             System.out.println("------------1111111111111---");
            statement.setString(1, name);
             System.out.println("------------11111111111---");
            statement.setString(2, gender);
            statement.setInt(3, departmentId);
            statement.executeUpdate();
          
        } catch (Exception e) {
            e.printStackTrace();
        }  loadEmployees();
    }

    public void loadEmployees() {
         
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ass  ?autoReconnect=true&useSSL=false", "root", "9129");
            System.out.println(connection);
            String sql = "SELECT e.id, e.name, e.gender, d.name AS department_name FROM employees e JOIN departments d ON e.department_id = d.id";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
          
               System.out.println("--------1111111111-------");
            while (rs.next()) {
                 employees = new ArrayList();
                  System.out.println("----------0000000-----");
                  departments = new ArrayList();
                  
      employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), new Department(0, rs.getString("department_name"))));
                
              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    public void loadDepartment() {
          departments = new ArrayList();
        try {
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ass  ?autoReconnect=true&useSSL=false", "root", "9129");
            String sql = "SELECT * FROM departments";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
         
            System.out.println("---------------");
            while (resultSet.next()) {
                System.out.println("---------00000000000------");
                departments.add(new Department(resultSet.getInt("id"), resultSet.getString("name")));
                  System.out.println("---------00000000005555550------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public static void main(String a[]) throws Exception {
        EmployeeBean d = new EmployeeBean();
      ///  d.loadDepartment();
        d.loadEmployees();
    //    d.addEmployee();
    }
}
