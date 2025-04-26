package day04;

import java.time.LocalDate;
import java.util.List;

public class EmployeeImplementation implements EmployeeInterface {

    @Override
    public List<Employee> initListEmployee() {

        Programmer anton = new Programmer(
                120,
                "Anton Pratama",
                Role.Programmer,
                6_000_000L,
                new Transport(LocalDate.of(2025, 1, 31), 500_000, 500_000, 200_000),
                new Overtime(LocalDate.of(2025, 1, 31), 100_000)
        );

        Programmer budi = new Programmer(
                121,
                "Budi Junaedi",
                Role.Programmer,
                6_000_000L,
                new Transport(LocalDate.of(2025, 1, 31), 500_000, 500_000, 200_000),
                new Overtime(LocalDate.of(2025, 1, 31), 100_000)
        );

        Programmer charlie = new Programmer(
                122,
                "Charlie Van Dijk",
                Role.Programmer,
                6_000_000L,
                new Transport(LocalDate.of(2025, 1, 31), 500_000, 500_000, 200_000),
                new Overtime(LocalDate.of(2025, 1, 31), 100_000)
        );

        Sales dian = new Sales(
                123,
                "Dian Permana",
                Role.Sales,
                3_000_000L,
                new Commission(LocalDate.of(2025, 1, 31), 500_000L, 200_000L),
                new Overtime(LocalDate.of(2025, 1, 31), 10_000)
        );

        Sales fatur = new Sales(
                125,
                "Fatur Rohman",
                Role.Sales,
                3_000_000L,
                new Commission(LocalDate.of(2025, 1, 31), 350_000L, 250_000L),
                new Overtime(LocalDate.of(2025, 1, 31), 10_000)
        );

        QA ellise = new QA(
                124,
                "Ellise Toon",
                Role.QA,
                4_500_000L,
                new Overtime(LocalDate.of(2025, 1, 31), 10_000)
        );

        QA gita = new QA(
                126,
                "Gita Gutawa",
                Role.QA,
                4_500_000L,
                new Overtime(LocalDate.of(2025, 1, 31), 10_000)
        );

        return List.of(anton, budi, charlie, dian, fatur, ellise, gita);
    }

    @Override
    public void generateSalary(List<Employee> employees) {
        for (Employee employee : employees) {
            if (employee instanceof SalaryInterface salary) {
                salary.calculateSalary();
            }
        }
    }

    @Override
    public void displayEmployee(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }
}
