package com.epam.dao.entity_builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.entity.User;
import com.epam.entity.roles.UserRole;

public class UserEntityBuilder implements EntityBuilder<User>{

	@Override
	public User map(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getObject("id", Integer.class));
		user.setName(resultSet.getObject("name", String.class));
		user.setEmail(resultSet.getObject("email", String.class));
		user.setPassword(resultSet.getObject("password", String.class));
		user.setUserRole(UserRole.valueOf(resultSet.getObject("role", String.class).toUpperCase()));
		return user;
	}

}
