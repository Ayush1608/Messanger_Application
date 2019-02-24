package com.jaxrs.example.messanger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jaxrs.example.messanger.database.Database;
import com.jaxrs.example.messanger.model.Profile;

public class ProfileService 
{
	public ProfileService()
	{
		profiles.put("Ayush", new Profile(1, "Ayush1", "Ayush", "Jain"));
		profiles.put("Piyush", new Profile(2, "Ayush2", "Piyush", "Jain"));
	}
	
	public static Map<String, Profile> profiles = Database.getProfiles();
	
	public List<Profile> getAllProfiles()
	{
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile addProfile(Profile profile)
	{
		profile.setProfileId(profiles.size()+1);
		profile.setCreated(new Date());
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile getProfile(String profileName)
	{
		return profiles.get(profileName);
	}
	public Profile updateProfile(Profile profile)
	{
		profiles.put(profile.getProfileName(), profile);
		return profiles.get(profile.getProfileName());
	}
	public Profile deleteProfile(String profileName)
	{
		return profiles.remove(profileName);
	}
}

