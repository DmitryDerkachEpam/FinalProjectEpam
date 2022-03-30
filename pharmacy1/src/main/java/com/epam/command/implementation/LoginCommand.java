package com.epam.command.implementation;

import javax.servlet.http.HttpServletRequest;
import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.entity.Order;
import com.epam.entity.User;
import com.epam.entity.roles.UserRole;
import com.epam.exception.ErrorMessages;
import com.epam.exception.ExceptionMessage;
import com.epam.exception.ServiceException;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;
import com.epam.service.OrderService;
import com.epam.service.UserService;
import com.epam.validator.OrderValidator;
import com.epam.validator.UserValidator;

public class LoginCommand implements Command {
	
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

	@Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        CommandResult commandResult = null;
        String page = "removeLater";
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
 
        UserService userService = new UserService(new TransactionFactory());
        OrderService orderService = new OrderService(new TransactionFactory());
        
        try {
        	if (UserValidator.isInputFromLoginFormCorrect(email, password)) {
                    User user = userService.getUserByLoginAndPassword(email, password).get();
                    UserRole userRole = user.getUserRole();
                    request.getSession().setAttribute("user", user);
                    switch (userRole) {
                        case USER:
                        	
                        	OrderValidator orderValidator = new OrderValidator();
                            if (!orderValidator.isOrderExists(user)) {
                            	Order userOrder = new Order();
                            	userOrder.setAssociatedUser(user);
                             	orderService.createOrder(userOrder);
                            	request.getSession().setAttribute("order", userOrder);
                            } else {
                            	request.getSession().setAttribute("order", orderService.findActualOrderByUserId(user));
                            }
                            
                            commandResult = new ShowAllMedicines().execute(request);
                            break;
                        case DOCTOR:
                        	commandResult = new ShowAllReceipts().execute(request);
                            break;
                        case ADMIN:
                        	commandResult = new ShowAllUsers().execute(request);  
                            break;
                        case PHARMACIST:
                        	page = request.getContextPath() + PageManager.getValue(PageMapper.PHARMACIST_MAIN_PAGE_KEY.getPageName());
                        	/*Копируем строчку их user. И пользователь и фармацевт буду видеть на jsp лекарства,разница будет в функционале*/
//                        	request.getSession().setAttribute("medicines", medicineService.findAllMedicines());
                        	break;
                        default:
                            throw new EnumConstantNotPresentException(UserRole.class, userRole.name());
                    }
                    //commandResult = new CommandResult(page, NavigationType.REDIRECT);
                } else {
            		page = request.getContextPath() + PageManager.getValue(PageMapper.LOGIN_PAGE_KEY.getPageName());
            		request.getSession().setAttribute("message", ErrorMessages.LOGIN_ERROR.getMessage());
                	commandResult = new CommandResult(page, NavigationType.REDIRECT);
                }

        } catch (ServiceException e) {
            throw new ServiceException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

}
