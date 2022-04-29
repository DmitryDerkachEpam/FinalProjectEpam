package com.epam.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.User;
import com.epam.exception.ServiceException;
import com.epam.service.UserService;

public class UserValidator {

	private static final String LOGIN_REG_EXP = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private static final String PASSWORD_REG_EXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}";

	/*
	 * 
	 * ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$
	 * 
	 *  ^                 # start-of-string
		(?=.*[0-9])       # a digit must occur at least once
		(?=.*[a-z])       # a lower case letter must occur at least once
		(?=.*[A-Z])       # an upper case letter must occur at least once
		(?=.*[@#$%^&+=])  # a special character must occur at least once
		(?=\S+$)          # no whitespace allowed in the entire string
		.{8,}             # anything, at least eight places though
		$                 # end-of-string
	 * 
	 * */
	
	public static boolean isInputFromLoginFormCorrect(String login, String password) throws ServiceException {
		boolean validationResult = false;
		if (isUserExists(login, password)) {
			validationResult = true;
		}
		return validationResult;
	}
	
	public static boolean isInputFromRegistrationFormCorrect(String login, String password, String name) throws ServiceException {
		boolean validationResult = false;
		if (isValidLogin(login) && isValidPassword(password) && (!isUserExists(login, password, name))) {
			validationResult = true;
		}
		return validationResult;
	}
	
	private static boolean isValidLogin(String login) {
		return login != null && login.matches(LOGIN_REG_EXP);
	}
	
	private static boolean isValidPassword(String password) {
		return password != null && password.matches(PASSWORD_REG_EXP);
	}

	private  static boolean isUserExists(String emailValue, String passwordValue) throws ServiceException {
		boolean result = false;
		UserService userService = new UserService(new TransactionFactory());
		Optional<User> maybeExistingUser = userService.getUserByLoginAndPassword(emailValue, passwordValue);
		if (maybeExistingUser.isPresent()) {
			result = true;
		}
		return result;
	}
	
	private  static boolean isUserExists(String emailValue, String passwordValue, String nameValue) throws ServiceException {
		boolean result = false;
		UserService userService = new UserService(new TransactionFactory());
		Optional<User> maybeExistingUser = userService.getUserByLoginAndPassword(emailValue, passwordValue);
		if (maybeExistingUser.isPresent()) {
			result = true;
		} else {
			Optional<User> user = userService.getUserByName(nameValue);
			if (user.isPresent()) {
				result = true;
			}
		}
		return result;
	}
	
}

