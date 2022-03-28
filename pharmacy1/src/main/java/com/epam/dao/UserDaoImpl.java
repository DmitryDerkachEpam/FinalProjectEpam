package com.epam.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.dao.abstract_dao.AbstractDao;
import com.epam.dao.abstract_dao.UserDao;
import com.epam.dao.entity_builder.UserEntityBuilder;
import com.epam.entity.User;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
	
	public UserDaoImpl(Connection connection) {
		super(connection);
	}

	private static final String FIND_BY_EMAIL_AND_PASSWORD_SQL = 
			"select * from users where email = ? and password = ?";
	
	private static final String SAVE = 
	    	"insert into users (name, email, password) values (?,?,?)";
	
	private static final String FIND_BY_NAME = 
	    	"select * from users where name = ?";
	
	@Override
	public Optional<User> findUserByEmailAndPassword(String login, String password) throws SQLException {
		Optional<User> result = null;
		result = executeForSingleResult(FIND_BY_EMAIL_AND_PASSWORD_SQL, new UserEntityBuilder(), login, password);
		return result;
	}
	
	public Optional<User> findByName(String nameValue) throws SQLException {
		return executeForSingleResult(FIND_BY_NAME, new UserEntityBuilder(), nameValue);
	}
	
	@Override
	public void save(User item) throws SQLException {
		String userName = item.getName();
		String userEmail = item.getEmail();
		String userPassword = item.getPassword();
		Object[] userDataArray = {userName, userEmail, userPassword};
		executeForVoidResult(SAVE, userDataArray);
		
	}
	
	@Override
	public Optional<User> findById(int id) throws SQLException {
		return super.findById(id);
	}
	
	@Override
	public List<User> findAll() throws SQLException {
		return super.findAll();
	}

	@Override
	public void removeBy(int id) throws SQLException {
		super.removeBy(id);
		
	}
	
	protected String getTableName() {
		return User.TABLE;
	}


	
}
