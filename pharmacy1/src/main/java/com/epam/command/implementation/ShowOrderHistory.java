package com.epam.command.implementation;

import java.util.List;
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
import com.epam.service.OrderService;

public class ShowOrderHistory implements Command{

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException, LoginException {
		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.ORDER_HISTORY_PAGE_KEY.getPageName());
		
		User user = (User) request.getSession().getAttribute("user");
		
		OrderService orderService = new OrderService(new TransactionFactory());
		List<Order> usedOrders = orderService.findUsedOrdersByUserId(user);
		
		request.getSession().setAttribute("userOrders", usedOrders);
		
		commandResult = new CommandResult(page, NavigationType.REDIRECT);
		return commandResult;
	}

}
