package com.epam.command.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.dto.ReceiptDto;
import com.epam.dto.dtobuilder.BuildReceiptDto;
import com.epam.entity.Receipt;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.ReceiptService;

public class ShowAllReceipts implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		
		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.DOCTOR_MAIN_PAGE_KEY.getPageName());
		
		ReceiptService receiptService = new ReceiptService(new TransactionFactory());
		
        List<Receipt> receiptsFromDatabase = receiptService.getAllRequstedReceipts();
        List<ReceiptDto> receiptsForOutput = new ArrayList<>();
        for (int i = 0; i < receiptsFromDatabase.size(); i++) {
        	Receipt receipt = receiptsFromDatabase.get(i);
        	ReceiptDto receiptForOutput = BuildReceiptDto.BuildReceiptDtoFromReceipt(receipt);
        	receiptsForOutput.add(receiptForOutput);
        }
		
        request.getSession().setAttribute("receipts", receiptsForOutput);
        commandResult = new CommandResult(page, NavigationType.REDIRECT);
		return commandResult;
	}

}
