package com.epam.campus.console;

import java.util.Map;

public class ConsoleMenuHandler {
    private final Map<Integer, MenuCommand> commands;
    private final InputHandler inputHandler;
    private boolean running;

    public ConsoleMenuHandler() {
        this.inputHandler = new InputHandler();
        MenuCommandFactory factory = new EmployeeMenuCommandFactory(inputHandler);
        this.commands = factory.createCommands();
        this.running = true;
    }

    public void start() {
        while (running) {
            displayMenu();
            int choice = inputHandler.getIntegerInput("Enter your choice (1-" + commands.size() + "): ",
                    1, commands.size());
            executeCommand(choice);
        }
    }

    private void displayMenu() {
        System.out.println("\n=== Employee Management System ===");
        commands.forEach((key, command) ->
                System.out.println(key + ". " + command.getDescription()));
        System.out.println("================================");
    }

    private void executeCommand(int choice) {
        try {
            MenuCommand command = commands.get(choice);
            if (command != null) {
                command.execute();
                if (choice == commands.size()) { // Exit command
                    running = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (running) {
            inputHandler.waitForEnter();
        }
    }

    public static void main(String[] args) {
        ConsoleMenuHandler menu = new ConsoleMenuHandler();
        menu.start();
    }
}
