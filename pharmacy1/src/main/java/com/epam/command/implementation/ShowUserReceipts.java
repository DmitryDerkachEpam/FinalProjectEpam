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
import com.epam.entity.User;
import com.epam.exception.LoginException;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.ReceiptService;

public class ShowUserReceipts implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException, LoginException {
		CommandResult commandResult;
		
		User user = (User) request.getSession().getAttribute("user");
		ReceiptService receiptService = new ReceiptService(new TransactionFactory());
		List<Receipt> listOfReceipts = receiptService.findReceiptsByUserId(user);
		List<ReceiptDto> listOfReceiptsForOutput = new ArrayList<>();
		for (int i = 0; i < listOfReceipts.size(); i++) {
			Receipt receipt = listOfReceipts.get(i);
			ReceiptDto receiptForOutput = BuildReceiptDto.BuildReceiptDtoFromReceipt(receipt);
			listOfReceiptsForOutput.add(receiptForOutput);
		}
		request.getSession().setAttribute("receiptDtos", listOfReceiptsForOutput);
		
		String page = request.getContextPath() + PageManager.getValue(PageMapper.USER_RECEIPTS_PAGE_KEY.getPageName());
		commandResult = new CommandResult(page, NavigationType.REDIRECT);
		return commandResult;
	}

}
