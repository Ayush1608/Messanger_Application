package com.jaxrs.example.messanger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jaxrs.example.messanger.model.Comments;
import com.jaxrs.example.messanger.service.CommentService;

@Path("/") //Optional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResources 
{
	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comments> getAllComments(@PathParam("messageId") int id)
	{
		return commentService.getComments(id);
	}
	
	@GET
	@Path("/{commentId}")
	public Comments getComments(@PathParam("messageId") int messageId, @PathParam("commentId") int commentId)
	{
		return commentService.getComment(messageId, commentId);
	}
	
	@POST
	public Comments addComments(@PathParam("messageId") int messageId, Comments comment)
	{
		return commentService.addComment(messageId, comment);
		
	}
	
	@PUT
	@Path("/{commentId}")
	public Comments updateComments(@PathParam("messageId") int messageId, @PathParam("commentId") int commentId, Comments comment)
	{
		comment.setCommentId(commentId);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{id}")
	public Comments deleteComments(@PathParam("messageId") int messageId, @PathParam("id") int id)
	{
		return commentService.deleteComment(messageId, id);
	}
}
