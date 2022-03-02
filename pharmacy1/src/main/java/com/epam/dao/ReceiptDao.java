package com.epam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.dbconnection.СonnectionManager;
import com.epam.entity.Medicine;
import com.epam.entity.Receipt;
import com.epam.entity.receipt_state.ReceiptState;

public class ReceiptDao implements Dao <Integer, Receipt>{
	
	private final static ReceiptDao INSTANCE = new ReceiptDao();
	private static final String FIND_ALL = "select * from receipts";
	
//	private final UserDao userDao = UserDao.getInstance();
	private final MedicineDaoImpl medicineDao = MedicineDaoImpl.getInstance();

	private static Connection getConnection() {
		Connection connection = СonnectionManager.get();
		return connection;
	}
	
	
	
	
	private ReceiptDao() {
		
	}
	
	public static ReceiptDao getInstance() {
		return INSTANCE;
	}
	
	@Override
	public List<Receipt> findAll() throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
				ResultSet resultSet = preparedStatement.executeQuery();
				List<Receipt> listOfReceipts = new ArrayList<>();
				while (resultSet.next()) {
					//listOfReceipts.add(buildReceipt(resultSet, connection));
					listOfReceipts.add(buildReceipt(resultSet));
				}
				return listOfReceipts;
			}
	}
	
	private Receipt buildReceipt(ResultSet resultSet) throws SQLException {
		Receipt receipt = new Receipt();
		receipt.setId(resultSet.getObject("id", Integer.class));
		/*Собираем инфрмацию про пользователю*/
		Integer assoctiatedUserId = resultSet.getObject("user_id", Integer.class);
//		receipt.setAssociatedUser(userDao.findById(assoctiatedUserId).get());
		/*Собираем информацию по лек-ву*/
		Integer assoctiatedMedicineId = resultSet.getObject("medicine_id", Integer.class);
		receipt.setAssociatedMedicine(medicineDao.findById(assoctiatedMedicineId).get());
		receipt.setDate(resultSet.getObject("expiration_date", LocalDate.class));
		receipt.setState(ReceiptState.valueOf(resultSet.getObject("state", String.class).toUpperCase()));
		return receipt;
}
	

	@Override
	public Optional<Receipt> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Receipt entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Receipt save(Receipt entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
