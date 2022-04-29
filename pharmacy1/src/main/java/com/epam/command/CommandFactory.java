package com.epam.command;

public class CommandFactory {

    public static Command defineCommand(String commandName) {
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        return commandType.getCommand();
    }
	
}
