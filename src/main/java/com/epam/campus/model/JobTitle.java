package com.epam.campus.model;

public enum JobTitle {
    INTERN(0.0, 10000.0),
    JUNIOR(10000.0, 30000.0),
    SENIOR(30000.0, 60000.0),
    LEAD(50000.0, 80000.0),
    MANAGER(70000.0, 100000.0),
    HEAD(90000.0, 150000.0);

    private final double minSalary;
    private final double maxSalary;

    JobTitle(double minSalary, double maxSalary) {
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public double getMinSalary() {
        return minSalary;
}
public double getMaxSalary() {
    return maxSalary;
    }
}
