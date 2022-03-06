package com.epam.service;

import java.sql.SQLException;
import java.util.Optional;

import com.epam.dao.OrderDaoImpl;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.dao.transaction.TransactionManager;
import com.epam.entity.Order;
import com.epam.entity.User;
import com.epam.exception.ServiceException;

public class OrderService {

	private TransactionFactory transactionFactory;
	private UserService userService = new UserService(transactionFactory);
	
	public OrderService(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}

	public void createOrder (Order order) throws ServiceException {
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			OrderDaoImpl dao = currentTransaction.createOrderDao();
			dao.save(order);
			currentTransaction.endTransaction();
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}//createOrder_Tested
	
	public Optional<Order> findOrderByUserId (User user) throws ServiceException {
		try(TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			OrderDaoImpl dao = currentTransaction.createOrderDao();
			Optional<Order> maybeExsitingOrder = dao.findByUserId(user);
			if (maybeExsitingOrder.isPresent()) {
				maybeExsitingOrder.get().setAssociatedUser(user);
			}
			return maybeExsitingOrder;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}
}


