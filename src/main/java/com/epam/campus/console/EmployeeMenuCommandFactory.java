package com.epam.campus.console;

import com.epam.campus.db.dao.EmployeeDAO;
import com.epam.campus.service.payroll.PayrollService;
import com.epam.campus.service.repository.EmployeeService;

import java.util.LinkedHashMap;
import java.util.Map;

public class EmployeeMenuCommandFactory implements MenuCommandFactory {
    private final EmployeeService employeeService;
    private final PayrollService payrollService;
    private final InputHandler inputHandler;

    public EmployeeMenuCommandFactory(InputHandler inputHandler) {
        EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
        this.employeeService = new EmployeeService(employeeDAO);
        this.payrollService = new PayrollService(employeeDAO);
        this.inputHandler = inputHandler;
    }

    @Override
    public Map<Integer, MenuCommand> createCommands() {
        Map<Integer, MenuCommand> commands = new LinkedHashMap<>();
        commands.put(1, new AddEmployeeCommand(employeeService, inputHandler));
        commands.put(2, new ViewEmployeeCommand(employeeService, inputHandler));
        commands.put(3, new UpdateEmployeeCommand(employeeService, inputHandler));
        commands.put(4, new DeleteEmployeeCommand(employeeService, inputHandler));
        commands.put(5, new GeneratePayrollCommand(payrollService, employeeService, inputHandler));
        commands.put(6, new ListAllEmployeesCommand(employeeService));
        commands.put(7, new ExitCommand());
        return commands;
    }
}