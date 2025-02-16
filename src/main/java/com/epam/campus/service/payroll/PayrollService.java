package com.epam.campus.service.payroll;

import com.epam.campus.db.dao.EmployeeDAO;
import com.epam.campus.model.Department;
import com.epam.campus.model.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PayrollService {
    private final EmployeeDAO employeeDAO;

    public PayrollService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public double generatePayroll(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        Payable payable = PayableFactory.getSalaryStrategy(employee);
        return payable.calculateSalary(employee);
    }

    // Generate payroll for all employees in a department
    public Map<String, Double> generatePayrollByDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }

        List<Employee> employees = employeeDAO.getEmployeesByDepartment(department);
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees found in department: " + department.getDepartmentName());
            return Collections.emptyMap();
        }

        return employees.stream()
                .collect(Collectors.toMap(
                        Employee::getEmpId, // Ensure this method exists in Employee class
                        this::generatePayroll
                ));
    }

    public Map<String, Double> generatePayrollForAllEmployees() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees found.");
            return Collections.emptyMap();
        }

        return employees.stream()
                .collect(Collectors.toMap(
                        Employee::getEmpId,
                        this::generatePayroll
                ));
    }
}
