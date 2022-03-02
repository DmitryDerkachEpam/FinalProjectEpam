package com.epam.service;

import com.epam.dao.transaction.TransactionFactory;

public class ReceiptService {
	
	private TransactionFactory transactionFactory;
	
	public ReceiptService(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}
	
}
