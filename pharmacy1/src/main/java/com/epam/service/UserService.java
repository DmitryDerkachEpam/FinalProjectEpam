package com.epam.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import com.epam.dao.UserDaoImpl;
import com.epam.dao.transaction.TransactionFactory;
import com.epam.dao.transaction.TransactionManager;
import com.epam.entity.User;
import com.epam.exception.ServiceException;

public class UserService {
	
	private TransactionFactory transactionFactory;
	
	public UserService(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}
	
	public Optional<User> getUserByLoginAndPassword (String login, String password) throws ServiceException {
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			UserDaoImpl dao = currentTransaction.createUserDao();
			Optional<User> user = dao.findUserByEmailAndPassword(login, password);
			currentTransaction.endTransaction();
			return user;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}//getUserByLoginAndPassword_Tested
	
	public List<User> getAllUsersFromDatabase() throws ServiceException {
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			UserDaoImpl dao = currentTransaction.createUserDao();
			List<User> users = dao.findAll();
			currentTransaction.endTransaction();
			return users;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}//getAllUsersFromDataBase_Tested
	
	public void saveUserIntoDatabase (User user) throws ServiceException {
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			UserDaoImpl dao = currentTransaction.createUserDao();
			dao.save(user);
			currentTransaction.endTransaction();
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}//saverUserIntoDatabase_Tested
	
	public Optional<User> getUserById(User user) throws ServiceException {
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			UserDaoImpl dao = currentTransaction.createUserDao();
			Optional<User> result = dao.findById(user.getId());
			currentTransaction.endTransaction();
			return result;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	public Optional<User> getUserByName(String nameValue) throws ServiceException {
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			UserDaoImpl dao = currentTransaction.createUserDao();
			Optional<User> result = dao.findByName(nameValue);
			currentTransaction.endTransaction();
			return result;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	public void changeUserRoleById(String roleValue, String userId) throws ServiceException {
		try (TransactionManager currentTransaction = transactionFactory.create()) {
			currentTransaction.startTransaction();
			UserDaoImpl dao = currentTransaction.createUserDao();
			dao.changeUserRoleById(roleValue, userId);
			currentTransaction.endTransaction();
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}
	
}
