package com.jaxrs.example.messanger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jaxrs.example.messanger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>
{

	@Override
	public Response toResponse(Throwable arg0) 
	{
		//arg0.printStackTrace();
		String errorMessage = arg0.getMessage();
		System.out.println(errorMessage);
		int errorCode = 500;
		if(errorMessage == null)
		{
			errorMessage = "HTTP 500 Internal Server Error due to NullPointerException";
		}
		String str[] = errorMessage.split("\\s");
		for(int i = 0 ; i < str.length ; i ++)
		{
			if(str[i].matches("^\\d{3}$"))
			{			
				errorCode = Integer.parseInt(str[i]);
			}
		}
		ErrorMessage error = new ErrorMessage(errorCode, errorMessage, "https://docs.oracle.com/javaee/7/api/javax/ws/rs/WebApplicationException.html");
		return Response.status(errorCode).entity(error).build();
		
		
	}
	
}