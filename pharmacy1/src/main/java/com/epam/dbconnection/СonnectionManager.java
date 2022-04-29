package com.epam.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class СonnectionManager {
	private static final String password_key = "db.password";
	private static final String username_key = "db.username";
	private static final String url_key = "db.url";
	private static final String pool_size_key = "db.pool.size";
	private static final Integer default_pool_size = 10;
	private static СonnectionManager instance;
	public static BlockingQueue<Connection> pool;
	private static List<Connection> sourceConnections;
	
	static {
		initConnectionPool();
	}
	
	private СonnectionManager() {
	
	}
	
	public static synchronized СonnectionManager getInstance() {
		if (instance == null) {
			instance = new СonnectionManager();
		}
		return instance;
	}

	private static void initConnectionPool() {
		String poolSize = PropertiesUtil.get(pool_size_key);
		int size = poolSize == null ? default_pool_size : Integer.parseInt(poolSize);
		pool = new ArrayBlockingQueue<Connection>(size);
		sourceConnections = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			Connection connection = open();
			InvocationHandler invHandler = new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
					if (method.getName().equals("close")) {
						return pool.add((Connection)proxy);
					} else {
						return method.invoke(connection, args);
					}		
				}
			};
			Class [] classes = new Class [] {Connection.class};
			ClassLoader classLoader = СonnectionManager.class.getClassLoader();
			Connection proxyConnection = (Connection) Proxy.newProxyInstance(classLoader, classes, invHandler); 
			pool.add(proxyConnection);
			sourceConnections.add(connection);
		}
	}
	public static Connection get() {
		try {
			return pool.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	private static Connection open() {
		try {
			return (DriverManager.getConnection(PropertiesUtil.get(url_key),
											    PropertiesUtil.get(username_key),
											    PropertiesUtil.get(password_key)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void closePool() {
		try {
			for (Connection sourceConnection : sourceConnections) {
				sourceConnection.close();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
