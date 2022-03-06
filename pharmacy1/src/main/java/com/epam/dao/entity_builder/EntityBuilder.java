package com.epam.dao.entity_builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.dao.marker.Identifable;
import com.epam.entity.Medicine;
import com.epam.entity.Receipt;
import com.epam.entity.User;

/*Реализации RowMapper умеют мапить ResultSet к определенному типу объекта*/
public interface EntityBuilder<T extends Identifable> {
	T map (ResultSet resultSet) throws SQLException;
	
	static EntityBuilder<? extends Identifable> create (String table) {
		switch (table) {
		case User.TABLE:
			return new UserEntityBuilder();
		case Medicine.TABLE:
			return new MedicineEntityBuilder();
		case Receipt.TABLE:
			return new ReceiptEntityBuilder();
		default:
			throw new IllegalArgumentException("Unknown table = " + table);
		}
	
	}
	
}
