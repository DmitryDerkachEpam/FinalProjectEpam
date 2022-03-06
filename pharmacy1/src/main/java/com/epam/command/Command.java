package com.epam.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import com.epam.exception.CommandException;
import com.epam.exception.LoginException;
import com.epam.exception.ServiceException;

public interface Command {

	CommandResult execute (HttpServletRequest request) throws ServiceException, LoginException;
	
}
