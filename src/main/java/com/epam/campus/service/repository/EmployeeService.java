package com.epam.campus.service.repository;

import com.epam.campus.db.dao.EmployeeDAO;
import com.epam.campus.model.Employee;

public class EmployeeService {

//    private static EmployeeService instance = null;
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


    public void add(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    public Employee get(String empId) {
        return employeeDAO.getEmployee(empId);
    }

    public void update(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

    public void delete(String empId) {
        employeeDAO.removeEmployee(empId);
    }
}
