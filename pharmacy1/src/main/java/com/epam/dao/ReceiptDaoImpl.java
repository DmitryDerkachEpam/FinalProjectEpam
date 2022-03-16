package com.epam.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.dao.abstract_dao.AbstractDao;
import com.epam.dao.abstract_dao.ReceiptDao;
import com.epam.dao.entity_builder.ReceiptEntityBuilder;
import com.epam.entity.Receipt;
import com.epam.entity.User;

public class ReceiptDaoImpl extends AbstractDao<Receipt> implements ReceiptDao  {
	
	public ReceiptDaoImpl(Connection connection) {
		super(connection);
	}
	
	private static final String FIND_BY_MEDICINE_ID_AND_USER_ID = 
			"select * from receipts where user_id = ? and medicine_id = ? and state not in ('used')";
	
	private static final String SAVE = 
	    	"insert into receipts (user_id, medicine_id) values (?,?)";
	
	private static final String FIND_BY_USER_ID = 
			"select * from receipts where user_id = ?";
	
	private static final String CHANGE_RECEIPT_STATE = 
			"update receipts set expiration_date = null, state = 'requested' where id = ?";

	public List<Receipt> findAll() throws SQLException {
		return super.findAll();
	}
	
	public Optional<Receipt> findbyMedicineIdAndUserId(int medId, int userId) throws SQLException {
		Optional<Receipt> result = null;
		result = executeForSingleResult(FIND_BY_MEDICINE_ID_AND_USER_ID, new ReceiptEntityBuilder(), medId, userId);
		return result;
	}
	
	@Override
	public void save(Receipt item) throws SQLException {
		int associatedUserId = item.getAssociatedUser().getId();
		int associatedMedicineId = item.getAssociatedMedicine().getId();
		Object[] userDataArray = {associatedUserId, associatedMedicineId};
		executeForVoidResult(SAVE, userDataArray);
	}

	@Override
	public List<Receipt> findAllReceiptsByUserId(User user) throws SQLException {
		List<Receipt> result;
		int associatedUserId = user.getId();
		result = executeForMultiResults(FIND_BY_USER_ID, new ReceiptEntityBuilder(), associatedUserId);
		return result;
	}

	public void changeReceiptStateById(int receiptId) throws SQLException{
		executeForVoidResult(CHANGE_RECEIPT_STATE, receiptId);
		
	}

	@Override
	protected String getTableName() {
		return Receipt.TABLE;
	}
	
}
