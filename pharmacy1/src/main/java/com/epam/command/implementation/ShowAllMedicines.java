package com.epam.command.implementation;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.MedicineDaoImpl;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.dbconnection.СonnectionManager;
import com.epam.entity.Medicine;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.MedicineService;

public class ShowAllMedicines implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {

		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.USER_MAIN_PAGE_KEY.getPageName());
		
		int currentPage = 1;
		int recordsPerPage = 5;
		
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		MedicineService medicineService = new MedicineService(new TransactionFactory());
		List<Medicine> listOfMedicines = medicineService.getAllMedicineUsingPagination((currentPage - 1) * recordsPerPage, recordsPerPage);
		
		int numOfRecords = medicineService.getTotalNumberOfRecordsInDatabase();
		int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / recordsPerPage); 
		
		request.getSession().setAttribute("medicines", listOfMedicines);
		request.getSession().setAttribute("numOfPages", numOfPages);
		request.getSession().setAttribute("currentPage", currentPage);
		
	    commandResult = new CommandResult(page, NavigationType.REDIRECT);
	    return commandResult;
	    
	}

}
