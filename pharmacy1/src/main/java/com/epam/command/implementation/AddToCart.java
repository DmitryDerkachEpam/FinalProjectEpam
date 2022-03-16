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
		OrderService orderService = new OrderService(new TransactionFactory());
		
		String medicineId = request.getParameter("medicineId");
		String quantity = request.getParameter("quantity");
		
		User user = (User) request.getSession().getAttribute("user");
		
		Optional<Order> order = orderService.findOrderByUserId(user);
		int orderId = order.get().getId();
		int medId = Integer.parseInt(medicineId);
		
		Optional<Item> item = itemService.findItemByOrderIdAndMedicineId(orderId, medId);
		
		if (item.isPresent()) {
			int oldQuantity = item.get().getQuantity();
			int newQuantity = oldQuantity + Integer.parseInt(quantity);
			orderService.changeItemQuantity(newQuantity, orderId, medId);
		} else {
			MedicineService medicineService = new MedicineService(new TransactionFactory());
			Item newItem = new Item();
			Optional<Medicine> medecineWithAllData = medicineService.getMedicineById(medId);
			newItem.setAssociatedMedicine(medecineWithAllData.get());
			newItem.setAssociatedOrder(order.get());
			newItem.setQuantity(Integer.parseInt(quantity));
			itemService.saveItemIntoDatabase(newItem);
		}
		
		request.getSession().setAttribute("message", "was successfully added to cart");
		request.getSession().setAttribute("messageId", medicineId);
		page = request.getContextPath() + PageManager.getValue(PageMapper.USER_MAIN_PAGE_KEY.getPageName());
		commandResult = new CommandResult(page, NavigationType.REDIRECT);
		return commandResult;
	}

}
