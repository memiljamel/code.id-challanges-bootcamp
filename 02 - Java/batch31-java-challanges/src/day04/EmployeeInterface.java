package day04;

import java.util.List;

public interface EmployeeInterface {

    List<Employee> initListEmployee();

    void generateSalary(List<Employee> employees);

    void displayEmployee(List<Employee> employees);
}
