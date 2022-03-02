package com.epam.dao.transaction;

import com.epam.dbconnection.СonnectionManager;

public class TransactionFactory {

	public TransactionManager create () {
		return new TransactionManager(СonnectionManager.get());
	}
}
