package com.epam.mapper;

import com.epam.entity.Receipt;
import dto.ReceiptDto;

public class MapFromReceiptToReceiptDto {

	public ReceiptDto mapFromReceiptToReceiptDto(Receipt receipt) {
		ReceiptDto receiptForOutput = new ReceiptDto();
		receiptForOutput.setExpirationDate(receipt.getDate());
		receiptForOutput.setId(receipt.getId());
		receiptForOutput.setMedicineName(receipt.getAssociatedMedicine().getName());
		receiptForOutput.setReceiptState(receipt.getState());
		receiptForOutput.setUserName(receipt.getAssociatedUser().getName());
		return receiptForOutput;
	}
}
