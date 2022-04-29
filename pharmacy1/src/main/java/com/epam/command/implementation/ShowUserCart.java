package com.epam.command.implementation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.Item;
import com.epam.entity.Medicine;
import com.epam.entity.User;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.ItemService;

public class ShowUserCart implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.USER_SHOPPING_CART_PAGE_KEY.getPageName());
		User user = (User) request.getSession().getAttribute("user");
		
		ItemService itemService = new ItemService(new TransactionFactory());
		List<Item> itemList = itemService.findAllNonBoughtItemsForUser(user);
		
		int orderTotalPrice = 0;
		for (int i = 0; i < itemList.size(); i++) {
			Item currentItem = itemList.get(i);
			Medicine currentMedicine = currentItem.getAssociatedMedicine();
			int medicinePrice = currentMedicine.getPrice();
			int quntity = currentItem.getQuantity();
			orderTotalPrice += quntity * medicinePrice;
		}
		
		request.getSession().setAttribute("items", itemList);
		request.getSession().setAttribute("totalPrice", orderTotalPrice);
		
		commandResult = new CommandResult(page, NavigationType.REDIRECT);
		
		return commandResult;
	}

}
