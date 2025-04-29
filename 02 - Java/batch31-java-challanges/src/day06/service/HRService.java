package day06.service;

import day06.constant.Status;
import day06.employee.Employee;

import java.util.List;

public interface HRService {

    List<Employee> initEmployeeData();

    void getTotalEmployee(List<Employee> employees);

    void getTotalEmployeeByType(List<Employee> employees, Status status);
}
