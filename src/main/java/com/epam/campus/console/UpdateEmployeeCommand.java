package com.epam.campus.console;

import com.epam.campus.model.Employee;
import com.epam.campus.service.repository.EmployeeService;

public class UpdateEmployeeCommand implements MenuCommand {
    private final EmployeeService employeeService;
    private final InputHandler inputHandler;
    private final EmployeeDisplayFormatter formatter;

    public UpdateEmployeeCommand(EmployeeService employeeService, InputHandler inputHandler) {
        this.employeeService = employeeService;
        this.inputHandler = inputHandler;
        this.formatter = new EmployeeDisplayFormatter();
    }

    @Override
    public void execute() {
        String empId = inputHandler.getStringInput("Enter Employee ID to update: ");
        try {
            Employee employee = employeeService.get(empId);
            System.out.println("\nCurrent Employee Details:");
            System.out.println(formatter.format(employee));

            System.out.println("\nEnter new details (press Enter to keep current value):");

            String input = inputHandler.getStringInput("First Name: ");
            if (!input.isEmpty()) employee.setFirstName(input);

            input = inputHandler.getStringInput("Last Name: ");
            if (!input.isEmpty()) employee.setLastName(input);

            input = inputHandler.getStringInput("Email: ");
            if (!input.isEmpty()) employee.setEmail(input);

            input = inputHandler.getStringInput("Phone Number: ");
            if (!input.isEmpty()) employee.setPhoneNumber(input);

            if (inputHandler.getConfirmation("Update Department?")) {
                employee.setDepartment(inputHandler.getDepartment());
            }

            if (inputHandler.getConfirmation("Update Job Title?")) {
                employee.setJobTitle(inputHandler.getJobTitle());
            }

            if (inputHandler.getConfirmation("Update Base Salary?")) {
                employee.setBaseSalary(inputHandler.getDoubleInput("Enter new Base Salary: "));
            }

            employeeService.update(employee);
            System.out.println("\nEmployee updated successfully!");
            System.out.println(formatter.format(employee));

        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Update Employee";
    }
}
