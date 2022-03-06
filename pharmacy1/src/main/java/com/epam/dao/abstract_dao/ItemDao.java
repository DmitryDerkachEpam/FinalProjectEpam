package com.epam.dao.abstract_dao;

import java.sql.SQLException;
import java.util.List;
import com.epam.entity.Item;
import com.epam.entity.Order;

public interface ItemDao {
	List<Item> findByOrderId(Order order) throws SQLException;
}
