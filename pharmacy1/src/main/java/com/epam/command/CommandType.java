package com.epam.command;

import com.epam.command.implementation.LoginCommand;
import com.epam.command.implementation.LogoutCommand;
import com.epam.command.implementation.RegistrationCommand;

public enum CommandType {
	LOGIN(new LoginCommand()),
	REGISTRATION (new RegistrationCommand()),
	LOGOUT(new LogoutCommand());
	
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }
}
