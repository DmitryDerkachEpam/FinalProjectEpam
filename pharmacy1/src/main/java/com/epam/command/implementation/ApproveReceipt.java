package com.epam.command.implementation;

import javax.servlet.http.HttpServletRequest;
import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.exception.InformationalMessages;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.ReceiptService;

public class ApproveReceipt implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.DOCTOR_MAIN_PAGE_KEY.getPageName());
		
		int receiptId = Integer.parseInt(request.getParameter("receiptId"));
		
		ReceiptService receiptService = new ReceiptService(new TransactionFactory());
		receiptService.changeReceiptStateToApproved(receiptId);
		
		commandResult = new ShowAllReceipts().execute(request);
        
		request.getSession().setAttribute("message", InformationalMessages.RECEIPT_STATE_CHANGED.getMessage());
		request.getSession().setAttribute("changedReceipt", receiptId);
		
		commandResult = new CommandResult(page, NavigationType.REDIRECT);
		return commandResult;
	}

}
