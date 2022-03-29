package com.epam.command.implementation;

import javax.servlet.http.HttpServletRequest;
import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.ReceiptService;

public class RequestReceiptProlongation implements Command{

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.USER_RECEIPTS_PAGE_KEY.getPageName());
		
		int receiptId = Integer.parseInt(request.getParameter("receiptDtoId")); 
		
		ReceiptService receiptService = new ReceiptService(new TransactionFactory());
		receiptService.changeReceiptStateToRequested(receiptId);
		
		ShowUserReceipts showUserReceipts = new ShowUserReceipts();
		commandResult = showUserReceipts.execute(request);
		return commandResult;
		
	}

}
