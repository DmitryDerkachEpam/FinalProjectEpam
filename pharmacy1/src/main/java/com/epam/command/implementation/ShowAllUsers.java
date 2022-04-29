package com.epam.command.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.roles.UserRole;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.UserService;

public class ShowAllUsers implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.ADMIN_MAIN_PAGE_KEY.getPageName());
		
		UserService userService = new UserService(new TransactionFactory());
		
		request.getSession().setAttribute("users", userService.getAllUsersFromDatabase());
		
		UserRole[] userRole = UserRole.values();
		List<UserRole> roles = new ArrayList<>();
		
		for (int i = 0; i < userRole.length; i++) {
			roles.add(userRole[i]);
		}
		
		request.getSession().setAttribute("roles", roles);
		
		commandResult = new CommandResult(page, NavigationType.FORWARD);
		return commandResult;
	}

}
