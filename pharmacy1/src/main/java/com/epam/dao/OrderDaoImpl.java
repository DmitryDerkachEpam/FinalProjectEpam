package com.epam.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import com.epam.dao.abstract_dao.AbstractDao;
import com.epam.dao.abstract_dao.OrderDao;
import com.epam.entity.Order;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
	
	private static final String SAVE = 
	    	"insert into orders (date, user_id) values (?,?)";
	
	public OrderDaoImpl(Connection connection) {
		super(connection);
	}
	
	@Override
	public void save(Order item) throws SQLException {
		LocalDate orderDate = item.getDate();
		int associatedUserId = item.getAssociatedUser().getId();
		Object[] orderDataArray = {orderDate, associatedUserId};
		executeForVoidResult(SAVE, orderDataArray);
	}

	@Override
	protected String getTableName() {
		return Order.TABLE;
	}

}
