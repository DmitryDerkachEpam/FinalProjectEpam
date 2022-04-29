package com.epam.dao.entity_builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.epam.entity.Medicine;
import com.epam.entity.Receipt;
import com.epam.entity.User;
import com.epam.entity.receipt_state.ReceiptState;

public class ReceiptEntityBuilder implements EntityBuilder<Receipt> {

	@Override
	public Receipt map(ResultSet resultSet) throws SQLException {
		Receipt receipt = new Receipt();
		receipt.setId(resultSet.getObject("id", Integer.class));
		receipt.setAssociatedUser(new User(resultSet.getObject("user_id", Integer.class)));
		receipt.setAssociatedMedicine(new Medicine(resultSet.getObject("medicine_id", Integer.class)));
		receipt.setDate(resultSet.getObject("expiration_date", LocalDate.class));
		receipt.setState(ReceiptState.valueOf(resultSet.getObject("state", String.class).toUpperCase()));
		return receipt;
	}

}
