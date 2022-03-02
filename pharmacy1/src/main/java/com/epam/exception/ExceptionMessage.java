package com.epam.exception;

public final class ExceptionMessage {
	/*Подумать, как реализовать обработку исключений. Сразу ронять через Runtime или
	 *писать свои, кастомные исключения*/
    public static final String CONNECTION_POOL_EXCEPTION_MESSAGE = "Connection pool error";
    public static final String DAO_EXCEPTION_MESSAGE = "Database error";
    public static final String COMMAND_EXCEPTION_MESSAGE = "Controller error";
    private ExceptionMessage() {
    	
    }
}
