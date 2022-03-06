package com.epam.command.implementation;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.exception.LoginException;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.MedicineService;

public class ShowAllMedicines implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException, LoginException {
		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.USER_MAIN_PAGE_KEY.getPageName());
		
		MedicineService medicineService = new MedicineService(new TransactionFactory());
		request.getSession().setAttribute("medicines", medicineService.getAllMedicinesFromDatabase());
		
	    commandResult = new CommandResult(page, NavigationType.REDIRECT);
	    return commandResult;
	}

}
