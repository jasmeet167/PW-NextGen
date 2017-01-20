package com.csc.fsg.life.security.authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
   Singleton class thet is configured via spring.
   Holds Map of application -> Properties object.
   Properties object contains the group->role mappings for that application.
**/
public class GroupToRoleManager
{
	
	/** Singleton object **/
	private static GroupToRoleManager instance = null;


	/** Map of application -> Properties object **/
	private Map<String, Properties> applications = new HashMap<String, Properties>();

	/**
	   Access to singleton.
	**/
	public static GroupToRoleManager getInstance() 
	{
		if (instance == null)
			instance = new GroupToRoleManager();

		return instance;
	}

	private GroupToRoleManager()
	{
		instance = this;
	}

	/**
	   If created via spring, then set the instance at creation time.
	**/
	private GroupToRoleManager(Map<String, Properties> apps)
	{
		instance = this;
		applications = apps;
	}

	/**
	   Get the properties for the specified application
	**/
	public Properties get(String application)
	{
		Properties p = applications.get(application);
		if (p == null)
		{
			p = new Properties();
			applications.put(application, p);
		}

		return p;
	}
}
