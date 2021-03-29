package com.jaxrs.example.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriInfo;

import com.jaxrs.example.messanger.database.Database;
import com.jaxrs.example.messanger.exceptions.DataNotFoundException;
import com.jaxrs.example.messanger.model.Message;
import com.jaxrs.example.messanger.resources.CommentResources;
import com.jaxrs.example.messanger.resources.MessageResourse;
import com.jaxrs.example.messanger.resources.ProfileResourse;


public class MessageService 
{
	private static Map<Integer, Message> messages = Database.getMessages();
	
	public MessageService()
	{
		messages.put(1, new Message(1, "Ayush 1", "Ayush"));
		messages.put(2, new Message(2, "Ayush 2", "Ayush"));
	}
	
	public List<Message> getAllMessages()
	{
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesByYear(int year)
	{
		List<Message> messageForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values())
		{
			cal.setTime(message.getCreatedOn());
			if(cal.get(Calendar.YEAR)==year)
			{
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	public List<Message> getAllMessagesPaginated(int start, int size)
	{
		List<Message> messagesPaginated = new ArrayList<Message>(messages.values());
		if(start+size>messagesPaginated.size())
		{
			return new ArrayList<Message>();
		}
		return messagesPaginated.subList(start, start + size);
	}
	
	public Message addMessage(Message message, UriInfo uriInfo)
	{
		message.setMessageId(messages.size()+1);
		message.setCreatedOn(new Date());
		message.addLinks("self", getUriForSelf(uriInfo, message));
		message.addLinks("profile", getUriForProfile(uriInfo, message));
		message.addLinks("comments", getUriForComments(uriInfo, message));
		messages.put(message.getMessageId(), message);
		return message;
	}
	
	public Message getMessage(int id)
	{
		Message message = messages.get(id);
		if(message == null)
		{
			throw new DataNotFoundException("Message with message id " + id + " not found");
		}
		return message;
	}
	
	public Message updateMessage(Message message)
	{
		messages.put(message.getMessageId(), message);
		return messages.get(message.getMessageId());
	}
	
	public Message deleteMessage(int id)
	{
		return messages.remove(id);
	}
	private String getUriForSelf(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResourse.class)
				.path(Integer.toString(message.getMessageId()))
				.build()
				.toString();
	}
	
	private String getUriForProfile(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
				.path(ProfileResourse.class)
				.path(message.getAuthor())
				.build()
				.toString();
	}
	private String getUriForComments(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResourse.class)
				.path(MessageResourse.class, "getCommentResources")
				.path(CommentResources.class)
				.resolveTemplate("messageId", message.getMessageId())
				.build()
				.toString();
	}

}
