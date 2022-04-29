package com.epam.dao.entity_builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.entity.Item;
import com.epam.entity.Medicine;
import com.epam.entity.Order;

public class ItemEntityBuilder implements EntityBuilder<Item>{

	@Override
	public Item map(ResultSet resultSet) throws SQLException {
		Item item = new Item();
		item.setId(resultSet.getObject("id", Integer.class));
		item.setAssociatedOrder(new Order(resultSet.getObject("order_id", Integer.class)));
		item.setAssociatedMedicine(new Medicine(resultSet.getObject("medicine_id", Integer.class)));
		item.setQuantity(resultSet.getObject("quantity", Integer.class));
		return item;
	}

}
