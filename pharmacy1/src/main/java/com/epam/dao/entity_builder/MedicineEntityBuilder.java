package com.epam.dao.entity_builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.entity.Medicine;

public class MedicineEntityBuilder implements EntityBuilder<Medicine> {

	@Override
	public Medicine map(ResultSet resultSet) throws SQLException {
		Medicine medicine = new Medicine();
		medicine.setId(resultSet.getObject("id", Integer.class));
		medicine.setName(resultSet.getObject("name", String.class));
		medicine.setDose(resultSet.getObject("dose", Integer.class));
		medicine.setIsReceiptRequired((resultSet.getObject("is_receipt_required", Boolean.class).booleanValue()));
		medicine.setPrice(resultSet.getObject("price", Integer.class));
		medicine.setMedicineImageKey(resultSet.getObject("image_name", String.class)); 
		return medicine;
	}

}
