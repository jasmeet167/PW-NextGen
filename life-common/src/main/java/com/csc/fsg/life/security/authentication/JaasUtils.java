package com.csc.fsg.life.security.authentication;

import java.util.List;

/**'
   Interface to a utility class for JAAS.
**/
public interface JaasUtils
{
	/** 
	 * This method find all users (object for given base dn(distiguished
	 * name) using userid and password.
	 * <p>
	 * It creates UserPrincipal Object for each user in given directory trss
	 * with attributes <br>
	 * commonName,givenName (firstName) , sn (lastname ) and list of uids
	 **/
    public List<UserPrincipal> getAllUsers(String applicationName, String userID, String password);
}
