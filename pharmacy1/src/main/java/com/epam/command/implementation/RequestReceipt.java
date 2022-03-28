package com.epam.command.implementation;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.Medicine;
import com.epam.entity.Receipt;
import com.epam.entity.User;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.ReceiptService;

public class RequestReceipt implements Command{

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		CommandResult commandResult;
		String page = null;

		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		String medicineName = request.getParameter("medicineName");
		Integer medicineId = Integer.parseInt(request.getParameter("medicineId"));
		
		Receipt receipt = new Receipt();
		receipt.setAssociatedMedicine(new Medicine(medicineId));
		receipt.setAssociatedUser(new User(userId));
		
		ReceiptService receiptService = new ReceiptService(new TransactionFactory());
		
		Optional<Receipt> maybeExsistingReceipt = receiptService.findReceiptByMedicineNameAndUserId(medicineName, userId);
		
		//Optional<Receipt> maybeExsistingReceipt = receiptService.findReceiptByMedicineIdAndUserId(userId, medicineId);
		if (maybeExsistingReceipt.isPresent()) {
			page = request.getContextPath() + PageManager.getValue(PageMapper.USER_SHOPPING_CART_PAGE_KEY.getPageName());
			request.getSession().setAttribute("messageFromReceiptService", "Receipt was already requested!");
			request.getSession().setAttribute("medId", medicineId);
		} else {
			receiptService.saveReceiptIntoDatabase(receipt);
			page = request.getContextPath() + PageManager.getValue(PageMapper.USER_SHOPPING_CART_PAGE_KEY.getPageName());
			request.getSession().setAttribute("messageFromReceiptService", "Receipt requested!");
			request.getSession().setAttribute("medId", medicineId);
		}
		commandResult = new CommandResult(page, NavigationType.REDIRECT);
		return commandResult;
	}

}
