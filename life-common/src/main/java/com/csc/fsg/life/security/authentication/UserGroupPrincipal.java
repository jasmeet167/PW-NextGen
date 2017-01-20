package com.csc.fsg.life.security.authentication;

/**
 * The <code>UserGroupPrincipal<code> class represents a single group principal for a user being autheticated <br>
 * it is populated with LDAP groups that the given user is member of  
 */
public class UserGroupPrincipal extends AbstractPrincipal {

	/**
	   Creates the UserGroupPrincipal object with the group name.
	   @param groupName the name for this group principal
	 */
	public UserGroupPrincipal(String groupName) {
        super(groupName);
	}
}
