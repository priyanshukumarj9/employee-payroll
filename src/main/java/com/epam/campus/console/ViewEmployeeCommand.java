package com.epam.campus.console;

import com.epam.campus.model.Employee;
import com.epam.campus.service.repository.EmployeeService;

public class ViewEmployeeCommand implements MenuCommand {
    private final EmployeeService employeeService;
    private final InputHandler inputHandler;
    private final EmployeeDisplayFormatter formatter;

    public ViewEmployeeCommand(EmployeeService employeeService, InputHandler inputHandler) {
        this.employeeService = employeeService;
        this.inputHandler = inputHandler;
        this.formatter = new EmployeeDisplayFormatter();
    }

    @Override
    public void execute() {
        String empId = inputHandler.getStringInput("Enter Employee ID: ");
        try {
            Employee employee = employeeService.get(empId);
            System.out.println(formatter.format(employee));
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "View Employee Details";
    }
}
