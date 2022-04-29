package com.epam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.command.Command;
import com.epam.command.CommandFactory;
import com.epam.command.CommandResult;
import com.epam.command.NavigationType;
import com.epam.dbconnection.СonnectionManager;
import com.epam.exception.ServiceException;

@WebServlet("/mainController")
public class MainController extends HttpServlet {
	
	private static final Logger LOGGER = LogManager.getLogger(MainController.class);

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
  
         try {
             result = command.execute(req);
             switch (result.getNavigationType()){
                 case FORWARD:
                     req.getRequestDispatcher(result.getPage()).forward(req, resp);
                     break;
                 case REDIRECT:
                     resp.sendRedirect(result.getPage());
                     break;
                 default:
                     throw new EnumConstantNotPresentException(NavigationType.class, result.getNavigationType().name());
             }
         } catch (ServletException | ServiceException | IOException e ) {
            e.printStackTrace();
            LOGGER.error("Error was occurred", e);
         }
    }
    
}

