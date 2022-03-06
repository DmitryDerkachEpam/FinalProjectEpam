package com.epam.validator;

import java.util.Optional;

import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.Order;
import com.epam.entity.User;
import com.epam.exception.ServiceException;
import com.epam.service.OrderService;

public class OrderValidator {

	OrderService orderService = new OrderService(new TransactionFactory());
	
	public OrderValidator() {
		
	}
	
	public boolean isOrderExists(User user) throws ServiceException {
		if (orderService.findOrderByUserId(user).isPresent() ) {
			return true;
		} else {
			return false;
		}	
	}
	
}
