package com.epam.dao.abstract_dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import com.epam.entity.Receipt;
import com.epam.entity.User;

public interface ReceiptDao {
	Optional<Receipt> findbyMedicineIdAndUserId(int medId, int userId) throws SQLException;
	List<Receipt> findAllReceiptsByUserId(User user) throws SQLException;
	void changeStateToRequested(int receiptId) throws SQLException;
	void changeStateToApproved(int receiptId) throws SQLException;
}
