package day06;

import day06.constant.Status;
import day06.employee.Employee;
import day06.service.HRService;
import day06.service.SalaryService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        HRService hr = new HRServiceImpl();
        SalaryService salary = new SalaryServiceImpl();

        List<Employee> employees = hr.initEmployeeData();
        salary.generateSalary(employees);

        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

        System.out.println("\n========================Summary========================\n");

        hr.getTotalEmployee(employees);
        hr.getTotalEmployeeByType(employees, Status.PERMANENT);
        hr.getTotalEmployeeByType(employees, Status.CONTRACT);
        hr.getTotalEmployeeByType(employees, Status.TRAINEE);
        hr.getTotalEmployeeByType(employees, Status.FREELANCER);

        salary.getTotalSalaryByType(employees, Status.PERMANENT);
        salary.getTotalSalaryByType(employees, Status.CONTRACT);
        salary.getTotalSalaryByType(employees, Status.TRAINEE);
        salary.getTotalSalaryByType(employees, Status.FREELANCER);
        salary.getTotalAllowances(employees);
        salary.getTotalTax(employees);
    }
}
