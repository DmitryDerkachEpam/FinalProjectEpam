package com.epam.command.implementation;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.Order;
import com.epam.entity.User;
import com.epam.exception.LoginException;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.ItemService;
import com.epam.service.OrderService;

public class CartReset implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException, LoginException {
		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.USER_SHOPPING_CART_PAGE_KEY.getPageName());
		
		User user = (User) request.getSession().getAttribute("user");
		
		OrderService orderService = new OrderService(new TransactionFactory());
		Optional<Order> order = orderService.findActualOrderByUserId(user);
		
		if (order.isPresent()) {
			
			ItemService itemService = new ItemService(new TransactionFactory());
			itemService.deleteItemsByOrderId(order.get());
			
		}
		
		request.getSession().setAttribute("itemsDeletingResult", "Items was successfully deleted from yours order");
		
		commandResult = new ShowUserCart().execute(request);
		return commandResult; 
	}

}
