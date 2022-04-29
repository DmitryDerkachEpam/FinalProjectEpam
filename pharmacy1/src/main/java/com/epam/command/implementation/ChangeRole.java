package com.epam.command.implementation;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.UserService;

public class ChangeRole implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		
		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.ADMIN_MAIN_PAGE_KEY.getPageName());
		
		String roleValue = request.getParameter("role");
		String userId = request.getParameter("userId");
		
		UserService userService = new UserService(new TransactionFactory());
		userService.changeUserRoleById(roleValue, userId);
		
		
		commandResult = new ShowAllUsers().execute(request);
		return commandResult;
	}

}
