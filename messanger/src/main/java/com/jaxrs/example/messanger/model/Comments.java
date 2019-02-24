package com.jaxrs.example.messanger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Comments 
{
	private int commentId;
	private String comment;
	private Date createdOn;
	private String author;
	
	public Comments() {}
	public Comments(int commentId, String comment, String author)
	{
		this.commentId = commentId;
		this.comment = comment;
		this.author = author;
		this.createdOn = new Date();
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
