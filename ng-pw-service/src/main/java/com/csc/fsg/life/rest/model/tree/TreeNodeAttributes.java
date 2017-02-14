package com.csc.fsg.life.rest.model.tree;

import java.io.Serializable;

public class TreeNodeAttributes
	implements Serializable
{
	static private final long serialVersionUID = -6907760991625593079L;

	static private TreeNodeAttributes defaultAttributes = new TreeNodeAttributes();

	private boolean isDisabled = false;
	private boolean isInquiryAllowed = false;
	private boolean isUpdateAllowed = false;

	static public TreeNodeAttributes defaultInstance()
	{
		return defaultAttributes;
	}

	private TreeNodeAttributes()
	{
	}

	public TreeNodeAttributes(boolean isDisabled, boolean isInquiryAllowed, boolean isUpdateAllowed)
	{
		this.isDisabled = isDisabled;
		this.isInquiryAllowed = isInquiryAllowed;
		this.isUpdateAllowed = isUpdateAllowed;

		// Enforce consistency
		if (this.isDisabled) {
			this.isInquiryAllowed = false;
			this.isUpdateAllowed = false;
		}
		else if (this.isUpdateAllowed) {
			this.isInquiryAllowed = true;
		}
	}

	public boolean isDisabled()
	{
		return isDisabled;
	}

	public boolean isInquiryAllowed()
	{
		return isInquiryAllowed;
	}

	public boolean isUpdateAllowed()
	{
		return isUpdateAllowed;
	}
}
