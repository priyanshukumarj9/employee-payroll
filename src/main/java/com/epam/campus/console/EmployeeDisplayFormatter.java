package com.epam.campus.console;

import com.epam.campus.model.Employee;

public class EmployeeDisplayFormatter implements DisplayFormatter {
    @Override
    public String format(Object obj) {
        if (obj instanceof Employee) {
            Employee emp = (Employee) obj;
            return String.format("""
                =========================================
                Employee ID: %s
                Name: %s %s
                Email: %s
                Phone: %s
                Department: %s
                Job Title: %s
                Base Salary: $%.2f
                =========================================
                """,
                    emp.getEmpId(),
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getEmail(),
                    emp.getPhoneNumber(),
                    emp.getDepartment().getDepartmentName(),
                    emp.getJobTitleType(),
                    emp.getBaseSalary());
        }
        return obj.toString();
    }
}
