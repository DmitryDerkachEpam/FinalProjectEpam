package com.epam.command.implementation;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.User;
import com.epam.exception.CommandException;
import com.epam.exception.ExceptionMessage;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.UserService;

public class RegistrationCommand implements Command {
	
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String NAME = "name";
	
	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		 CommandResult commandResult = null;
		 String page;
		 /*Получать данные с JSP*/
		 String emailValue = request.getParameter(EMAIL);
		 String passwordValue =request.getParameter(PASSWORD);
		 String nameValue = request.getParameter(NAME);
		 UserService userService = new UserService(new TransactionFactory());
		 
		 User newUser = new User();
		 newUser.setEmail(emailValue);
		 newUser.setName(nameValue);
		 newUser.setPassword(passwordValue);
		 
		 userService.saveUserIntoDatabase(newUser);
		 
		 page = request.getContextPath() + PageManager.getValue(PageMapper.REGISTRATION_PAGE_KEY.getPageName());
		 commandResult = new CommandResult(page, NavigationType.REDIRECT);
		 return commandResult;
	}

	
}
