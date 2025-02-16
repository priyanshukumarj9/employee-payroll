package com.epam.campus.console;

import com.epam.campus.model.Department;
import com.epam.campus.model.DepartmentTypes;
import com.epam.campus.model.JobTitle;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public Department getDepartment() {
        System.out.println("Available Departments:");
        DepartmentTypes[] departments = DepartmentTypes.values();
        for (int i = 0; i < departments.length; i++) {
            System.out.println((i + 1) + ". " + departments[i]);
        }

        int choice = getIntegerInput("Select department (1-" + departments.length + "): ", 1, departments.length);
        return new Department(choice, departments[choice - 1]);
    }

    public JobTitle getJobTitle() {
        System.out.println("Available Job Titles:");
        JobTitle[] titles = JobTitle.values();
        for (int i = 0; i < titles.length; i++) {
            System.out.println((i + 1) + ". " + titles[i]);
        }

        int choice = getIntegerInput("Select job title (1-" + titles.length + "): ", 1, titles.length);
        return titles[choice - 1];
    }

    public int getIntegerInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public boolean getConfirmation(String prompt) {
        System.out.print(prompt + " (y/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }

    public void waitForEnter() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}