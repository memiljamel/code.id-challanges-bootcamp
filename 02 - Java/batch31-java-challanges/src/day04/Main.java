package day04;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EmployeeInterface employee = new EmployeeImplementation();

        List<Employee> employees = employee.initListEmployee();
        employee.generateSalary(employees);
        employee.displayEmployee(employees);
    }
}
