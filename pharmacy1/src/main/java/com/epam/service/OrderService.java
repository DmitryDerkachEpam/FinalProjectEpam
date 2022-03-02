package com.epam.service;

import java.sql.SQLException;
import com.epam.dao.OrderDaoImpl;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.dao.transaction.TransactionManager;
import com.epam.entity.Order;
import com.epam.exception.ServiceException;

public class OrderService {

	private TransactionFactory transactionFactory;
	
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
}


