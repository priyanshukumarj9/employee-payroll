package com.epam.campus.console;

import com.epam.campus.model.Employee;
import com.epam.campus.service.repository.EmployeeService;

import java.util.Date;

public class AddEmployeeCommand implements MenuCommand {
    private final EmployeeService employeeService;
    private final InputHandler inputHandler;
    private final EmployeeDisplayFormatter formatter;

    public AddEmployeeCommand(EmployeeService employeeService, InputHandler inputHandler) {
        this.employeeService = employeeService;
        this.inputHandler = inputHandler;
        this.formatter = new EmployeeDisplayFormatter();
    }

    @Override
    public void execute() {
        String firstName = inputHandler.getStringInput("Enter First Name: ");
        String lastName = inputHandler.getStringInput("Enter Last Name: ");
        String email = inputHandler.getStringInput("Enter Email: ");
        String phoneNumber = inputHandler.getStringInput("Enter Phone Number: ");

        Employee employee = new Employee.EmployeeBuilder(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setDepartment(inputHandler.getDepartment())
                .setJobTitle(inputHandler.getJobTitle())
                .setBaseSalary(inputHandler.getDoubleInput("Enter Base Salary: "))
                .setJoiningDate(new Date())
                .build();

        employeeService.add(employee);
        System.out.println("\nEmployee added successfully!");
        System.out.println(formatter.format(employee));
    }

    @Override
    public String getDescription() {
        return "Add New Employee";
    }
}
