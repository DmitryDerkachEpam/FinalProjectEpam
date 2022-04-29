package com.epam.service;

import java.sql.SQLException;
import java.util.List;
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
	
	public Optional<Order> findActualOrderByUserId (User user) throws ServiceException {
		try(TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			OrderDaoImpl dao = currentTransaction.createOrderDao();
			Optional<Order> maybeExsitingOrder = dao.findActualOrderByUserId(user);
			if (maybeExsitingOrder.isPresent()) {
				maybeExsitingOrder.get().setAssociatedUser(user);
			}
			currentTransaction.endTransaction();
			return maybeExsitingOrder;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Order> findUsedOrdersByUserId(User user) throws ServiceException {
		try(TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			OrderDaoImpl dao = currentTransaction.createOrderDao();
			List<Order> listOfUserdOrders = dao.findUsedOrdersByUserId(user);
			for (int i = 0; i < listOfUserdOrders.size(); i++) {
				Order order = listOfUserdOrders.get(i);
				order.setAssociatedUser(user);
			}
			currentTransaction.endTransaction();
			return listOfUserdOrders;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
		
	}

	public void changeItemQuantity(int quantity, int orderId, int medId) throws ServiceException {
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			OrderDaoImpl dao = currentTransaction.createOrderDao();
			dao.updateItemQuantity(quantity, orderId, medId);
			currentTransaction.endTransaction();
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	public void chanageOrderStateByUserId(User user) throws ServiceException {
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			OrderDaoImpl dao = currentTransaction.createOrderDao();
			int userId = user.getId();
			dao.changeOrderStateByUserId(userId);
			currentTransaction.endTransaction();
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
		
	}

}


