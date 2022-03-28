package com.epam.command.implementation;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.Item;
import com.epam.entity.Medicine;
import com.epam.entity.Order;
import com.epam.entity.User;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.ItemService;
import com.epam.service.OrderService;

public class ShowOrder implements Command{

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.ORDER_DETAIL_PAGE_KEY.getPageName());
		/*Хочу увидеть итемы ордера!!!*/
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		
		ItemService itemService = new ItemService(new TransactionFactory());
		List<Item> orderedItems = itemService.findItemsByOrderId(orderId);
		
		/*Посчитаем общую сумму:
		 *
		 * */
		int orderTotalPrice = 0;
		for (int i = 0; i < orderedItems.size(); i++) {
			Item currentItem = orderedItems.get(i);
			Medicine currentMedicine = currentItem.getAssociatedMedicine();
			int medicinePrice = currentMedicine.getPrice();
			int quntity = currentItem.getQuantity();
			orderTotalPrice += quntity * medicinePrice;
		}
		
		request.getSession().setAttribute("itemsForBoughtOrder", orderedItems);
		request.getSession().setAttribute("orderTotalPrice", orderTotalPrice);
		
		commandResult = new CommandResult(page, NavigationType.REDIRECT);
		return commandResult;
	}

}
