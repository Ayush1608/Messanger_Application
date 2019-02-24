package com.jaxrs.example.messanger.exceptions;

public class DataNotFoundException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 341433588874986860L;

	public DataNotFoundException(String message)
	{
		super(message);
	}
}
