package com.epam.command.implementation;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;

public class LogoutCommand implements Command{

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		String page = request.getContextPath() + PageManager.getValue(PageMapper.LOGOUT_PAGE_KEY.getPageName());
		request.getSession().invalidate();
		return new CommandResult(page, NavigationType.REDIRECT);
		
	}

}
