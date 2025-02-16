package com.epam.campus.console;

import com.epam.campus.model.Department;
import com.epam.campus.model.Employee;
import com.epam.campus.service.payroll.PayrollService;
import com.epam.campus.service.repository.EmployeeService;

import java.util.Map;

public class GeneratePayrollCommand implements MenuCommand {
    private final PayrollService payrollService;
    private final EmployeeService employeeService;
    private final InputHandler inputHandler;

    public GeneratePayrollCommand(PayrollService payrollService, EmployeeService employeeService,
                                  InputHandler inputHandler) {
        this.payrollService = payrollService;
        this.employeeService = employeeService;
        this.inputHandler = inputHandler;
    }

    @Override
    public void execute() {
        System.out.println("Select Department for Payroll Generation:");
        Department department = inputHandler.getDepartment();

        try {
            Map<String, Double> payroll = payrollService.generatePayrollByDepartment(department);

            if (payroll.isEmpty()) {
                System.out.println("No employees found in selected department.");
                return;
            }

            System.out.println("\nPayroll Report for " + department.getDepartmentName());
            System.out.println("=====================================");

            double totalPayroll = 0.0;
            for (Map.Entry<String, Double> entry : payroll.entrySet()) {
                Employee emp = employeeService.get(entry.getKey());
                System.out.printf("%s %s (ID: %s): $%.2f%n",
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmpId(),
                        entry.getValue()
                );
                totalPayroll += entry.getValue();
            }

            System.out.println("=====================================");
            System.out.printf("Total Department Payroll: $%.2f%n", totalPayroll);
            System.out.printf("Number of Employees: %d%n", payroll.size());
            System.out.printf("Average Salary: $%.2f%n", totalPayroll / payroll.size());

        } catch (RuntimeException e) {
            System.out.println("Error generating payroll: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Generate Payroll by Department";
    }
}
