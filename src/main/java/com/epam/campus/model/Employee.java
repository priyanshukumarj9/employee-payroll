package com.epam.campus.model;
import java.util.Date;
import java.util.UUID;


public class Employee {
    private final String empId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected Date joiningDate;
    protected Department department;
    protected Double baseSalary;
    protected JobTitle jobTitle;

    public Employee(String empId) {
        this.empId = empId;
    }
    @Override
    public String toString() {
        return "Employee Details:\n" +
                "ID: " + empId + "\n" +
                "Name: " + firstName + " " + lastName + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phoneNumber + "\n" +
                "Department: " + (department != null ? department.getDepartmentName() : "N/A") + "\n" +
                "Job Title: " + (jobTitle != null ? jobTitle : "N/A") + "\n" +
                "Base Salary: " + baseSalary + "\n" +
                "Joining Date: " + (joiningDate != null ? joiningDate : "N/A");
    }

    private Employee(EmployeeBuilder builder) {
        this.empId = UUID.randomUUID().toString(); // ID is assigned when adding to DAO
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.department = builder.department;
        this.baseSalary = builder.baseSalary;
        this.jobTitle = builder.jobTitle;
        this.joiningDate=builder.joiningDate;
    }

    public String getEmpId() {
        return empId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public JobTitle getJobTitleType() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public static class EmployeeBuilder{
        protected String firstName;
        protected String lastName;
        protected String email;
        protected String phoneNumber;
        protected Department department;
        protected Double baseSalary;
        protected JobTitle jobTitle;
        protected Date joiningDate;

        public EmployeeBuilder( String firstName) {
            this.firstName = firstName;
        }

        public EmployeeBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public EmployeeBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public EmployeeBuilder setDepartment(Department department) {
            this.department = department;
            return this;
        }

        public EmployeeBuilder setBaseSalary(Double baseSalary) {
            if (baseSalary == null || baseSalary < 0) {
                throw new IllegalArgumentException("Base salary cannot be null or negative.");
            }
            if (jobTitle != null && (baseSalary < jobTitle.getMinSalary() || baseSalary > jobTitle.getMaxSalary())) {
                throw new IllegalArgumentException("Base salary for " + jobTitle + " must be between " +
                        jobTitle.getMinSalary() + " and " + jobTitle.getMaxSalary());
            }
            this.baseSalary = baseSalary;
            return this;
        }

        public EmployeeBuilder setJobTitle(JobTitle jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public EmployeeBuilder setJoiningDate(Date joiningDate) {
            this.joiningDate = joiningDate;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
