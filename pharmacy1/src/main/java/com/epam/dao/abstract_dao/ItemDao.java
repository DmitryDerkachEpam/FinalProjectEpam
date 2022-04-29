package com.epam.dao.abstract_dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import com.epam.entity.Item;
import com.epam.entity.Order;

public interface ItemDao {
	List<Item> findItemsByOrderId(int orderId) throws SQLException;
	Optional<Item> findItemByOrderAndMedId(int firtsId, int secondId) throws SQLException;
	void deleteItemsByOrderId(int orderId) throws SQLException;
}
