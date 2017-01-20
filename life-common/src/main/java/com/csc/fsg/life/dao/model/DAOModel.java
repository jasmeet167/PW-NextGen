package com.csc.fsg.life.dao.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.csc.fsg.life.dao.dataaccessor.DataAccessor;

/* Modifications: T0150 */

/**
 * This class would serve as the base class for all generated 
 * DataAccessor Models
 */
public abstract class DAOModel
{
	/** Constant for models that were created in memory but are marked to be deleted. **/
	public static int CREATED_IN_MEMORY_DELETED = -1;

	/** Constant for models that were CREATED_IN_MEMORY. **/
	public static final int CREATED_IN_MEMORY = 1;

	/** Constant for models that are READ_FROM_DB. **/
	public static final int READ_FROM_DB = 2;

	/** Constant for models that are READ_FROM_DB but have been updated. **/
	public static final int READ_FROM_DB_UPDATED = 3;

	/** Constant for models that are READ_FROM_DB and are marked to be deleted from the db. **/
	public static final int READ_FROM_DB_DELETED = 4;

	/** Constant for models that are to be added to the db. **/
	public static int ADD_TO_DB = 5;

	private int state;

	/** The environment used. **/
	protected String envKey;

	/**
	   Create a new instance of a model in memory.
	**/
	public DAOModel()
	{
		state = CREATED_IN_MEMORY;
	}

	/**
	   Create a new instance of a model in memory.
	**/
	public DAOModel(String envKey)
	{
		this.envKey = envKey;
		state = CREATED_IN_MEMORY;
	}

	/**
	   Create a new instance of a model with a specified state.
	**/
	public DAOModel(int state)
	{
		this.state = state;
	}

	/**
	   Returns the model state.
	   @return the model state.
	**/ 
	public int getModelState ()
	{
		return state;
	}

	/**
	   Sets the model state.
	   @param state the new state.
	**/
	public void setModelState(int state)
	{
		this.state = state;
	}

	/**
	   Create a new instance of this model. 
	**/
	public DAOModel createNew()
	{
		return null;
	}

	/**
	   Returns the ModelName property.
	   @return the ModelName property.
	**/
	public String getModelName()
	{
		return null;
	}

	/**
	   Returns the DataAccessorObject property.
	   @return the DataAccessorObject property.
	**/
	public DataAccessor getDataAccessorObject()
	{
		return null;
	}

	/**
	   Has the data changed since it was read from the db.
	**/
	public boolean hasChanged()
	{
		return false;
	}

	/**
	   Has a key field changed.
	**/
	public boolean hasKeysChanged()
	{
		return false;
	}

	/**
	   Has a key field changed.
	**/
	public boolean hasKeysChanged(Object aObject)
	{
		return false;
	}

	/**
	   Returns the OldCopy property.
	   @return the OldCopy property.
	**/
    public DAOModel getOldCopy()
    {
    	return null;
    }
    
	/**
	   Ensure an object (as a string) is not null.
	**/
    public static String toSafeString(Object o) {
        if (o != null) {
            return o.toString();
        }
        else {
            return "";
        }
    }
    
	/**
	   Convert a string to an integer. 
	**/
    public static Integer toInteger(String s) {
        if (s == null) {
            return null;
        }
        else {
            if (s.trim().length() == 0) {
                return Integer.valueOf(-1);
            }
            else {
                try {
                    return Integer.valueOf(s);
                }
                catch (Exception e) {
                    return null;
                }
            }
        }
    }
    
	/**
	   Convert a string to a Float. 
	**/
    public static Float toFloat(String s) {
        if (s == null) {
            return null;
        }
        else {
            if (s.trim().length() == 0) {
                return new Float(-1);
            }
            else {
                try {
                    return Float.valueOf(s);
                }
                catch (Exception e) {
                    return null;
                }
            }
        }
    }
    
	/**
	   Convert a string to a Double. 
	**/
    public static Double toDouble(String s) {
        if (s == null) {
            return null;
        }
        else {
            if (s.trim().length() == 0) {
                return new Double(-1);
            }
            else {
                try {
                    return Double.valueOf(s);
                }
                catch (Exception e) {
                    return null;
                }
            }
        }
    }

	/**
	   Convert a string to a Long. 
	**/
	public static Long toLong(String s) {
		if (s == null) {
			return null;
		}
		else {
			if (s.trim().length() == 0) {
				return Long.valueOf(-1);
			}
			else {
				try {
					return Long.valueOf(s);
				}
				catch (Exception e) {
					return null;
				}
			}
		}
	}
    
	/**
	   Convert a string to a Boolean. 
	**/
    public static Boolean toBoolean(String s) {
        if ((s == null) 
        || (s.trim().length() == 0)) {
            return null;
        }
        else {
            try {
                return Boolean.valueOf(s);
            }
            catch (Exception e) {
                return null;
            }
        }
    }
    
	/**
	   Convert a string to a date. 
	**/
    public static java.sql.Date toDate(String s) {
        if ((s == null) 
        || (s.trim().length() == 0)) {
            return null;
        }
        else {
            try {
                return java.sql.Date.valueOf(s);
            }
            catch (Exception e) {
                return null;
            }
        }
    }
    
	/**
	   Convert a string to a Time. 
	**/
    public static java.sql.Time toTime(String s) {
        if ((s == null) 
        || (s.trim().length() == 0)) {
            return null;
        }
        else {
            try {
                return java.sql.Time.valueOf(s);
            }
            catch (Exception e) {
                return null;
            }
        }
    }
    
	/**
	   Convert a string to a TimeStamp.
	**/
    public static java.sql.Timestamp toTimeStamp(String s) {
        if ((s == null) 
        || (s.trim().length() == 0)) {
            return null;
        }
        else {
            try {
                return java.sql.Timestamp.valueOf(s);
            }
            catch (Exception e) {
                return null;
            }
        }
    }
    
    /**
     *  Overridden in subclasses in order to create an xml representation
     *  of the model.
     */
    public Element toXML(Document doc) {
        return null;
    }
    
    /**
     *  Overridden in subclasses in order to instantiate a model from an 
     *  XML representation.
     */
    public DAOModel fromXML(Element aElement) {
        return null;
    }
	
	/**
	 * @return the envKey
	 */
	public String getEnvKey() {
	
		return envKey;
	}

	/**
	 * @param envKey the envKey to set
	 */
	public void setEnvKey(String envKey) {
	
		this.envKey = envKey;
	}
}

