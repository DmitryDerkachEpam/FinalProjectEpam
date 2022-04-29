package com.epam.dao.entity_builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import com.epam.entity.Order;
import com.epam.entity.User;

public class OrderEntityBuilder implements EntityBuilder<Order>{

	@Override
	public Order map(ResultSet resultSet) throws SQLException {
		Order order = new Order();
		order.setId(resultSet.getObject("id", Integer.class));
		order.setDate(resultSet.getObject("date", LocalDate.class));
		order.setAssociatedUser(new User(resultSet.getObject("user_id", Integer.class)));
		order.setIsPaidStatus(resultSet.getObject("is_paid", Boolean.class));
		return order;
	}

}
