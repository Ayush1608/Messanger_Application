package com.jaxrs.example.messanger.resources;

import java.net.URI;
import java.util.List;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import com.jaxrs.example.messanger.model.Message;
import com.jaxrs.example.messanger.resources.beans.MessageFilterBean;
import com.jaxrs.example.messanger.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResourse 
{
	MessageService messageService = new MessageService();

/*	@GET
	public List<Message> getMessages(@QueryParam("year") int year, 
									 @QueryParam("start") int start,
									 @QueryParam("size") int size)
	{
		if(year > 0)
		{
			return messageService.getAllMessagesByYear(year);
		}
		if(start > 0 && size > 0)
		{
			start = start - 1;
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}*/
	
	@GET	//second way for upper commented code
	public List<Message> getMessages(@BeanParam MessageFilterBean mfb)
	{
		if(mfb.getYear() > 0)
		{
			return messageService.getAllMessagesByYear(mfb.getYear());
		}
		if(mfb.getStart() > 0 && mfb.getSize() > 0)
		{
			mfb.setStart(mfb.getStart() - 1);;
			return messageService.getAllMessagesPaginated(mfb.getStart(), mfb.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{id}")
	public Message getMessage(@PathParam("id") int id)
	{
		Message message = messageService.getMessage(id);
		return message;
	}

	/*
	Using Response class for returning response(Headers info, status codes etc.) in addMessage method
	*/
	@POST
	public Response addMessage(Message message, @Context UriInfo urlInfo )
	{
		Message newMessage = messageService.addMessage(message, urlInfo);
		URI url = urlInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getMessageId())).build();
		return Response.created(url)
						.entity(newMessage)
						.build();
	}
	
//	@POST
//	public Message addMessage(Message message)
//	{
//		
//		return messageService.addMessage(message);
//		
//	}
	
	@PUT
	@Path("/{id}")
	public Message updateMessage(@PathParam("id") int id, Message message)
	{
		message.setMessageId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteMessage(@PathParam("id") int id)
	{
		Message message = messageService.deleteMessage(id);
		return Response.status(Status.NO_CONTENT).entity(message).build();
	}
	
	@Path("/{messageId}/comments")
	public CommentResources getCommentResources()
	{
		return new CommentResources();
	}
	
	
	
}
