package com.epam.dto.dtobuilder;

import java.util.Optional;

import com.epam.dao.transaction.TransactionFactory;
import com.epam.dto.ReceiptDto;
import com.epam.entity.Medicine;
import com.epam.entity.Receipt;
import com.epam.entity.User;
import com.epam.exception.ServiceException;
import com.epam.service.MedicineService;
import com.epam.service.UserService;

public class BuildReceiptDto {
	
	public static ReceiptDto BuildReceiptDtoFromReceipt (Receipt receipt) throws ServiceException {
		Receipt fullReceipt = receiveAllData(receipt);
		ReceiptDto receiptForOutput = new ReceiptDto();
		receiptForOutput.setExpirationDate(fullReceipt.getDate());
		receiptForOutput.setId(fullReceipt.getId());
		receiptForOutput.setMedicineName(fullReceipt.getAssociatedMedicine().getName());
		receiptForOutput.setReceiptState(fullReceipt.getState());
		receiptForOutput.setUserName(fullReceipt.getAssociatedUser().getName());
		return receiptForOutput;
	}
	
	private static Receipt receiveAllData (Receipt receipt) throws ServiceException {
		
		UserService userService = new UserService(new TransactionFactory());
		MedicineService medicineService = new MedicineService(new TransactionFactory());
		
		Medicine associatedMedicine = receipt.getAssociatedMedicine();
		User associatedUser = receipt.getAssociatedUser();
		
		Optional<User> allDataAboutUser = userService.getUserById(associatedUser);
		Optional<Medicine> allDataAboutMedicne = medicineService.getMedicineById(associatedMedicine.getId());
		
		if (allDataAboutMedicne.isPresent()) {
			receipt.setAssociatedMedicine(allDataAboutMedicne.get());
		}
		
		if (allDataAboutUser.isPresent()) {
			receipt.setAssociatedUser(allDataAboutUser.get());
		}
		
		return receipt;
		
	}
	
}
