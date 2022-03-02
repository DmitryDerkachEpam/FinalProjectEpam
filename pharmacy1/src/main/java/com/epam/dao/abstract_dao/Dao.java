package com.epam.dao.abstract_dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

	Optional<T> findById(int id) throws SQLException;
	List<T> findAll() throws SQLException; 
	void save(T item) throws SQLException;
	void removeBy(int id) throws SQLException;
}
 