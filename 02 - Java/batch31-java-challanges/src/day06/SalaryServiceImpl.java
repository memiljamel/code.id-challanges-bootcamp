package day06;

import day06.allowance.Allowance;
import day06.allowance.Insurance;
import day06.allowance.Operational;
import day06.allowance.Overtime;
import day06.constant.Status;
import day06.employee.*;
import day06.service.SalaryService;

import java.util.List;

public class SalaryServiceImpl implements SalaryService {

    @Override
    public void generateSalary(List<Employee> employees) {
        for (Employee employee : employees) {
            if (employee instanceof Permanent permanent) {
                permanent.calcPph();
                permanent.calcTapera();
                permanent.calcTotalSalary();
            } else if (employee instanceof Contract contract) {
                contract.calcPph();
                contract.calcTapera();
                contract.calcTotalSalary();
            } else if (employee instanceof Trainee trainee) {
                trainee.calcPph();
                trainee.calcTapera();
                trainee.calcTotalSalary();
            } else if (employee instanceof Freelancer freelancer) {
                freelancer.calcPpn();
                freelancer.totalPO();
            }
        }
    }

    @Override
    public void getTotalAllowances(List<Employee> employees) {
        double totalInsurance = 0;
        double totalOvertime = 0;
        double totalOperational = 0;

        for (Employee employee : employees) {
            if (employee instanceof Permanent permanent) {
                for (Allowance allowance : permanent.getAllowances()) {
                    if (allowance instanceof Overtime overtime) {
                        totalOvertime += overtime.getTotalAllowance();
                    } else if (allowance instanceof Operational operational) {
                        totalOperational += operational.getTotalAllowance();
                    } else if (allowance instanceof Insurance insurance) {
                        totalInsurance += insurance.getTotalAllowance();
                    }
                }
            } else if (employee instanceof Contract contract) {
                for (Allowance allowance : contract.getAllowances()) {
                    if (allowance instanceof Overtime overtime) {
                        totalOvertime += overtime.getTotalAllowance();
                    } else if (allowance instanceof Operational operational) {
                        totalOperational += operational.getTotalAllowance();
                    } else if (allowance instanceof Insurance insurance) {
                        totalInsurance += insurance.getTotalAllowance();
                    }
                }
            } else if (employee instanceof Trainee trainee) {
                for (Allowance allowance : trainee.getAllowances()) {
                    if (allowance instanceof Overtime overtime) {
                        totalOvertime += overtime.getTotalAllowance();
                    } else if (allowance instanceof Operational operational) {
                        totalOperational += operational.getTotalAllowance();
                    } else if (allowance instanceof Insurance insurance) {
                        totalInsurance += insurance.getTotalAllowance();
                    }
                }
            }
        }

        System.out.println("Total Insurance: " + totalInsurance);
        System.out.println("Total Overtime: " + totalOvertime);
        System.out.println("Total Operational: " + totalOperational);
    }

    @Override
    public void getTotalTax(List<Employee> employees) {
        double totalTax = 0;

        for (Employee employee : employees) {
            if (employee instanceof Permanent permanent) {
                totalTax += (permanent.getPph() + permanent.getTapera());
            } else if (employee instanceof Contract contract) {
                totalTax += (contract.getPph() + contract.getTapera());
            } else if (employee instanceof Trainee trainee) {
                totalTax += (trainee.getPph() + trainee.getTapera());
            } else if (employee instanceof Freelancer freelancer) {
                totalTax += freelancer.getPpn();
            }
        }

        System.out.println("Total Tax: " + totalTax);
    }

    @Override
    public void getTotalSalaryByType(List<Employee> employees, Status status) {
        double totalSalary = 0;

        for (Employee employee : employees) {
            if (employee.getStatus().equals(status)) {
                totalSalary += employee.getTotalSalary();
            }
        }

        System.out.println("Total Salary By " + status + ": " + totalSalary);
    }
}
