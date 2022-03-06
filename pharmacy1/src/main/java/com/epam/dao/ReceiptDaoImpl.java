package com.epam.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.epam.dao.abstract_dao.AbstractDao;
import com.epam.dao.abstract_dao.ReceiptDao;
import com.epam.entity.Receipt;

public class ReceiptDaoImpl extends AbstractDao<Receipt> implements ReceiptDao  {
	
	public ReceiptDaoImpl(Connection connection) {
		super(connection);
	}

	public List<Receipt> findAll() throws SQLException {
		return super.findAll();
	}
	
	@Override
	public void save(Receipt item) throws SQLException {

	}

	@Override
	protected String getTableName() {
		return Receipt.TABLE;
	}

}
