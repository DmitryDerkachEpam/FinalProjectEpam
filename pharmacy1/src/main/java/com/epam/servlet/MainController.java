package com.epam.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.command.Command;
import com.epam.command.CommandFactory;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dbconnection.СonnectionManager;
import com.epam.exception.LoginException;
import com.epam.exception.ServiceException;

@WebServlet("/mainController")
public class MainController extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }
	
    @Override
    public void destroy() {
        СonnectionManager.closePool();
    }

    @Override
    public void init() throws ServletException {
    	СonnectionManager.getInstance();
    }
	
    private void processRequest(HttpServletRequest req, HttpServletResponse resp)  {
    	
    	 String commandName = req.getParameter("command");
    	 Command command = CommandFactory.defineCommand(commandName);
    	 CommandResult result;
    	 /*Разообраться c forward и redirect, чтобы реализовать F5 защиту*/
         try {
             result = command.execute(req);
             switch (result.getNavigationType()){
                 case FORWARD:
                     req/*getServletContext()*/.getRequestDispatcher(result.getPage()).forward(req, resp);
                     break;
                 case REDIRECT:
                     resp.sendRedirect(result.getPage());
                     break;
                 default:
                     throw new EnumConstantNotPresentException(NavigationType.class, result.getNavigationType().name());
             }
         } catch (ServletException e) {
            e.printStackTrace();
            /*ЛОГИРОВАНИЕ*/
            /*ССЫЛКА НА СТРАНИЦУ С ОШИБКОЙ*/

		
	} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
