package com.epam.campus.model;

import java.util.HashSet;
import java.util.Set;

public class Department {

    protected Integer departmentCode;
    protected DepartmentTypes departmentName;
    protected Set<Employee> employees;


    public Department(Integer departmentCode, DepartmentTypes departmentName) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
    }

    public Integer getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Integer departmentCode) {
        this.departmentCode = departmentCode;
    }

    public DepartmentTypes getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(DepartmentTypes departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        if (employees == null) {
            employees = new HashSet<>();
        }
        employees.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        if (employees != null) {
            employees.remove(employee);
            if (employee.getDepartment() == this) {
                employee.setDepartment(null);
            }
        }
    }
}
