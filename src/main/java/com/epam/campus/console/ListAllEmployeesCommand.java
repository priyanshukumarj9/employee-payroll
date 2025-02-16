package com.epam.campus.console;

import com.epam.campus.db.dao.EmployeeDAO;
import com.epam.campus.model.DepartmentTypes;
import com.epam.campus.model.Employee;
import com.epam.campus.service.repository.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListAllEmployeesCommand implements MenuCommand {
    private final EmployeeService employeeService;
    private final EmployeeDisplayFormatter formatter;
    private final EmployeeDAO employeeDAO;

    public ListAllEmployeesCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.formatter = new EmployeeDisplayFormatter();
        this.employeeDAO = EmployeeDAO.getInstance();
    }

    @Override
    public void execute() {
        List<Employee> employees = employeeDAO.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees found in the system.");
            return;
        }

        System.out.println("\nEmployee Directory");
        System.out.println("=================");

        // Group employees by department
        Map<DepartmentTypes, List<Employee>> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(emp -> emp.getDepartment().getDepartmentName()));

        employeesByDept.forEach((dept, deptEmployees) -> {
            System.out.println("\n" + dept + " Department");
            System.out.println("-".repeat(20));

            deptEmployees.forEach(emp -> System.out.println(formatter.format(emp)));
        });

        System.out.println("\nSummary");
        System.out.println("=======");
        System.out.println("Total Employees: " + employees.size());
        System.out.println("Departments: " + employeesByDept.size());
        employeesByDept.forEach((dept, deptEmployees) ->
                System.out.printf("%s: %d employees%n", dept, deptEmployees.size()));
    }

    @Override
    public String getDescription() {
        return "List All Employees";
    }
}

