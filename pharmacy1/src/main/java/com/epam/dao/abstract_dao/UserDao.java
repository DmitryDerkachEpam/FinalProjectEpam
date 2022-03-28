package com.epam.dao.abstract_dao;

import java.sql.SQLException;
import java.util.Optional;
import com.epam.entity.User;

public interface UserDao {
	Optional<User> findUserByEmailAndPassword (String login, String password) throws SQLException;
	Optional<User> findByName(String nameValue) throws SQLException;
}
