package day06;

import day06.allowance.Insurance;
import day06.allowance.Operational;
import day06.allowance.Overtime;
import day06.constant.Status;
import day06.employee.*;
import day06.service.HRService;

import java.time.LocalDate;
import java.util.List;

public class HRServiceImpl implements HRService {

    @Override
    public List<Employee> initEmployeeData() {
        Permanent anton = new Permanent(
                100,
                "Anton",
                LocalDate.of(2020, 4, 2),
                Status.PERMANENT,
                2_000_000,
                List.of(
                        new Insurance(LocalDate.of(2025, 4, 30), 1, 2, 0.02),
                        new Overtime(LocalDate.of(2025, 4, 30), 10, 50_000),
                        new Operational(LocalDate.of(2025, 4, 30), 2, 30_000, 20_000)
                ),
                0.005,
                0.005
        );

        Permanent budi = new Permanent(
                101,
                "Budi",
                LocalDate.of(2021, 4, 2),
                Status.PERMANENT,
                1_500_000,
                List.of(
                        new Insurance(LocalDate.of(2025, 4, 30), 1, 3, 0.02),
                        new Overtime(LocalDate.of(2025, 4, 30), 5, 50_000)
                ),
                0.005,
                0.005
        );

        Contract charlie = new Contract(
                102,
                "Charlie",
                LocalDate.of(2022, 4, 2),
                Status.CONTRACT,
                1_500_000,
                List.of(
                        new Insurance(LocalDate.of(2025, 4, 30), 1, 0, 0.02),
                        new Overtime(LocalDate.of(2025, 4, 30), 5, 45_000)
                ),
                0.005,
                0.005
        );

        Contract dian = new Contract(
                103,
                "Dian",
                LocalDate.of(2023, 4, 2),
                Status.CONTRACT,
                1_000_000,
                List.of(
                        new Insurance(LocalDate.of(2025, 4, 30), 1, 0, 0.02),
                        new Overtime(LocalDate.of(2025, 4, 30), 6, 45_000)
                ),
                0.005,
                0.005
        );

        Trainee gita = new Trainee(
                104,
                "Gita",
                LocalDate.of(2024, 4, 2),
                Status.TRAINEE,
                0,
                List.of(
                        new Operational(LocalDate.of(2025, 4, 30), 25, 30_000, 20_000)
                ),
                0.0,
                0.0
        );

        Freelancer rima = new Freelancer(
                105,
                "Rima",
                LocalDate.of(2025, 4, 2),
                Status.FREELANCER,
                0,
                2_500_000,
                0.025
        );

        return List.of(anton, budi, charlie, dian, gita, rima);
    }

    @Override
    public void getTotalEmployee(List<Employee> employees) {
        System.out.println("Total Employee: " + employees.size());
    }

    @Override
    public void getTotalEmployeeByType(List<Employee> employees, Status status) {
        int totalEmployee = 0;

        for (Employee employee : employees) {
            if (employee.getStatus().equals(status)) {
                totalEmployee++;
            }
        }

        System.out.println("Total Employee By " + status + ": " + totalEmployee);
    }
}
