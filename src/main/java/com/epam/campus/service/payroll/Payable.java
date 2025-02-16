package com.epam.campus.service.payroll;

import com.epam.campus.model.Employee;

public interface Payable {
        double calculateSalary(Employee employee);
}
