package com.epam.dao.abstract_dao;

import java.sql.SQLException;
import java.util.Optional;
import com.epam.entity.Order;
import com.epam.entity.User;

public interface OrderDao {
	Optional<Order> findByUserId (User user) throws SQLException;
	void updateItemQuantity (int newQuantity, int orderId, int medId) throws SQLException;
}
