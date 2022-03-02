package com.epam.command.implementation;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.Medicine;
import com.epam.entity.Order;
import com.epam.entity.Receipt;
import com.epam.entity.User;
import com.epam.entity.roles.UserRole;
import com.epam.exception.CommandException;
import com.epam.exception.ExceptionMessage;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.MedicineService;
import com.epam.service.OrderService;
import com.epam.service.ReceiptService;
import com.epam.service.UserService;

public class LoginCommand implements Command {
	
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String SESSION_USER = "user";

	@Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        CommandResult commandResult = null;
        String page;
        String emailValue = request.getParameter(EMAIL);
        String passwordValue = request.getParameter(PASSWORD);
 
        UserService userService = new UserService(new TransactionFactory());
        MedicineService medicineService = new MedicineService(new TransactionFactory());
        OrderService orderService = new OrderService(new TransactionFactory());
        
        try {
        	/*Делаем 3 проверки. Проверка на валидность логина, проверка на валидность па-
        	 * роля, проверка на существование юзера в БД. Если все три условия вып-ся, то то-
        	 * лько тогда двигаться дальше. Пока этот момент опустим*/
//            if (UserValidator.isValidLogin(loginValue) &&
//                    UserValidator.isValidPassword(passwordValue) &&
        			/*Потом как-то прикрутить шифрование. Мб использовать стороннюю либу*/
//                    UserService.getInstance().isUserExist(loginValue, PasswordEncoder.encodePassword(passwordValue))) {
        	
        	/*Запилить проверку на фейл логина как в финальном проекте*/
                Optional<User> maybeExistingUser = userService.getUserByLoginAndPassword(emailValue, passwordValue);
                if (maybeExistingUser.isPresent()) {
                    User user = maybeExistingUser.get();
                    UserRole userRole = user.getUserRole();
                    /*Подумать, надо ли закидывать в сессию юзера, наверное да*/
                    //request.getSession().setAttribute("user", user);
                    switch (userRole) {
                        case USER:
                            page = request.getContextPath() + PageManager.getValue(PageMapper.USER_MAIN_PAGE_KEY.getPageName());
                            request.getSession().setAttribute("medicines", medicineService.getAllMedicinesFromDatabase());
                            /*Также, когда мы залогинились как пользователь, мы должны создать новый order, к которому будут привязаны order_items  - покупаемые продукты*/
                            Order userOrder = new Order();
                            userOrder.setAssociatedUser(user);
                            orderService.createOrder(userOrder);
                            System.out.println();
                            /*Мб вернуть обратно, хз*/
                            
                            break;
                        case DOCTOR:
                            page = request.getContextPath() + PageManager.getValue(PageMapper.DOCTOR_MAIN_PAGE_KEY.getPageName());
                            request.getSession().setAttribute("receipts", receiptService.getAllRequstedReceipts());
                            /*Для проверки (все ок!)*/
  //                         List<Receipt> secondTestList = receiptService.getAllRequstedReceipts();
                            System.out.println();
                            break;
                        case ADMIN:
                            page = request.getContextPath() + PageManager.getValue(PageMapper.ADMIN_MAIN_PAGE_KEY.getPageName()); 
                            request.getSession().setAttribute("users", userService.getAllUsersFromDatabase());
                            break;
                        case PHARMACIST:
                        	page = request.getContextPath() + PageManager.getValue(PageMapper.PHARMACIST_MAIN_PAGE_KEY.getPageName());
                        	/*Копируем строчку их user. И пользователь и фармацевт буду видеть на jsp лекарства,разница будет в функционале*/
//                        	request.getSession().setAttribute("medicines", medicineService.findAllMedicines());
                        default:
                            throw new EnumConstantNotPresentException(UserRole.class, userRole.name());
                    }
                    commandResult = new CommandResult(page, NavigationType.REDIRECT);
                }
              /*Этот else мы пишем только в том случае, если у нас реализованы раз-
               * личные проверки на валидность*/  
//            } else {
//				 Что-то придумать...
//            }
                /*Подумать, что делать с эксепшенами*/
        } catch (ServiceException e) {
            throw new ServiceException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

}
