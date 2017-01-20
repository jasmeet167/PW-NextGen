package com.csc.fsg.life.configuration;


/********************************************************
*  This class is for Polling process usage.             
*  The getUserConfigFile() and getSystemConfigFile()    
*  provides access for PollingConfig.java to call these 
*  methods to get the configuration files.              
*********************************************************/
public final class ConfigFileNames {

	private static String userConfigFile, systemConfigFile;


	/**
	   Returns the userConfigFile file name registered.
	   @return The userConfigFile file name registered.
	**/
	public static String getUserConfigFile() {
		return userConfigFile;
	}

	/**
	   Sets the user config file.
	   @param newUserConfigFile The userconfigfile as parameter.                  
	**/
	public static void setUserConfigFile(String newUserConfigFile) {
		userConfigFile = newUserConfigFile;
	}

	/**
	   Returns the system config file registered.
	   @return The systemConfigFile registered.
	**/
	public static String getSystemConfigFile() {
		return systemConfigFile;
	}

	/**
	   Sets the system config file.
	   @param newSystemConfigFile The new system config file name.
	**/
	public static void setSystemConfigFile(String newSystemConfigFile) {
		systemConfigFile = newSystemConfigFile;
	}
}
