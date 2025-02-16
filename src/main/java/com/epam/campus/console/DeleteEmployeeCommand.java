package com.epam.campus.console;

import com.epam.campus.service.repository.EmployeeService;

public class DeleteEmployeeCommand implements MenuCommand {
    private final EmployeeService employeeService;
    private final InputHandler inputHandler;

    public DeleteEmployeeCommand(EmployeeService employeeService, InputHandler inputHandler) {
        this.employeeService = employeeService;
        this.inputHandler = inputHandler;
    }

    @Override
    public void execute() {
        String empId = inputHandler.getStringInput("Enter Employee ID to delete: ");
        try {
            if (inputHandler.getConfirmation("Are you sure you want to delete this employee?")) {
                employeeService.delete(empId);
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Delete operation cancelled.");
            }
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Delete Employee";
    }
}
