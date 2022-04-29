package com.epam.command.implementation;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.User;
import com.epam.exception.ErrorMessages;
import com.epam.exception.InformationalMessages;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.UserService;
import com.epam.validator.UserValidator;

public class RegistrationCommand implements Command {
	
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String NAME = "name";
	
	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		 CommandResult commandResult = null;
		 String page;
		 
		 String emailValue = request.getParameter(EMAIL);
		 String passwordValue =request.getParameter(PASSWORD);
		 String nameValue = request.getParameter(NAME);
		 
		 if (UserValidator.isInputFromRegistrationFormCorrect(emailValue, passwordValue, nameValue)) {
			 
			 UserService userService = new UserService(new TransactionFactory());
			 User newUser = new User();
			 newUser.setEmail(emailValue);
			 newUser.setName(nameValue);
			 newUser.setPassword(passwordValue);
			 
			 userService.saveUserIntoDatabase(newUser);
			 
			 page = request.getContextPath() + PageManager.getValue(PageMapper.LOGIN_PAGE_KEY.getPageName());
			 request.getSession().setAttribute("message", InformationalMessages.REGISTARTION_SUCCESSFUL.getMessage());
		 } else {
     		page = request.getContextPath() + PageManager.getValue(PageMapper.REGISTRATION_PAGE_KEY.getPageName());
        	request.getSession().setAttribute("message", ErrorMessages.REGISTARTION_ERROR.getMessage());
        	commandResult = new CommandResult(page, NavigationType.REDIRECT);
		 }
		
		 commandResult = new CommandResult(page, NavigationType.REDIRECT);
		 return commandResult;
	}

	
}
