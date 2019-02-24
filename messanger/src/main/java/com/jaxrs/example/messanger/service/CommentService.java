package com.jaxrs.example.messanger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ws.rs.NotFoundException;
import com.jaxrs.example.messanger.database.Database;
import com.jaxrs.example.messanger.model.Comments;
import com.jaxrs.example.messanger.model.Message;

public class CommentService {

	private Map<Integer, Message> messages = Database.getMessages();
	public List<Comments> getComments(int id) 
	{
		Map<Integer, Comments> comments = messages.get(id).getComments();
		return new ArrayList<Comments>(comments.values());
	}

	public Comments getComment(int messageId, int commentId) 
	{
		Message message = messages.get(messageId);
		if(message == null || message.getComments().get(commentId) == null)
			throw new NotFoundException();
		return message.getComments().get(commentId);
	}

	public Comments addComment(int messageId, Comments comment) 
	{
		Map<Integer, Comments> comments = messages.get(messageId).getComments();
		comment.setCommentId(comments.size() + 1);
		comment.setCreatedOn(new Date());
		comments.put(comment.getCommentId(), comment);
		return comment;
	}

	public Comments updateComment(int messageId, Comments comment) 
	{
		Map<Integer, Comments> comments = messages.get(messageId).getComments();
		comments.put(comment.getCommentId(), comment);
		return comment;
	}

	public Comments deleteComment(int id, int commentId) 
	{
		Map<Integer, Comments> comments = messages.get(id).getComments();
		return comments.remove(commentId);
	}

}
