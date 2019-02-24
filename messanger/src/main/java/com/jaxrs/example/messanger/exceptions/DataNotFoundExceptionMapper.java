package com.jaxrs.example.messanger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jaxrs.example.messanger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>
{

	@Override
	public Response toResponse(DataNotFoundException arg0) 
	{
		ErrorMessage error = new ErrorMessage(404, arg0.getMessage(), "No Documentation");
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}
	
}
