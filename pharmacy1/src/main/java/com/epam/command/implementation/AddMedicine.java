package com.epam.command.implementation;

import javax.servlet.http.HttpServletRequest;
import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.Medicine;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.MedicineService;

public class AddMedicine implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		CommandResult commandResult;
		String page = request.getContextPath() + PageManager.getValue(PageMapper.PHARMACIST_MAIN_PAGE_KEY.getPageName());
		
		String medicineName = request.getParameter("medicineName");
		String medicineDose = request.getParameter("medicineDose");
		String medicinePrice = request.getParameter("medicinePrice");
		boolean isReceiptRequired = Boolean.parseBoolean(request.getParameter("isReceiptRequired"));
		
		Medicine newMedicine = new Medicine();
		newMedicine.setDose(Integer.parseInt(medicineDose));
		newMedicine.setIsReceiptRequired(isReceiptRequired);
		newMedicine.setName(medicineName);
		newMedicine.setPrice(Integer.parseInt(medicinePrice));
		
		MedicineService medicineService = new MedicineService(new TransactionFactory());
		medicineService.saveNewMedicine(newMedicine);
		
		commandResult = new CommandResult(page, NavigationType.REDIRECT);
		return commandResult;
	}

}
