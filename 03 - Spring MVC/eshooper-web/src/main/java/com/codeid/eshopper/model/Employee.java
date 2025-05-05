package com.codeid.eshopper.model;

import java.time.LocalDate;
import java.util.Random;

public class Employee {
    private int empId;

    private String fullName;

    private LocalDate hireDate;

    private double salary;

    public Employee() {
    }

    public Employee(String fullName, LocalDate hireDate, double salary) {
        Random random = new Random();
        this.empId = random.nextInt(101, 111);
        this.fullName = fullName;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
