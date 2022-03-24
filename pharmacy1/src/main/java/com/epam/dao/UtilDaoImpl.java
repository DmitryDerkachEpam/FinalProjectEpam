package com.epam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.dao.abstract_dao.UtilDao;
import com.epam.entity.User;

public class UtilDaoImpl implements UtilDao {

	private final Connection connection;
	
	public UtilDaoImpl(Connection connection) {
		this.connection = connection;
	}
	
	private static final String HOW_MUCH_MEDICINE_USER_WANT_TO_BY = 
			"SELECT COUNT(oi.id) as number\r\n"
			+ "FROM order_items oi\r\n"
			+ "         JOIN orders o ON oi.order_id = o.id\r\n"
			+ "         JOIN users u ON o.user_id = u.id\r\n"
			+ "WHERE o.is_paid =  FALSE\r\n"
			+ "AND u.id = ?";
	
	private static final String HOW_MUCH_MEDICINE_USER_CAN_BY = 
			"SELECT COUNT(a.name) as number\r\n"
			+ "FROM\r\n"
			+ "(SELECT m.name\r\n"
			+ "FROM orders o\r\n"
			+ "         JOIN order_items om ON o.id = om.order_id\r\n"
			+ "         JOIN medicines m ON om.medicine_id = m.id\r\n"
			+ "         JOIN receipts r ON m.id = r.medicine_id\r\n"
			+ "WHERE o.user_id = ?\r\n"
			+ "  AND o.is_paid = FALSE\r\n"
			+ "  AND m.is_receipt_required = TRUE\r\n"
			+ "  AND (r.state = 'approved' AND r.user_id = ?)\r\n"
			+ "UNION ALL\r\n"
			+ "SELECT m.name\r\n"
			+ "FROM orders o\r\n"
			+ "         JOIN order_items om ON o.id = om.order_id\r\n"
			+ "         JOIN medicines m ON om.medicine_id = m.id\r\n"
			+ "WHERE o.user_id = ?\r\n"
			+ "  AND o.is_paid = FALSE\r\n"
			+ "  AND m.is_receipt_required = FALSE) AS a";
	
	public int howMuchMedicineUserWantToBuy(User user) throws SQLException {
		int userId = user.getId();
		return this.execute(HOW_MUCH_MEDICINE_USER_WANT_TO_BY, userId);
	}
	
	public int howMuchMedicineUserCanBuy(User user) throws SQLException {
		int userId = user.getId();
		return this.execute(HOW_MUCH_MEDICINE_USER_CAN_BY, userId, userId, userId);
	}
	
	private int execute(String statement, int... params) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(statement);
		for (int i = 1; i <= params.length; i++) {
			preparedStatement.setObject(i, params[i-1]);
		}
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int result = resultSet.getObject("number", Integer.class);
		return result;
	}
	

}
