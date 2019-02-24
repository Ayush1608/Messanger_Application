package com.jaxrs.example.messanger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@XmlRootElement
public class Message 
{
	private int messageId;
	private String message;
	private Date createdOn;
	private String author;
	@XmlTransient
	private Map<Integer, Comments> comments = new HashMap<Integer, Comments>();
	private List<Links> links = new ArrayList<Links>();
	
	public Message() {}
	public Message(int messageId, String message, String author)
	{
		this.messageId = messageId;
		this.message = message;
		this.author = author;
		this.createdOn = new Date();
		this.comments.put(1, new Comments(1, "Default", this.author));
		constructLinksForMessageConstructor();
	}
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public Map<Integer, Comments> getComments() {
		return comments;
	}
	public void setComments(Map<Integer, Comments> comments) {
		this.comments = comments;
	}
	public List<Links> getLinks() {
		return links;
	}
	public void setLinks(List<Links> links) {
		this.links = links;
	}
	public void addLinks(String rel, String link) {
		Links l = new Links();
		l.setLink(link);
		l.setRel(rel);
		links.add(l);
	}
	private void constructLinksForMessageConstructor() {
		Links l1 = new Links();
		l1.setLink("http://localhost:8080/messanger/webapi/messages/" + this.messageId);
		l1.setRel("self");
		links.add(l1);
		Links l2 = new Links();
		l2.setLink("http://localhost:8080/messanger/webapi/profiles/" + this.author);
		l2.setRel("profiles");
		links.add(l2);
		Links l3 = new Links();
		l3.setLink("http://localhost:8080/messanger/webapi/messages/" + this.messageId + "/comments/");
		l3.setRel("comments");
		links.add(l3);
	}
}
