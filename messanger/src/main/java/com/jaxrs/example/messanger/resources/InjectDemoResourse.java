package com.jaxrs.example.messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResourse 
{
	@GET
	@Path("/annotation")
	public String getParamsUsingAnnotations(@MatrixParam("param") String paramValue,			//The parameters inside annotations 
											@HeaderParam("customHeader") String headerValue,	//should be same as given in client.
											@CookieParam("customCookie") String cookieValue)
	{
		return "matrix param = " + paramValue + 
				" || custom header value = " + headerValue + 
				" || custom cookie value = " + cookieValue;
	}
	
	// If we don't know the exact values for header, cookies or params we use this annotation.
	@GET
	@Path("/context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeader)
	{
		return uriInfo.getAbsolutePath().toString() + " header cookies = " + httpHeader.getCookies().toString();			//uriInfo now has various methods to access different things
		
	}
	
	//second way for accepting multiple annotations is creating bean param annotation. USED IN MESSAGE RESOURCES CLASS.
	
}
