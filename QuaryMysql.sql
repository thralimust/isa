
create database Ass;
-- Create Departments Table
CREATE TABLE departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Insert sample departments
INSERT INTO departments (name) VALUES ('HR'), ('IT'), ('Finance');

-- Create Employees Table
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

create database db_ass;

SELECT e.id, e.name, e.gender, d.name AS department_name FROM employees e JOIN departments d ON e.department_id = d.id