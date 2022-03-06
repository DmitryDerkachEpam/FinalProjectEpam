package com.epam.dao.abstract_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.dao.entity_builder.EntityBuilder;
import com.epam.dao.marker.Identifable;

/*Класс, который умеет выполнять абстрактный запрос*/
public abstract class AbstractDao <T extends Identifable> implements Dao<T> {

	private final Connection connection;
	
	protected AbstractDao(Connection connection) {
		this.connection = connection;
	}
	
	private PreparedStatement createStatement(String query, Object... params) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(query);
		for (int i = 1; i <= params.length; i++) {
			statement.setObject(i, params[i-1]);
		}
		return statement;
	}
	
	protected List<T> executeForMultiResults(String query, EntityBuilder<T> mapper, Object... params) throws SQLException {
		
		try {
			PreparedStatement statement = createStatement(query, params);
			ResultSet resultSet = statement.executeQuery();
			List<T> entities = new ArrayList<>();
			while (resultSet.next()) {
				T entity = mapper.map(resultSet);
				entities.add(entity);
			}
			return entities;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
		
	}

	protected Optional<T> executeForSingleResult(String query, EntityBuilder<T> mapper, Object... params) throws SQLException {
		List<T> items = executeForMultiResults(query, mapper, params);
		if (items.size() == 1) {
			return Optional.of(items.get(0));
		} else {
			if (items.size() > 1) {
				throw new IllegalArgumentException();
			} else {
				return Optional.empty();
			}
		}
	}
	
	protected void executeForVoidResult(String query, Object... params) throws SQLException {
		try(PreparedStatement statement = createStatement(query, params)) {
			statement.executeUpdate();
		}
	}
	
	@Override
	public Optional<T> findById(int id) throws SQLException {
		
		return null;
	}
	
	
	@Override
	public void removeBy(int id) throws SQLException {
		String table = getTableName();
		EntityBuilder<T> mapper = (EntityBuilder<T>) EntityBuilder.create(table);
		executeForVoidResult("delete from " + table + "where id = ", id);
	}
	
	@Override
	public List<T> findAll() throws SQLException {
		/*Узнаем имя таблицы*/
		String table = getTableName();
		/*По ней создаем маппер*/
		EntityBuilder<T> mapper = (EntityBuilder<T>) EntityBuilder.create(table);
		return executeForMultiResults("select * from " + table, mapper);
	}

	protected abstract String getTableName();
}
