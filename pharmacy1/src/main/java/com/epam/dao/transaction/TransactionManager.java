package com.epam.dao.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.dao.ItemDaoImpl;
import com.epam.dao.MedicineDaoImpl;
import com.epam.dao.OrderDaoImpl;
import com.epam.dao.ReceiptDaoImpl;
import com.epam.dao.UserDaoImpl;
import com.epam.dao.UtilDaoImpl;

public class TransactionManager implements AutoCloseable{

	private Connection connection;

	public TransactionManager(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void close() throws SQLException{
		connection.close();
	}
	
	public UserDaoImpl createUserDao() {
		return new UserDaoImpl(connection);
	}
	
	public MedicineDaoImpl createMedicineDao() {
		return new MedicineDaoImpl(connection);
	}
	
	public OrderDaoImpl createOrderDao() {
		return new OrderDaoImpl(connection);
	}
	
	public ReceiptDaoImpl createReceiptDao() {
		return new ReceiptDaoImpl(connection);
	}
	
	public ItemDaoImpl createItemDao() {
		return new ItemDaoImpl(connection);
	}
	
	public UtilDaoImpl createUtilDao() {
		return new UtilDaoImpl(connection);
	}
	
	public void startTransaction() throws SQLException {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new SQLException();
		}
	}
	
	public void endTransaction() throws SQLException {
		try {
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new SQLException();
		}
	}
	
}
