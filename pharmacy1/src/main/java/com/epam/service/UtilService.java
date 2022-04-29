package com.epam.service;

import java.sql.SQLException;
import com.epam.dao.UtilDaoImpl;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.dao.transaction.TransactionManager;
import com.epam.entity.User;
import com.epam.exception.ServiceException;

public class UtilService {

	private TransactionFactory transactionFactory;
	
	public UtilService(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}
	
	/*Сравниваем по циферкам*/
	public boolean isItPossibleToBuy (User user) throws ServiceException {
		boolean result = false;
		try(TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			UtilDaoImpl dao = currentTransaction.createUtilDao();
			int howMuchUserWantToBuy = dao.howMuchMedicineUserWantToBuy(user);
			int howMuchUserCanBuy = dao.howMuchMedicineUserCanBuy(user);
			if (howMuchUserWantToBuy == howMuchUserCanBuy) {
				result = true;
			}
			currentTransaction.endTransaction();
			return result;
		} catch (SQLException e) {
			throw new ServiceException();
		}
	}
	
	
}
