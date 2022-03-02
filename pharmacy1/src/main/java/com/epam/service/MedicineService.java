package com.epam.service;

import java.sql.SQLException;
import java.util.List;
import com.epam.dao.MedicineDaoImpl;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.dao.transaction.TransactionManager;
import com.epam.entity.Medicine;
import com.epam.exception.ServiceException;

public class MedicineService {
	
	private TransactionFactory transactionFactory;
	
	public MedicineService(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}
	
	public List<Medicine> getAllMedicinesFromDatabase() throws ServiceException {
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			MedicineDaoImpl dao = currentTransaction.createMedicineDao();
			List<Medicine> medicines = dao.findAll();
			currentTransaction.endTransaction();
			return medicines;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}//getAllMedicinesFromDataBase_Tested

}
