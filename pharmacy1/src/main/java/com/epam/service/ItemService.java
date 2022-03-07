package com.epam.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.dao.ItemDaoImpl;
import com.epam.dao.MedicineDaoImpl;
import com.epam.dao.OrderDaoImpl;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.dao.transaction.TransactionManager;
import com.epam.entity.Item;
import com.epam.entity.Medicine;
import com.epam.entity.Order;
import com.epam.entity.User;
import com.epam.exception.ServiceException;

public class ItemService {

	private TransactionFactory transactionFactory;
	
	public ItemService(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}
	
	public void saveItemIntoDatabase(Item item) throws ServiceException {
		try(TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			ItemDaoImpl dao = currentTransaction.createItemDao();
			dao.save(item);
			currentTransaction.endTransaction();
		} catch (SQLException e) {
			throw new ServiceException();
		}
	}
	
	public List<Item> findAllItemsForUser(User user) throws ServiceException {
		List<Item> itemList = new ArrayList<Item>();
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			
			ItemDaoImpl dao = currentTransaction.createItemDao();
			OrderDaoImpl orderDao = currentTransaction.createOrderDao();
			MedicineDaoImpl medicineDao = currentTransaction.createMedicineDao();
			
			Optional<Order> userOrder = orderDao.findByUserId(user);
			if (userOrder.isPresent()) {
				itemList = dao.findByOrderId(userOrder.get());
			}
			for (int i = 0; i < itemList.size(); i++) {
				Item particularItem = itemList.get(i);
				
				int medicineId = particularItem.getAssociatedMedicine().getId();
				Optional<Medicine> medicine = medicineDao.findById(medicineId);
				
				particularItem.setAssociatedOrder(userOrder.get());
				particularItem.setAssociatedMedicine(medicine.get());
				System.out.println();
				
			}
			currentTransaction.endTransaction();
		} catch (SQLException e) {
			throw new ServiceException();
		}
		return itemList;
	}
}
