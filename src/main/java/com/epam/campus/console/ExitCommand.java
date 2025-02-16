package com.epam.campus.console;

public class ExitCommand implements MenuCommand {
    @Override
    public void execute() {
        System.out.println("Thank you for using Employee Management System!");
    }

    @Override
    public String getDescription() {
        return "Exit";
    }
}