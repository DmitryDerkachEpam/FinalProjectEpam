package com.epam.command.implementation;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.Order;
import com.epam.entity.User;
import com.epam.exception.LoginException;
import com.epam.exception.ServiceException;
import com.epam.service.OrderService;
import com.epam.service.UtilService;

public class PayForCart implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException, LoginException {
		CommandResult commandResult;
		
		UtilService utilService = new UtilService(new TransactionFactory());
		OrderService orderService = new OrderService(new TransactionFactory());
		User user = (User) request.getSession().getAttribute("user");
		
		boolean result = utilService.isItPossibleToBuy(user);
		if (result) {
			request.getSession().setAttribute("paymentResult", "Purchase successful!");
			/*Поменять статус текущего ордера + создать новый ордер, если человек решить продложить покупки без логаутаorder-а*/
			orderService.chanageOrderStateByUserId(user);
        	Order userOrder = new Order();
        	userOrder.setAssociatedUser(user);
         	orderService.createOrder(userOrder);
        	request.getSession().setAttribute("order", userOrder);		
		} else {
			request.getSession().setAttribute("paymentResult", "Shopping cart contains medecine for which a receipt has not been received or approved!");
		}
		
		ShowUserCart showUserCart = new ShowUserCart();
		commandResult = showUserCart.execute(request);
		return commandResult;
	}

}
