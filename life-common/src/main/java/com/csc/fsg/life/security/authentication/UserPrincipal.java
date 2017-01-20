package com.csc.fsg.life.security.authentication;

import java.util.List;

/**
 * The <code>UserPrincipal</code> class represents a a user being authenticated <br>  
 * It is populated with LDAP commonName as username , LDAP uid's as userid and other required attributes
 * like firstname,lastname, email etc
 */
public class UserPrincipal 
	extends AbstractPrincipal 
{
	private String firstName;
	private String lastName;
	private List<String> userIds;

	/**
	   Creates a UserPrincipal from a name and a list of ids.
	   @param name the ldap username once the user is authenticated  
	   @param userIds the ldap userid once the user is authenticated
	*/
	public UserPrincipal(String name, List<String> userIds) {
		super(name);
		this.userIds = userIds; 
	}	

	/**
	   Creates a UserPrincipal object with additional LDAP attributes.
	   @param name the ldap username once the user is authenticated.
	   @param userIds the ldap userid once the user is authenticated.
	   @param firstName the ldap first name.
	   @param lastName the ldap last name.
	**/
	public UserPrincipal(String name, List<String> userIds,String firstName,String lastName) 
	{
		this(name,userIds);
		this.firstName = firstName;
		this.lastName = lastName;
	}	

	/**
	   Returns the list of ids.
	**/
	public List<String> getUserIds() {
		return userIds;
	}

	/**
	   Returns the first name.
	   @return Returns the firstName.
	*/
	public String getFirstName() {
		return firstName;
	}


	/**
	   Returns the last name.
	   @return Returns the lastName.
	*/
	public String getLastName() {
		return lastName;
	}

	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("commonName:");
		buf.append(getName());
		buf.append(" firstName: ");
		buf.append(firstName);
		buf.append(" lastName: ");
		buf.append(lastName);
		buf.append("\n");
		for (int i = 0; i < userIds.size(); i++) {
			buf.append("UID ");
			buf.append(i);
			buf.append(':');
			buf.append(userIds.get(i));
		}
		
		return buf.toString();
	}
}
