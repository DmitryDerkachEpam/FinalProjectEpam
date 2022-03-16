package com.epam.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import com.epam.dao.abstract_dao.AbstractDao;
import com.epam.dao.abstract_dao.MedicineDao;
import com.epam.entity.Medicine;

public class MedicineDaoImpl extends AbstractDao<Medicine> implements MedicineDao{
	
	public MedicineDaoImpl (Connection connection) {
		super(connection);
	}
	
	private static final String SAVE = 
			"insert into medicines (name, dose, is_receipt_required, price) values (?, ?, ?, ?)";
	@Override
	public List<Medicine> findAll() throws SQLException {
		return super.findAll();
	}

	@Override
	public Optional<Medicine> findById(int id) throws SQLException {
		return super.findById(id);
	}

	@Override
	public void save(Medicine item) throws SQLException {
		String medicineName = item.getName();
		int medicineDose = item.getDose();
		boolean isReceiptRequired = item.getIsReceiptRequired();
		int medicinePrice = item.getPrice();
		Object[] medicineDataArray = {medicineName, medicineDose, isReceiptRequired, medicinePrice};
		executeForVoidResult(SAVE, medicineDataArray);
	}

	@Override
	public void removeBy(int id) throws SQLException {
		super.removeBy(id);
	}

	@Override
	protected String getTableName() {
		return Medicine.TABLE;
	}
	

}
