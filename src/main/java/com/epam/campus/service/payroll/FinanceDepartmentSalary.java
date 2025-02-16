package com.epam.campus.service.payroll;

import com.epam.campus.model.Employee;

public class FinanceDepartmentSalary implements Payable {

    @Override
    public double calculateSalary(Employee employee) {
        return employee.getBaseSalary() + 1.1;
    }
}

