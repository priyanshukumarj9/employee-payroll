package com.epam.campus.service.payroll;

import com.epam.campus.model.DepartmentTypes;
import com.epam.campus.model.Employee;

public class PayableFactory {
    public static Payable getSalaryStrategy(Employee employee) {
        if (employee.getDepartment().getDepartmentName() == DepartmentTypes.IT) {
            return new ITDepartmentSalary();
        } else if (employee.getDepartment().getDepartmentName() == DepartmentTypes.HR) {
            return new HRDepartmentSalary();
        } else if (employee.getDepartment().getDepartmentName() == DepartmentTypes.FINANCE) {
            return new FinanceDepartmentSalary();
        }
        return new DefaultSalaryStrategy(); // Fallback strategy
    }
}
