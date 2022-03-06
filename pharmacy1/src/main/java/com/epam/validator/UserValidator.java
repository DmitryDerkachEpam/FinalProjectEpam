package com.epam.validator;

import java.util.Optional;

import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.User;
import com.epam.exception.ServiceException;
import com.epam.service.UserService;

public class UserValidator {

	private static final String LOGIN_REG_EXP = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private static final String PASSWORD_REG_EXP = "Null";
	
	public static boolean isInputFromLoginCorrect(String login, String password) throws ServiceException {
		boolean validationResult = false;
		if (isValidLogin(login) && isValidPassword(login) && isUserExists(login, password)) {
			validationResult = true;
		}
		return validationResult;
	}
	
	private static boolean isValidLogin(String login) {
		return login != null && login.matches(LOGIN_REG_EXP);
	}
	
	private static boolean isValidPassword(String login) {
		return true;
		/*Вставить в самом конце!*/
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
	
}

