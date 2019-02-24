package com.jaxrs.example.messanger.database;

import java.util.HashMap;
import java.util.Map;

import com.jaxrs.example.messanger.model.Message;
import com.jaxrs.example.messanger.model.Profile;

public class Database 
{
	public static Map<Integer, Message> messages = new HashMap<Integer, Message>();
	public static Map<String, Profile> profiles  = new HashMap<String, Profile>();
	
	public static Map<Integer, Message> getMessages()
	{
		return messages;
	}
	public static Map<String, Profile> getProfiles()
	{
		return profiles;
	}
}
