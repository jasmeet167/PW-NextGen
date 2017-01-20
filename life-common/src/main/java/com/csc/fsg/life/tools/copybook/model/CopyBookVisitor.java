package com.csc.fsg.life.tools.copybook.model;

import java.util.Iterator;
import java.util.List;


/**
   Abstract class used to visit fields and groups in a copybook data structure.
**/
abstract public class CopyBookVisitor
{
	/**
	   The model data strcuture for the copybook visiting.
	**/
	protected CopyBookMapModel fieldMap;
	
	/**
	   Creates the visitor off of the copybook model.
	   @param fieldMap The model.
	**/
	public CopyBookVisitor(CopyBookMapModel fieldMap)
	{
		this.fieldMap = fieldMap;
	}
	
	/**
	   Main visit method.
	   @param userObject a user defined object that can be used to carry state.
	**/
	public void visit(Object userObject)
	{
		visit(null, userObject);
	}

	/**
	   Visits all children of a field.
	   @param parent The parent field.
	   @param userObject a user defined object that can be used to carry state.
	**/
	@SuppressWarnings("unchecked")
	protected void visit(FieldDefinitionEntry parent, Object userObject)
	{
		// Get the children for the specified parent.
		int parentId = CopyBookMapModel.ROOT;
		if (parent != null)
			parentId = parent.getFieldId();
		List children = fieldMap.getChildFields(parentId);
		if (children == null)
			return;

		// Iterate over the children.
		for (Iterator itt = children.iterator(); itt.hasNext();) {
			// Get the next field.
			Object nextObj = itt.next();
			if (nextObj instanceof List) {
				List redefineGroup = (List)nextObj;
				visitRedefineGroup(redefineGroup, userObject);
			}
			else {
				FieldDefinitionEntry field = (FieldDefinitionEntry)nextObj;
				if (field.isGroup())
					visitGroup(field, userObject);
				else
					visitField(field, userObject);
			}
		}
	}

	/**
	   Visits a redefine group.
	   @param redefineGroup The group.
	   @param userObject a user defined object that can be used to carry state.
	**/
	protected void visitRedefineGroup(List<FieldDefinitionEntry> redefineGroup, Object userObject)
	{
		for (Iterator<FieldDefinitionEntry> redefineItt = redefineGroup.iterator();redefineItt.hasNext();) {
			FieldDefinitionEntry field = (FieldDefinitionEntry)redefineItt.next();
			if (field.isGroup())
				visitGroup(field, userObject);
			else
				visitField(field, userObject);
		}
	}

	/**
	   Visits a group.  Override to do an custom work but be sure to call this 
	   method to continue visiting group children.
	   @param group The group.
	   @param userObject a user defined object that can be used to carry state.
	 **/
	protected void visitGroup(FieldDefinitionEntry group, Object userObject)
	{
		visit(group, userObject);
	}

	/**
	   Visits a field.
	   @param field The field.
	   @param userObject a user defined object that can be used to carry state.
	 **/
	abstract protected void visitField(FieldDefinitionEntry field, Object userObject);

}
