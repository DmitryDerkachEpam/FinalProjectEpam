package com.epam.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import com.epam.dao.abstract_dao.AbstractDao;
import com.epam.dao.abstract_dao.OrderDao;
import com.epam.dao.entity_builder.OrderEntityBuilder;
import com.epam.entity.Order;
import com.epam.entity.User;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
	
	private static final String SAVE = 
	    	"insert into orders (date, user_id) values (?,?)";
	private static final String FIND_ORDER_BY_USER_ID = 
			"select * from orders where user_id = ? and is_paid = 'false'";
	private static final String UPDATE_ITEM_QUANTITY = 
			"update order_items set quantity = ? where order_id = ? and medicine_id = ?";
	
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
	public Optional<Order> findByUserId(User user) throws SQLException {
		int userId = user.getId();
		Optional<Order> maybeExsistingOrder = executeForSingleResult(FIND_ORDER_BY_USER_ID, new OrderEntityBuilder(), userId);
		return maybeExsistingOrder;
	}
	
	@Override
	protected String getTableName() {
		return Order.TABLE;
	}

	public void updateItemQuantity(int quantity, int orderId, int medId) throws SQLException {
		executeForVoidResult(UPDATE_ITEM_QUANTITY, quantity, orderId, medId);
		
	}

}
