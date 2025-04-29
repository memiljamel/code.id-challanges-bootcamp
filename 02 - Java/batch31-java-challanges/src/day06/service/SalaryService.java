package day06.service;

import day06.constant.Status;
import day06.employee.Employee;

import java.util.List;

public interface SalaryService {

    void generateSalary(List<Employee> employees);

    void getTotalAllowances(List<Employee> employees);

    void getTotalTax(List<Employee> employees);

    void getTotalSalaryByType(List<Employee> employees, Status status);
}
