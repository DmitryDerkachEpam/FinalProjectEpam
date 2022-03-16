package com.epam.command.implementation;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.dto.ReceiptDto;
import com.epam.dto.dtobuilder.BuildReceiptDto;
import com.epam.entity.Order;
import com.epam.entity.Receipt;
import com.epam.entity.User;
import com.epam.entity.roles.UserRole;
import com.epam.exception.ExceptionMessage;
import com.epam.exception.LoginException;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.MedicineService;
import com.epam.service.OrderService;
import com.epam.service.ReceiptService;
import com.epam.service.UserService;
import com.epam.validator.OrderValidator;
import com.epam.validator.UserValidator;

public class LoginCommand implements Command {
	
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

	@Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException, LoginException {
        CommandResult commandResult = null;
        String page;
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
 
        UserService userService = new UserService(new TransactionFactory());
        MedicineService medicineService = new MedicineService(new TransactionFactory());
        OrderService orderService = new OrderService(new TransactionFactory());
        ReceiptService receiptService = new ReceiptService(new TransactionFactory());
        
        try {
        	if (UserValidator.isInputFromLoginCorrect(email, password)) {
                    User user = userService.getUserByLoginAndPassword(email, password).get();
                    UserRole userRole = user.getUserRole();
                    request.getSession().setAttribute("user", user);
                    switch (userRole) {
                        case USER:
                            page = request.getContextPath() + PageManager.getValue(PageMapper.USER_MAIN_PAGE_KEY.getPageName());
                            request.getSession().setAttribute("medicines", medicineService.getAllMedicinesFromDatabase());

                            OrderValidator orderValidator = new OrderValidator();
                            if (!orderValidator.isOrderExists(user)) {
                            	Order userOrder = new Order();
                            	userOrder.setAssociatedUser(user);
                             	orderService.createOrder(userOrder);
                            	request.getSession().setAttribute("order", userOrder);
                            } else {
                            	request.getSession().setAttribute("order", orderService.findOrderByUserId(user));
                            }
                            break;
                        case DOCTOR:
                            page = request.getContextPath() + PageManager.getValue(PageMapper.DOCTOR_MAIN_PAGE_KEY.getPageName());
                            List<Receipt> receiptsFromDatabase = receiptService.getAllRequstedReceipts();
                            List<ReceiptDto> receiptsForOutput = new ArrayList<>();
                            for (int i = 0; i < receiptsFromDatabase.size(); i++) {
                            	Receipt receipt = receiptsFromDatabase.get(i);
                            	ReceiptDto receiptForOutput = BuildReceiptDto.BuildReceiptDtoFromReceipt(receipt);
                            	receiptsForOutput.add(receiptForOutput);
                            }
                            request.getSession().setAttribute("receipts", receiptsForOutput);
                            break;
                        case ADMIN:
                            page = request.getContextPath() + PageManager.getValue(PageMapper.ADMIN_MAIN_PAGE_KEY.getPageName()); 
                            request.getSession().setAttribute("users", userService.getAllUsersFromDatabase());
                            break;
                        case PHARMACIST:
                        	page = request.getContextPath() + PageManager.getValue(PageMapper.PHARMACIST_MAIN_PAGE_KEY.getPageName());
                        	/*Копируем строчку их user. И пользователь и фармацевт буду видеть на jsp лекарства,разница будет в функционале*/
//                        	request.getSession().setAttribute("medicines", medicineService.findAllMedicines());
                        	break;
                        default:
                            throw new EnumConstantNotPresentException(UserRole.class, userRole.name());
                    }
                    commandResult = new CommandResult(page, NavigationType.REDIRECT);
                } else {
            		page = request.getContextPath() + PageManager.getValue(PageMapper.LOGIN_PAGE_KEY.getPageName());
                	request.getSession().setAttribute("message", LoginException.LOGIN_ERROR_MESSAGE);
                	commandResult = new CommandResult(page, NavigationType.REDIRECT);
                }

        } catch (ServiceException e) {
            throw new ServiceException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

}
