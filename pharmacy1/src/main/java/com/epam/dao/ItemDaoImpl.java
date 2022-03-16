package com.epam.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.dao.abstract_dao.AbstractDao;
import com.epam.dao.abstract_dao.ItemDao;
import com.epam.dao.entity_builder.ItemEntityBuilder;
import com.epam.entity.Item;
import com.epam.entity.Order;


public class ItemDaoImpl extends AbstractDao<Item> implements ItemDao {

	private static final String SAVE = 
	    	"insert into order_items (order_id, medicine_id, quantity) values (?,?,?)";
	
	private static final String FIND_BY_ORDER_ID = 
	    	"select * from order_items where order_id = ?";
	
	private static final String FIND_BY_ORDER_AND_MED_ID = 
			"select * from order_items where order_id = ? and medicine_id = ?";
	
	public ItemDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public void save(Item item) throws SQLException {
		int associatedOrderId = item.getAssociatedOrder().getId();
		int assciatedMedicineId = item.getAssociatedMedicine().getId();
		int quantity = item.getQuantity();
		Object[] itemData = {associatedOrderId, assciatedMedicineId, quantity};
		executeForVoidResult(SAVE, itemData);
		
	}
	
	@Override
	public List<Item> findByOrderId(Order order) throws SQLException {
		int orderId = order.getId();
		List<Item> itemList = executeForMultiResults(FIND_BY_ORDER_ID, new ItemEntityBuilder(), orderId);
		return itemList;
	}

	@Override
	protected String getTableName() {
		return Item.TABLE;
	}

	public Optional<Item> findItemByOrderAndMedId(int orderId, int medicineId) throws SQLException {
		Object[] itemData = {orderId, medicineId};
		Optional<Item> result = executeForSingleResult(FIND_BY_ORDER_AND_MED_ID, new ItemEntityBuilder(), itemData);
		return result;
	}

}
