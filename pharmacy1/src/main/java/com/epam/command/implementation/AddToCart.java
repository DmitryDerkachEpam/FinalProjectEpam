package com.epam.command.implementation;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.ItemService;
import com.epam.service.MedicineService;
import com.epam.service.OrderService;
import com.epam.entity.Item;
import com.epam.entity.Medicine;
import com.epam.entity.Order;
import com.epam.entity.User;
public class AddToCart implements Command {

	@Override
	public CommandResult execute(HttpServletRequest request) throws ServiceException {
		CommandResult commandResult = null;
		String page;
		
		ItemService itemService = new ItemService(new TransactionFactory());
		MedicineService medicineService = new MedicineService(new TransactionFactory());
		OrderService orderService = new OrderService(new TransactionFactory());
		
		/*Нажимаем по 1-ой кнопке за раз! Никаких массивов*/
		String medicineId = request.getParameter("medicineId");
		String quantity = request.getParameter("quantity");
		Item item = new Item();

		Medicine medicine = new Medicine();
		medicine.setId(Integer.parseInt(medicineId));
		Optional<Medicine> medecineWithAllData = medicineService.getMedicineById(medicine.getId());
		item.setAssociatedMedicine(medecineWithAllData.get());
		
		User user = (User) request.getSession().getAttribute("user");
		Optional<Order> order = orderService.findOrderByUserId(user);
		
		item.setAssociatedOrder(order.get());
		System.out.println();
		item.setQuantity(Integer.parseInt(quantity));
	
		/*Сохраняет!!!*/
		itemService.saveItemIntoDatabase(item);
		
		page = request.getContextPath() + PageManager.getValue(PageMapper.USER_MAIN_PAGE_KEY.getPageName());
		request.getSession().setAttribute("message", "was successfully added to cart");
		request.getSession().setAttribute("messageId", medicineId);
		commandResult = new CommandResult(page, NavigationType.REDIRECT);
		
		
		return commandResult;
	}

}
