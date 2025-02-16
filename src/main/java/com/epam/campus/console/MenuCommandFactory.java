package com.epam.campus.console;
import java.util.Map;

public interface MenuCommandFactory {
    Map<Integer, MenuCommand> createCommands();
}