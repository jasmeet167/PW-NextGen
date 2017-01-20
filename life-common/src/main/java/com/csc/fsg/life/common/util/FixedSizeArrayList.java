package com.csc.fsg.life.common.util;

import java.util.ArrayList;
import java.util.Collection;

/* $#(c)
 * This software contains trade secrets and confidential information,
 * which are proprietary to Computer Sciences Corporation. The use,
 * reproduction, distribution, or disclosure of this software, in whole
 * or in part, without the express written permission of Computer
 * Sciences Corporation is prohibited. This software is also an
 * unpublished work protected under the copyright laws of the United
 * States of America and other countries. If this software becomes
 * published, the following notice shall apply:
 * 
 * Copyright (c) 1994 - 2006 Computer Sciences Corporation,
 * all rights reserved.
 * 
 * This software may be modified only in accordance with the terms of the
 * applicable license agreement.  Any modifications may result in the 
 * voiding of applicable warranties and support services.
 */

/* Modifications: ENH1107 */

/**
 * Class limits the number of items in the list.
 * When item(s) is(are) added to the list, the list size is compared to the maxSize maximum size.
 * If adding new item(s) exceeds the declared maxSize size then an exception is thrown.
 */
public class FixedSizeArrayList<E> extends ArrayList<E> {

	// maximum size of the list
	private int maxSize; 
	private String errMsg = "Number of items cannot exceed the maximum size of the list, maximum size is ";

	/**
	 * Create the list with the specified maximum size.
	 * 
	 * @param maxSize
	 *            the maximum size this list can grow to.
	 */
	public FixedSizeArrayList(int maxSize)
	{
        super();
        this.maxSize = maxSize;
 	}

	/**
	 * Overrides List.add() method.
	 */
	@Override
	public boolean add(E obj)
	{
		if (isFull())
			throw new ArrayIndexOutOfBoundsException(errMsg + this.maxSize);
		return super.add(obj);
	}

	/**
	 * Overrides List.add() method.
	 */
	@Override
	public void add(int index, E obj)
	{
		if (isFull())
			throw new ArrayIndexOutOfBoundsException(errMsg + this.maxSize);
		super.add(index, obj);
	}
	
	/**
	 * Overrides List.addAll() method.
	 */
	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		if (isFull())
			throw new ArrayIndexOutOfBoundsException(errMsg + this.maxSize);
		return super.addAll(c);
	}

	/**
	 * Overrides List.addAll() method.
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c)
	{
		if (isFull())
			throw new ArrayIndexOutOfBoundsException(errMsg + this.maxSize);
		return super.addAll(index, c);
	}
	
	/**
	 * Returns true if this collection has reached its maximum size.
	 */
	public boolean isFull()
	{
		return size() == maxSize;
	}

	/**
	 * Returns the maximum size of this list.
	 */
	public int getMaxSize()
	{
		return maxSize;
	}
}
