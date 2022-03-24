package com.epam.dao.abstract_dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import com.epam.entity.Order;
import com.epam.entity.User;

public interface OrderDao {
	Optional<Order> findActualOrderByUserId (User user) throws SQLException;
	void updateItemQuantity (int newQuantity, int orderId, int medId) throws SQLException;
	void changeOrderStateByUserId(int userId) throws SQLException;
	List<Order> findUsedOrdersByUserId(User user) throws SQLException;
}
