package com.epam.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.epam.dao.MedicineDaoImpl;
import com.epam.dao.ReceiptDaoImpl;
import com.epam.dao.UserDaoImpl;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.dao.transaction.TransactionManager;
import com.epam.entity.Medicine;
import com.epam.entity.Receipt;
import com.epam.entity.User;
import com.epam.exception.ServiceException;

public class ReceiptService {
	
	private TransactionFactory transactionFactory;
	
	public ReceiptService(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}

	public List<Receipt> getAllRequstedReceipts() throws ServiceException{
		
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			ReceiptDaoImpl dao = currentTransaction.createReceiptDao();
			List<Receipt> receiptsWithDepricatedData = dao.findAll();
			UserDaoImpl userDao = currentTransaction.createUserDao();
			MedicineDaoImpl medicineDao = currentTransaction.createMedicineDao();
			List<Receipt> receiptsWithFullData = new ArrayList<>();
			for (int i = 0; i < receiptsWithDepricatedData.size(); i++) {
				Receipt receiptWithDepricatedData = receiptsWithDepricatedData.get(i);
				User assoctiatedUser = receiptWithDepricatedData.getAssociatedUser();
				Medicine assoctiatedMedicine = receiptWithDepricatedData.getAssociatedMedicine();
				Optional<User> allDataAboutUser = userDao.findById(assoctiatedUser.getId());
				Optional<Medicine> allDataAboutMedicine = medicineDao.findById(assoctiatedMedicine.getId());
				if (allDataAboutMedicine.isPresent() && allDataAboutUser.isPresent()) {
					receiptWithDepricatedData.setAssociatedMedicine(allDataAboutMedicine.get());
					receiptWithDepricatedData.setAssociatedUser(allDataAboutUser.get());
					receiptsWithFullData.add(receiptWithDepricatedData);
				} else {
					throw new ServiceException();
				}	
			}
			currentTransaction.endTransaction();
			return receiptsWithFullData;
			} catch (SQLException e) {
				throw new ServiceException();
			}
		}
	}