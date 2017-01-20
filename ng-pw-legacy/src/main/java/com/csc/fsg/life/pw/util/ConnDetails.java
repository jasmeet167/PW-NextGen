package com.csc.fsg.life.pw.util;

import java.util.Date;

/* Modifications: T0129 */

public class ConnDetails {
	
	long creationTime = 0L;
	long closedTime = 0L;
	String url = null;
	//int id = -1;
	boolean released = false;
	
	public boolean isReleased() {
		return released;
	}
	public void setReleased(boolean released) {
		this.released = released;
		closedTime = System.currentTimeMillis();
	}
		
	public ConnDetails(String url,int id){
		try{
		creationTime = System.currentTimeMillis();
		this.url = url;
		//this.id = id;
		}catch (Exception e) {
		}
	}
	
	public String getStatus(){
		if (released)
			return "<P>"+new Date(creationTime).toString()+" - Connection to "+url+" used for "+((closedTime-creationTime)/1000)+" seconds </P><BR>";
		else
			return "<P><FONT COLOR='red'>"+new Date(creationTime).toString()+" - Connection to "+url+" is active for "+((System.currentTimeMillis()-creationTime)/1000)+" seconds</FONT></P><BR>";
	}
	
}
