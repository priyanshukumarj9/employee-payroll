package com.epam.campus.db.dao;

import com.epam.campus.model.Department;
import com.epam.campus.model.Employee;
import com.epam.campus.model.JobTitle;

import java.util.*;

public class EmployeeDAO {
    private static EmployeeDAO instance = null;
    private final Map<String, Employee> employeeMap = new HashMap<>();
    private final Map<Integer, List<Employee>> departmentMap = new HashMap<>();

    private EmployeeDAO() {}

    public static EmployeeDAO getInstance() {
        if (instance == null) {
            instance = new EmployeeDAO();
        }
        return instance;
    }


    public void addEmployee(Employee employee) {

        if (employeeMap.containsKey(employee.getEmpId())) {
            throw new RuntimeException("Employee with ID " + employee.getEmpId() + " already exists!");
        }

        employeeMap.put(employee.getEmpId(), employee);

        int departmentCode = employee.getDepartment().getDepartmentCode();
        departmentMap.computeIfAbsent(departmentCode, k -> new ArrayList<>()).add(employee);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    public Employee getEmployee(String empId) {
        if (!employeeMap.containsKey(empId)){
            throw new RuntimeException("Employee with ID " + empId + " don't exists!");
        }

        return employeeMap.get(empId);
    }

    public void removeEmployee(String empId) {
        if (!employeeMap.containsKey(empId)) {
            throw new RuntimeException("Employee with ID " + empId + " doesn't exist!");
        }

        // Remove employee from departmentMap
        Employee employee = employeeMap.get(empId);
        int departmentCode = employee.getDepartment().getDepartmentCode();
        List<Employee> departmentEmployees = departmentMap.get(departmentCode);
        if (departmentEmployees != null) {
            departmentEmployees.remove(employee);
        }

        // Remove employee from employeeMap
        employeeMap.remove(empId);
    }

    public void updateEmployee(Employee employee) {
        if (!employeeMap.containsKey(employee.getEmpId())) {
            throw new RuntimeException("Employee with ID " + employee.getEmpId() + " doesn't exist!");
        }

        // Update employee in employeeMap
        employeeMap.put(employee.getEmpId(), employee);

        // Update employee in departmentMap
        int departmentCode = employee.getDepartment().getDepartmentCode();
        List<Employee> departmentEmployees = departmentMap.get(departmentCode);
        if (departmentEmployees != null) {
            departmentEmployees.removeIf(e -> e.getEmpId().equals(employee.getEmpId()));
            departmentEmployees.add(employee);
        }
    }

    public List<Employee> getEmployeesByDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }

        // Return employees from departmentMap
        return departmentMap.getOrDefault(department.getDepartmentCode(), Collections.emptyList());
    }

}
