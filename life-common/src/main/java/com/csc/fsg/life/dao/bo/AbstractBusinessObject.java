package com.csc.fsg.life.dao.bo;

import java.sql.SQLException;
import java.util.HashMap;

import com.csc.fsg.life.dao.exceptions.BusinessObjectException;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/********************************************************
*  This class would serve as the base class for all
*  event specific JBO(s)
*********************************************************/

public abstract class AbstractBusinessObject 
	implements IBusinessObject
{
	@SuppressWarnings("unchecked")
	private HashMap<String, DAOModelCollection> allModels;

	@SuppressWarnings("unchecked")
	public 	AbstractBusinessObject ()
	{
		allModels = new HashMap<String, DAOModelCollection>();
	}

	/********************************************************
	* Use this method to get an existing ( or pre-created )
	* DAOModelCollection
	*********************************************************/
	@SuppressWarnings("unchecked")
	public DAOModelCollection getModel (String modelname)
        throws BusinessObjectException
	{
		return (DAOModelCollection)allModels.get(modelname);
	}

	/********************************************************
	* Use this method to invoke an action on the JBO
	*********************************************************/
	public void process ( BusinessObjectCommand command )
        throws BusinessObjectException, SQLException{};
}
