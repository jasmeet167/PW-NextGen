package com.csc.fsg.life.biz.copyobject;

import java.util.ArrayList;
import java.util.List;

import com.csc.fsg.life.biz.bo.HistoryDetailItem;
import com.csc.fsg.life.biz.bo.XgCommArea;
import com.csc.fsg.life.biz.service.ServiceValidationException;
import com.csc.fsg.life.common.util.ToStringBuilder;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.convert.Converter;

/* Modifications: T0088, ENH988, P0158, T0140, P0200, T0175, T0166 */

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
 * Copyright (c) 1994 - 2014 Computer Sciences Corporation,
 * all rights reserved.
 * 
 * This software may be modified only in accordance with the terms of the
 * applicable license agreement.  Any modifications may result in the 
 * voiding of applicable warranties and support services.
 */
 
/**
 *  Copy Object class that represents copybook CXGCOMM1.
 */
public class XgCommAreaCopyObject
    extends CopyObject 
    implements XgCommArea 
{

    /**
	 * Default XgCommAreaCopyObject constructor.  Creates this object and initializes all fields
	 * and sub-objects with default values.  
	 * @throws CopyObjectException If there is an initialization problem.
	 */
    public XgCommAreaCopyObject()
        throws CopyObjectException 
    {
        bytes = new byte[500];

        setCicsTrx("");
        setDelim1("");
        setClientInData("");
        setDelim2("");
        setIcTdStartup("");
        setDelim3("");
        setTrxDelay(Long.valueOf(0));
        setRequestTimestamp("");
        setSystemId("");
        setEventId("");
        setEventMode("");
        setUserId("");
        setUserPwd(new char[0]);
        setUserIp("");
        setTrxCode("");
        setProdCode("");
        setTaskNumber("");
        setTaskInvocation("");
        setMrocontrolindicator("");
        setMrocontrolaorregion("");
        setMrotsqueuename("");
        setMrocontroluserdata("");
        setMessagekey("");
        setInputoperationcode("");
        setHighestinputrecordseq(Long.valueOf(0));
        setErroroverrideind("");
        setRtdcind("");
        setReturnoverriddenerrorsind("");
        setRestrictcoderemovalind("");
        setSuspendcoderemovalind("");
        setDcrcreviseind("");
        setDcrcrevisedkey("");
        setFilecontrol("");
        setBrDatabase("");
        setBrSchema("");
        setApDatabase("");
        setApSchema("");
        setCommitStrategy("");
        setVcslogSwitch("");
        setVcslogFormat("");
        setCompanyCode("");
        setReturnDataAreasPassed(Long.valueOf(0));
        setVcslogDest("");
        setVcslogDetailSw("");
        setActivityId("");
        setSequenceNo(Long.valueOf(0));
        replaceBytes(337, 352, convertToHost(Converter.ALPHANUM, 15, 0, ""));
        setResponseTimestamp("");
        setReturndataarealength(Long.valueOf(0));
        setOutputoperationcode("");
        setHighestoutputrecordseq(Long.valueOf(0));
        setReturnstatus("");
        setErrorcode("");
        setResponseSequenceNo(Long.valueOf(0));
        replaceBytes(406, 500, convertToHost(Converter.ALPHANUM, 94, 0, ""));
    }
    
    /**
	 * Creates this object and initializes all fields and sub-objects 
	 * with default values.   In addition, initializes the user context.
	 * @param userContext the user context.
	 * @throws CopyObjectException If there is an initialization problem.
	 */
    @Override
    public void init(UserContext userContext) 
        throws CopyObjectException
    {
        if (initialized)
        	return;
        	
        setUserContext(userContext);

        initialized = true;
    }

    /**
	 * Create a XgCommAreaCopyObject from the specified byte array.
	 * @param userContext The user context.
	 * @param copybookData The byte array to initialize to.
	 * @throws CopyObjectException If initialization fails.
	 */
    public XgCommAreaCopyObject(UserContext userContext, byte[] copybookData) 
        throws CopyObjectException
    {
        this();
    	init(userContext, copybookData);
    }
    
    /**
	 * Initialization method for this copy object.  This initializes the copy object 
	 * using the data contained in the given byte array.
	 * @param userContext The user context.
	 * @param copybookData The byte array to initialize to.
	 * @throws CopyObjectException If initialization fails.
	 */
    @Override
    public void init(UserContext userContext, byte[] copybookData)
    	throws CopyObjectException
    {
        if (copybookData.length > 500)
            throw new CopyObjectException("Invalid length for XgCommArea copybook, length must be less than 500 but length is " + copybookData.length);
               
        // initialize byte array
        init(userContext);
        
        // copy the given copybook data into byte array
        System.arraycopy(copybookData, 0, bytes, 0, copybookData.length);
        
    }

	/**
	 * Returns the copybook name.
	 * @return the copybook name.
	 */
	@Override
	public String getCopybookName()
	{
		return "CXGCOMM1";
	}

	/**
	 * Returns a byte array that represents the copybook.  This byte array is
	 * already converted to the appropriate format (ASCII, EBCIDIC, etc.) 
	 * as defined by the converter.
	 * @return a byte array that represents the copybook. 
	 * @throws CopyObjectException If conversion fails.
	 */
    @Override
    public byte[] toBytes()
        throws CopyObjectException
    {
        return bytes;
    }
    
    /**
     * Get the value of cicsTrx.
	 * @return the value of cicsTrx.
	 * @see #setCicsTrx
     */
    public String getCicsTrx()
    {
        return convertFromHost(Converter.ALPHANUM, 4, 0, getBytes(0, 4)).trim();
    }
    	
    /**
     * Set the value of cicsTrx.
	 * @param value the new value of cicsTrx.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getCicsTrx
     */
    public void setCicsTrx(String value)
        throws CopyObjectException
    {
        validateString("cicsTrx", 4, value);
        replaceBytes(0, 4, convertToHost(Converter.ALPHANUM, 4, 0, value));
    }
    
    /**
     * Get the value of delim1.
	 * @return the value of delim1.
	 * @see #setDelim1
     */
    public String getDelim1()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(4, 5)).trim();
    }
    	
    /**
     * Set the value of delim1.
	 * @param value the new value of delim1.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getDelim1
     */
    public void setDelim1(String value)
        throws CopyObjectException
    {
        validateString("delim1", 1, value);
        replaceBytes(4, 5, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of clientInData.
	 * @return the value of clientInData.
	 * @see #setClientInData
     */
    public String getClientInData()
    {
        return convertFromHost(Converter.ALPHANUM, 35, 0, getBytes(5, 40)).trim();
    }
    	
    /**
     * Set the value of clientInData.
	 * @param value the new value of clientInData.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getClientInData
     */
    public void setClientInData(String value)
        throws CopyObjectException
    {
        validateString("clientInData", 35, value);
        replaceBytes(5, 40, convertToHost(Converter.ALPHANUM, 35, 0, value));
    }
    
    /**
     * Get the value of delim2.
	 * @return the value of delim2.
	 * @see #setDelim2
     */
    public String getDelim2()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(40, 41)).trim();
    }
    	
    /**
     * Set the value of delim2.
	 * @param value the new value of delim2.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getDelim2
     */
    public void setDelim2(String value)
        throws CopyObjectException
    {
        validateString("delim2", 1, value);
        replaceBytes(40, 41, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of icTdStartup.
	 * @return the value of icTdStartup.
	 * @see #setIcTdStartup
     */
    public String getIcTdStartup()
    {
        return convertFromHost(Converter.ALPHANUM, 2, 0, getBytes(41, 43)).trim();
    }
    	
    /**
     * Set the value of icTdStartup.
	 * @param value the new value of icTdStartup.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getIcTdStartup
     */
    public void setIcTdStartup(String value)
        throws CopyObjectException
    {
        validateString("icTdStartup", 2, value);
        replaceBytes(41, 43, convertToHost(Converter.ALPHANUM, 2, 0, value));
    }
    
    /**
     * Get the value of delim3.
	 * @return the value of delim3.
	 * @see #setDelim3
     */
    public String getDelim3()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(43, 44)).trim();
    }
    	
    /**
     * Set the value of delim3.
	 * @param value the new value of delim3.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getDelim3
     */
    public void setDelim3(String value)
        throws CopyObjectException
    {
        validateString("delim3", 1, value);
        replaceBytes(43, 44, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of trxDelay.
	 * @return the value of trxDelay.
	 * @see #setTrxDelay
     */
    public Long getTrxDelay()
    {
        return Long.valueOf(convertFromHost(Converter.NUMERIC, 6, 0, getBytes(44, 50)));
    }
    	
    /**
     * Set the value of trxDelay.
	 * @param value the new value of trxDelay.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getTrxDelay
     */
    public void setTrxDelay(Long value)
        throws CopyObjectException
    {
        if (value == null)
            value = Long.valueOf(0);
        else
            validateLong("trxDelay", 6, 0, value);
        replaceBytes(44, 50, convertToHost(Converter.NUMERIC, 6, 0, value.toString()));
    }
    
    /**
     * Get the value of requestTimestamp.
	 * @return the value of requestTimestamp.
	 * @see #setRequestTimestamp
     */
    public String getRequestTimestamp()
    {
        return convertFromHost(Converter.ALPHANUM, 26, 0, getBytes(50, 76)).trim();
    }
    	
    /**
     * Set the value of requestTimestamp.
	 * @param value the new value of requestTimestamp.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getRequestTimestamp
     */
    public void setRequestTimestamp(String value)
        throws CopyObjectException
    {
        validateString("requestTimestamp", 26, value);
        replaceBytes(50, 76, convertToHost(Converter.ALPHANUM, 26, 0, value));
    }
    
    /**
     * Get the value of systemId.
	 * @return the value of systemId.
	 * @see #setSystemId
     */
    public String getSystemId()
    {
        return convertFromHost(Converter.ALPHANUM, 5, 0, getBytes(76, 81)).trim();
    }
    	
    /**
     * Set the value of systemId.
	 * @param value the new value of systemId.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getSystemId
     */
    public void setSystemId(String value)
        throws CopyObjectException
    {
        validateString("systemId", 5, value);
        replaceBytes(76, 81, convertToHost(Converter.ALPHANUM, 5, 0, value));
    }
    
    /**
     * Get the value of eventId.
	 * @return the value of eventId.
	 * @see #setEventId
     */
    public String getEventId()
    {
        return convertFromHost(Converter.ALPHANUM, 5, 0, getBytes(81, 86)).trim();
    }
    	
    /**
     * Set the value of eventId.
	 * @param value the new value of eventId.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getEventId
     */
    public void setEventId(String value)
        throws CopyObjectException
    {
        validateString("eventId", 5, value);
        replaceBytes(81, 86, convertToHost(Converter.ALPHANUM, 5, 0, value));
    }
    
    /**
     * Get the value of eventMode.
	 * @return the value of eventMode.
	 * @see #setEventMode
     */
    public String getEventMode()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(86, 87)).trim();
    }
    	
    /**
     * Set the value of eventMode.
	 * @param value the new value of eventMode.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getEventMode
     */
    public void setEventMode(String value)
        throws CopyObjectException
    {
        validateString("eventMode", 1, value);
        replaceBytes(86, 87, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of userId.
	 * @return the value of userId.
	 * @see #setUserId
     */
    public String getUserId()
    {
        return convertFromHost(Converter.ALPHANUM, 20, 0, getBytes(87, 107)).trim();
    }
    	
    /**
     * Set the value of userId.
	 * @param value the new value of userId.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getUserId
     */
    public void setUserId(String value)
        throws CopyObjectException
    {
        validateString("userId", 20, value);
        replaceBytes(87, 107, convertToHost(Converter.ALPHANUM, 20, 0, value));
    }
    
    /**
     * Set the value of userPwd.
	 * @param value the new value of userPwd.
	 * @throws CopyObjectException If conversion fails.
     */
    public void setUserPwd(char[] value)
        throws CopyObjectException
    {
        validateCharArray("userPwd", 20, value);
        
        // this password field must be treated as mixed case (not forced to UpperCase),
        // since passwords are case sensitive
        replaceBytes(107, 127, convertToHost(Converter.MIXEDCASE, 20, value));
    }
    
    /**
     * Get the value of userIp.
	 * @return the value of userIp.
	 * @see #setUserIp
     */
    public String getUserIp()
    {
        return convertFromHost(Converter.ALPHANUM, 15, 0, getBytes(127, 142)).trim();
    }
    	
    /**
     * Set the value of userIp.
	 * @param value the new value of userIp.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getUserIp
     */
    public void setUserIp(String value)
        throws CopyObjectException
    {
        validateString("userIp", 15, value);
        replaceBytes(127, 142, convertToHost(Converter.ALPHANUM, 15, 0, value));
    }
    
    /**
     * Get the value of trxCode.
	 * @return the value of trxCode.
	 * @see #setTrxCode
     */
    public String getTrxCode()
    {
        return convertFromHost(Converter.ALPHANUM, 4, 0, getBytes(142, 146)).trim();
    }
    	
    /**
     * Set the value of trxCode.
	 * @param value the new value of trxCode.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getTrxCode
     */
    public void setTrxCode(String value)
        throws CopyObjectException
    {
        validateString("trxCode", 4, value);
        replaceBytes(142, 146, convertToHost(Converter.ALPHANUM, 4, 0, value));
    }
    
    /**
     * Get the value of prodCode.
	 * @return the value of prodCode.
	 * @see #setProdCode
     */
    public String getProdCode()
    {
        return convertFromHost(Converter.ALPHANUM, 2, 0, getBytes(146, 148)).trim();
    }
    	
    /**
     * Set the value of prodCode.
	 * @param value the new value of prodCode.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getProdCode
     */
    public void setProdCode(String value)
        throws CopyObjectException
    {
        validateString("prodCode", 2, value);
        replaceBytes(146, 148, convertToHost(Converter.ALPHANUM, 2, 0, value));
    }
    
    /**
     * Get the value of taskNumber.
	 * @return the value of taskNumber.
	 * @see #setTaskNumber
     */
    public String getTaskNumber()
    {
        return convertFromHost(Converter.ALPHANUM, 4, 0, getBytes(148, 152)).trim();
    }
    	
    /**
     * Set the value of taskNumber.
	 * @param value the new value of taskNumber.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getTaskNumber
     */
    public void setTaskNumber(String value)
        throws CopyObjectException
    {
        validateString("taskNumber", 4, value);
        replaceBytes(148, 152, convertToHost(Converter.ALPHANUM, 4, 0, value));
    }
    
    /**
     * Get the value of taskInvocation.
	 * @return the value of taskInvocation.
	 * @see #setTaskInvocation
     */
    public String getTaskInvocation()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(152, 153)).trim();
    }
    	
    /**
     * Set the value of taskInvocation.
	 * @param value the new value of taskInvocation.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getTaskInvocation
     */
    public void setTaskInvocation(String value)
        throws CopyObjectException
    {
        validateString("taskInvocation", 1, value);
        replaceBytes(152, 153, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of mrocontrolindicator.
	 * @return the value of mrocontrolindicator.
	 * @see #setMrocontrolindicator
     */
    public String getMrocontrolindicator()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(153, 154)).trim();
    }
    	
    /**
     * Set the value of mrocontrolindicator.
	 * @param value the new value of mrocontrolindicator.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getMrocontrolindicator
     */
    public void setMrocontrolindicator(String value)
        throws CopyObjectException
    {
        validateString("mrocontrolindicator", 1, value);
        replaceBytes(153, 154, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of mrocontrolaorregion.
	 * @return the value of mrocontrolaorregion.
	 * @see #setMrocontrolaorregion
     */
    public String getMrocontrolaorregion()
    {
        return convertFromHost(Converter.ALPHANUM, 4, 0, getBytes(154, 158)).trim();
    }
    	
    /**
     * Set the value of mrocontrolaorregion.
	 * @param value the new value of mrocontrolaorregion.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getMrocontrolaorregion
     */
    public void setMrocontrolaorregion(String value)
        throws CopyObjectException
    {
        validateString("mrocontrolaorregion", 4, value);
        replaceBytes(154, 158, convertToHost(Converter.ALPHANUM, 4, 0, value));
    }
    
    /**
     * Get the value of mrotsqueuename.
	 * @return the value of mrotsqueuename.
	 * @see #setMrotsqueuename
     */
    public String getMrotsqueuename()
    {
        return convertFromHost(Converter.ALPHANUM, 8, 0, getBytes(158, 166)).trim();
    }
    	
    /**
     * Set the value of mrotsqueuename.
	 * @param value the new value of mrotsqueuename.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getMrotsqueuename
     */
    public void setMrotsqueuename(String value)
        throws CopyObjectException
    {
        validateString("mrotsqueuename", 8, value);
        replaceBytes(158, 166, convertToHost(Converter.ALPHANUM, 8, 0, value));
    }
    
    /**
     * Get the value of mrocontroluserdata.
	 * @return the value of mrocontroluserdata.
	 * @see #setMrocontroluserdata
     */
    public String getMrocontroluserdata()
    {
        return convertFromHost(Converter.ALPHANUM, 10, 0, getBytes(166, 176)).trim();
    }
    	
    /**
     * Set the value of mrocontroluserdata.
	 * @param value the new value of mrocontroluserdata.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getMrocontroluserdata
     */
    public void setMrocontroluserdata(String value)
        throws CopyObjectException
    {
        validateString("mrocontroluserdata", 10, value);
        replaceBytes(166, 176, convertToHost(Converter.ALPHANUM, 10, 0, value));
    }
    
    /**
     * Get the value of messagekey.
	 * @return the value of messagekey.
	 * @see #setMessagekey
     */
    public String getMessagekey()
    {
        return convertFromHost(Converter.ALPHANUM, 20, 0, getBytes(176, 196)).trim();
    }
    	
    /**
     * Set the value of messagekey.
	 * @param value the new value of messagekey.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getMessagekey
     */
    public void setMessagekey(String value)
        throws CopyObjectException
    {
        validateString("messagekey", 20, value);
        replaceBytes(176, 196, convertToHost(Converter.ALPHANUM, 20, 0, value));
    }
    
    /**
     * Get the value of inputoperationcode.
	 * @return the value of inputoperationcode.
	 * @see #setInputoperationcode
     */
    public String getInputoperationcode()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(196, 197)).trim();
    }
    	
    /**
     * Set the value of inputoperationcode.
	 * @param value the new value of inputoperationcode.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getInputoperationcode
     */
    public void setInputoperationcode(String value)
        throws CopyObjectException
    {
        validateString("inputoperationcode", 1, value);
        replaceBytes(196, 197, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of highestinputrecordseq.
	 * @return the value of highestinputrecordseq.
	 * @see #setHighestinputrecordseq
     */
    public Long getHighestinputrecordseq()
    {
        return Long.valueOf(convertFromHost(Converter.NUMERIC, 4, 0, getBytes(197, 201)));
    }
    	
    /**
     * Set the value of highestinputrecordseq.
	 * @param value the new value of highestinputrecordseq.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getHighestinputrecordseq
     */
    public void setHighestinputrecordseq(Long value)
        throws CopyObjectException
    {
        if (value == null)
            value = Long.valueOf(0);
        else
            validateLong("highestinputrecordseq", 4, 0, value);
        replaceBytes(197, 201, convertToHost(Converter.NUMERIC, 4, 0, value.toString()));
    }
    
    /**
     * Get the value of erroroverrideind.
	 * @return the value of erroroverrideind.
	 * @see #setErroroverrideind
     */
    public String getErroroverrideind()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(201, 202)).trim();
    }
    	
    /**
     * Set the value of erroroverrideind.
	 * @param value the new value of erroroverrideind.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getErroroverrideind
     */
    public void setErroroverrideind(String value)
        throws CopyObjectException
    {
        validateString("erroroverrideind", 1, value);
        replaceBytes(201, 202, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of rtdcind.
	 * @return the value of rtdcind.
	 * @see #setRtdcind
     */
    public String getRtdcind()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(202, 203)).trim();
    }
    	
    /**
     * Set the value of rtdcind.
	 * @param value the new value of rtdcind.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getRtdcind
     */
    public void setRtdcind(String value)
        throws CopyObjectException
    {
        validateString("rtdcind", 1, value);
        replaceBytes(202, 203, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of returnoverriddenerrorsind.
	 * @return the value of returnoverriddenerrorsind.
	 * @see #setReturnoverriddenerrorsind
     */
    public String getReturnoverriddenerrorsind()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(203, 204)).trim();
    }
    	
    /**
     * Set the value of returnoverriddenerrorsind.
	 * @param value the new value of returnoverriddenerrorsind.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getReturnoverriddenerrorsind
     */
    public void setReturnoverriddenerrorsind(String value)
        throws CopyObjectException
    {
        validateString("returnoverriddenerrorsind", 1, value);
        replaceBytes(203, 204, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of restrictcoderemovalind.
	 * @return the value of restrictcoderemovalind.
	 * @see #setRestrictcoderemovalind
     */
    public String getRestrictcoderemovalind()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(204, 205)).trim();
    }
    	
    /**
     * Set the value of restrictcoderemovalind.
	 * @param value the new value of restrictcoderemovalind.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getRestrictcoderemovalind
     */
    public void setRestrictcoderemovalind(String value)
        throws CopyObjectException
    {
        validateString("restrictcoderemovalind", 1, value);
        replaceBytes(204, 205, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of suspendcoderemovalind.
	 * @return the value of suspendcoderemovalind.
	 * @see #setSuspendcoderemovalind
     */
    public String getSuspendcoderemovalind()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(205, 206)).trim();
    }
    	
    /**
     * Set the value of suspendcoderemovalind.
	 * @param value the new value of suspendcoderemovalind.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getSuspendcoderemovalind
     */
    public void setSuspendcoderemovalind(String value)
        throws CopyObjectException
    {
        validateString("suspendcoderemovalind", 1, value);
        replaceBytes(205, 206, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of dcrcreviseind.
	 * @return the value of dcrcreviseind.
	 * @see #setDcrcreviseind
     */
    public String getDcrcreviseind()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(206, 207)).trim();
    }
    	
    /**
     * Set the value of dcrcreviseind.
	 * @param value the new value of dcrcreviseind.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getDcrcreviseind
     */
    public void setDcrcreviseind(String value)
        throws CopyObjectException
    {
        validateString("dcrcreviseind", 1, value);
        replaceBytes(206, 207, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of dcrcrevisedkey.
	 * @return the value of dcrcrevisedkey.
	 * @see #setDcrcrevisedkey
     */
    public String getDcrcrevisedkey()
    {
        return convertFromHost(Converter.ALPHANUM, 56, 0, getBytes(207, 263)).trim();
    }
    	
    /**
     * Set the value of dcrcrevisedkey.
	 * @param value the new value of dcrcrevisedkey.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getDcrcrevisedkey
     */
    public void setDcrcrevisedkey(String value)
        throws CopyObjectException
    {
        validateString("dcrcrevisedkey", 56, value);
        replaceBytes(207, 263, convertToHost(Converter.ALPHANUM, 56, 0, value));
    }
    
    /**
     * Get the value of filecontrol.
	 * @return the value of filecontrol.
	 * @see #setFilecontrol
     */
    public String getFilecontrol()
    {
        return convertFromHost(Converter.ALPHANUM, 6, 0, getBytes(263, 269)).trim();
    }
    	
    /**
     * Set the value of filecontrol.
	 * @param value the new value of filecontrol.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getFilecontrol
     */
    public void setFilecontrol(String value)
        throws CopyObjectException
    {
        validateString("filecontrol", 6, value);
        replaceBytes(263, 269, convertToHost(Converter.ALPHANUM, 6, 0, value));
    }
    
    /**
     * Get the value of brDatabase.
	 * @return the value of brDatabase.
	 * @see #setBrDatabase
     */
    public String getBrDatabase()
    {
        return convertFromHost(Converter.ALPHANUM, 8, 0, getBytes(269, 277)).trim();
    }
    	
    /**
     * Set the value of brDatabase.
	 * @param value the new value of brDatabase.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getBrDatabase
     */
    public void setBrDatabase(String value)
        throws CopyObjectException
    {
        validateString("brDatabase", 8, value);
        replaceBytes(269, 277, convertToHost(Converter.ALPHANUM, 8, 0, value));
    }
    
    /**
     * Get the value of brSchema.
	 * @return the value of brSchema.
	 * @see #setBrSchema
     */
    public String getBrSchema()
    {
        return convertFromHost(Converter.ALPHANUM, 8, 0, getBytes(277, 285)).trim();
    }
    	
    /**
     * Set the value of brSchema.
	 * @param value the new value of brSchema.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getBrSchema
     */
    public void setBrSchema(String value)
        throws CopyObjectException
    {
        validateString("brSchema", 8, value);
        replaceBytes(277, 285, convertToHost(Converter.ALPHANUM, 8, 0, value));
    }
    
    /**
     * Get the value of apDatabase.
	 * @return the value of apDatabase.
	 * @see #setApDatabase
     */
    public String getApDatabase()
    {
        return convertFromHost(Converter.ALPHANUM, 8, 0, getBytes(285, 293)).trim();
    }
    	
    /**
     * Set the value of apDatabase.
	 * @param value the new value of apDatabase.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getApDatabase
     */
    public void setApDatabase(String value)
        throws CopyObjectException
    {
        validateString("apDatabase", 8, value);
        replaceBytes(285, 293, convertToHost(Converter.ALPHANUM, 8, 0, value));
    }
    
    /**
     * Get the value of apSchema.
	 * @return the value of apSchema.
	 * @see #setApSchema
     */
    public String getApSchema()
    {
        return convertFromHost(Converter.ALPHANUM, 8, 0, getBytes(293, 301)).trim();
    }
    	
    /**
     * Set the value of apSchema.
	 * @param value the new value of apSchema.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getApSchema
     */
    public void setApSchema(String value)
        throws CopyObjectException
    {
        validateString("apSchema", 8, value);
        replaceBytes(293, 301, convertToHost(Converter.ALPHANUM, 8, 0, value));
    }
    
    /**
     * Get the value of commitStrategy.
	 * @return the value of commitStrategy.
	 * @see #setCommitStrategy
     */
    public String getCommitStrategy()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(301, 302)).trim();
    }
    	
    /**
     * Set the value of commitStrategy.
	 * @param value the new value of commitStrategy.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getCommitStrategy
     */
    public void setCommitStrategy(String value)
        throws CopyObjectException
    {
        validateString("commitStrategy", 1, value);
        replaceBytes(301, 302, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of vcslogSwitch.
	 * @return the value of vcslogSwitch.
	 * @see #setVcslogSwitch
     */
    public String getVcslogSwitch()
    {
        return convertFromHost(Converter.ALPHANUM, 2, 0, getBytes(302, 304)).trim();
    }
    	
    /**
     * Set the value of vcslogSwitch.
	 * @param value the new value of vcslogSwitch.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getVcslogSwitch
     */
    public void setVcslogSwitch(String value)
        throws CopyObjectException
    {
        validateString("vcslogSwitch", 2, value);
        replaceBytes(302, 304, convertToHost(Converter.ALPHANUM, 2, 0, value));
    }
    
    /**
     * Get the value of vcslogFormat.
	 * @return the value of vcslogFormat.
	 * @see #setVcslogFormat
     */
    public String getVcslogFormat()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(304, 305)).trim();
    }
    	
    /**
     * Set the value of vcslogFormat.
	 * @param value the new value of vcslogFormat.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getVcslogFormat
     */
    public void setVcslogFormat(String value)
        throws CopyObjectException
    {
        validateString("vcslogFormat", 1, value);
        replaceBytes(304, 305, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of companyCode.
	 * @return the value of companyCode.
	 * @see #setCompanyCode
     */
    public String getCompanyCode()
    {
        return convertFromHost(Converter.ALPHANUM, 3, 0, getBytes(305, 308)).trim();
    }
    	
    /**
     * Set the value of companyCode.
	 * @param value the new value of companyCode.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getCompanyCode
     */
    public void setCompanyCode(String value)
        throws CopyObjectException
    {
        validateString("companyCode", 3, value);
        replaceBytes(305, 308, convertToHost(Converter.ALPHANUM, 3, 0, value));
    }
    
    /**
     * Get the value of returnDataAreasPassed.
	 * @return the value of returnDataAreasPassed.
	 * @see #setReturnDataAreasPassed
     */
    public Long getReturnDataAreasPassed()
    {
        return getLong(308, 309, Converter.NUMERIC);
    }
    	
    /**
     * Set the value of returnDataAreasPassed.
	 * @param value the new value of returnDataAreasPassed.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getReturnDataAreasPassed
     */
    public void setReturnDataAreasPassed(Long value)
        throws CopyObjectException
    {
		setLong("returnDataAreasPassed", 308, 309, 0, value, Converter.NUMERIC);
    }
    
    /**
     * Get the value of vcslogDest.
	 * @return the value of vcslogDest.
	 * @see #setVcslogDest
     */
    public String getVcslogDest()
    {
    	return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(309, 310)).trim();
    }
    	
    /**
     * Set the value of vcslogDest.
	 * @param value the new value of vcslogDest.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getVcslogDest
     */
    public void setVcslogDest(String value)
        throws CopyObjectException
    {
        validateString("vcslogDest", 1, value);
        replaceBytes(309, 310, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of vcslogDetailSw.
	 * @return the value of vcslogDetailSw.
	 * @see #setVcslogDetailSw
     */
    public String getVcslogDetailSw()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(310, 311)).trim();
    }
    	
    /**
     * Set the value of vcslogDetailSw.
	 * @param value the new value of vcslogDetailSw.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getVcslogDetailSw
     */
    public void setVcslogDetailSw(String value)
        throws CopyObjectException
    {
        validateString("vcslogDetailSw", 1, value);
        replaceBytes(310, 311, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of activityId.
	 * @return the value of activityId.
	 * @see #setActivityId
     */
    public String getActivityId()
    {
        return getString(311, 331, Converter.ALPHANUM);
    }
    	
    /**
     * Set the value of activityId.
	 * @param value the new value of activityId.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getActivityId
     */
    public void setActivityId(String value)
        throws CopyObjectException
    {
		setString("activityId", 311, 331, value, Converter.ALPHANUM);
    }
    
    /**
     * Get the value of sequenceNo.
	 * @return the value of sequenceNo.
	 * @see #setSequenceNo
     */
    public Long getSequenceNo()
    {
        return getLong(331, 337, Converter.NUMERIC);
    }
    	
    /**
     * Set the value of sequenceNo.
	 * @param value the new value of sequenceNo.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getSequenceNo
     */
    public void setSequenceNo(Long value)
        throws CopyObjectException
    {
		setLong("sequenceNo", 331, 337, 0, value, Converter.NUMERIC);
    }
    
    /**
     * Get the value of responseTimestamp.
	 * @return the value of responseTimestamp.
	 * @see #setResponseTimestamp
     */
    public String getResponseTimestamp()
    {
        return convertFromHost(Converter.ALPHANUM, 26, 0, getBytes(352, 378)).trim();
    }
    	
    /**
     * Set the value of responseTimestamp.
	 * @param value the new value of responseTimestamp.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getResponseTimestamp
     */
    public void setResponseTimestamp(String value)
        throws CopyObjectException
    {
        validateString("responseTimestamp", 26, value);
        replaceBytes(352, 378, convertToHost(Converter.ALPHANUM, 26, 0, value));
    }
    
    /**
     * Get the value of returndataarealength.
	 * @return the value of returndataarealength.
	 * @see #setReturndataarealength
     */
    public Long getReturndataarealength()
    {
        return Long.valueOf(convertFromHost(Converter.NUMERIC, 9, 0, getBytes(378, 387)));
    }
    	
    /**
     * Set the value of returndataarealength.
	 * @param value the new value of returndataarealength.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getReturndataarealength
     */
    public void setReturndataarealength(Long value)
        throws CopyObjectException
    {
        if (value == null)
            value = Long.valueOf(0);
        else
            validateLong("returndataarealength", 9, 0, value);
        replaceBytes(378, 387, convertToHost(Converter.NUMERIC, 9, 0, value.toString()));
    }
    
    /**
     * Get the value of outputoperationcode.
	 * @return the value of outputoperationcode.
	 * @see #setOutputoperationcode
     */
    public String getOutputoperationcode()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(387, 388)).trim();
    }
    	
    /**
     * Set the value of outputoperationcode.
	 * @param value the new value of outputoperationcode.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getOutputoperationcode
     */
    public void setOutputoperationcode(String value)
        throws CopyObjectException
    {
        validateString("outputoperationcode", 1, value);
        replaceBytes(387, 388, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
     * Get the value of highestoutputrecordseq.
	 * @return the value of highestoutputrecordseq.
	 * @see #setHighestoutputrecordseq
     */
    public Long getHighestoutputrecordseq()
    {
        return Long.valueOf(convertFromHost(Converter.NUMERIC, 4, 0, getBytes(388, 392)));
    }
    	
    /**
     * Set the value of highestoutputrecordseq.
	 * @param value the new value of highestoutputrecordseq.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getHighestoutputrecordseq
     */
    public void setHighestoutputrecordseq(Long value)
        throws CopyObjectException
    {
        if (value == null)
            value = Long.valueOf(0);
        else
            validateLong("highestoutputrecordseq", 4, 0, value);
        replaceBytes(388, 392, convertToHost(Converter.NUMERIC, 4, 0, value.toString()));
    }
    
    /**
     * Get the value of returnstatus.
	 * @return the value of returnstatus.
	 * @see #setReturnstatus
     */
    public String getReturnstatus()
    {
        return convertFromHost(Converter.ALPHANUM, 4, 0, getBytes(392, 396)).trim();
    }
    	
    /**
     * Set the value of returnstatus.
	 * @param value the new value of returnstatus.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getReturnstatus
     */
    public void setReturnstatus(String value)
        throws CopyObjectException
    {
        validateString("returnstatus", 4, value);
        replaceBytes(392, 396, convertToHost(Converter.ALPHANUM, 4, 0, value));
    }
    
    /**
     * Get the value of errorcode.
	 * @return the value of errorcode.
	 * @see #setErrorcode
     */
    public String getErrorcode()
    {
        return convertFromHost(Converter.ALPHANUM, 4, 0, getBytes(396, 400)).trim();
    }
    	
    /**
     * Set the value of errorcode.
	 * @param value the new value of errorcode.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getErrorcode
     */
    public void setErrorcode(String value)
        throws CopyObjectException
    {
        validateString("errorcode", 4, value);
        replaceBytes(396, 400, convertToHost(Converter.ALPHANUM, 4, 0, value));
    }

    /**
     * Get the value of responseSequenceNo.
	 * @return the value of responseSequenceNo.
	 * @see #setResponseSequenceNo
     */
    public Long getResponseSequenceNo()
    {
        return getLong(400, 406, Converter.NUMERIC);
    }
    	
    /**
     * Set the value of responseSequenceNo.
	 * @param value the new value of responseSequenceNo.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getResponseSequenceNo
     */
    public void setResponseSequenceNo(Long value)
        throws CopyObjectException
    {
		setLong("responseSequenceNo", 400, 406, 0, value, Converter.NUMERIC);
    }

    /**
	 * Validate this copy object.
	 * @throws ServiceValidationException If there is invalid data.
	 */
    @Override
    public void validate()
		throws ServiceValidationException
    {
		ServiceValidationException e = new ServiceValidationException();


		// If there were any invalid allowable values throw exception.
		if (e.hasMessages())
			throw e;
    }

    /**
	 * Returns a list of detail objects.  Each object in the list represents one field 
	 * in the copybook.
	 * @param prefix A string to prefix field names with.
	 * @return a list of detail objects.
	 */
    @Override
    public List<HistoryDetailItem> toDetailList(String prefix)
    {
	    List<HistoryDetailItem> detailList = new ArrayList<HistoryDetailItem>();
        addDetail(prefix, "CicsTrx", detailList);
        addDetail(prefix, "Delim1", detailList);
        addDetail(prefix, "ClientInData", detailList);
        addDetail(prefix, "Delim2", detailList);
        addDetail(prefix, "IcTdStartup", detailList);
        addDetail(prefix, "Delim3", detailList);
        addDetail(prefix, "TrxDelay", detailList);
        addDetail(prefix, "RequestTimestamp", detailList);
        addDetail(prefix, "SystemId", detailList);
        addDetail(prefix, "EventId", detailList);
        addDetail(prefix, "EventMode", detailList);
        addDetail(prefix, "UserId", detailList);
        addDetail(prefix, "UserIp", detailList);
        addDetail(prefix, "TrxCode", detailList);
        addDetail(prefix, "ProdCode", detailList);
        addDetail(prefix, "TaskNumber", detailList);
        addDetail(prefix, "TaskInvocation", detailList);
        addDetail(prefix, "Mrocontrolindicator", detailList);
        addDetail(prefix, "Mrocontrolaorregion", detailList);
        addDetail(prefix, "Mrotsqueuename", detailList);
        addDetail(prefix, "Mrocontroluserdata", detailList);
        addDetail(prefix, "Messagekey", detailList);
        addDetail(prefix, "Inputoperationcode", detailList);
        addDetail(prefix, "Highestinputrecordseq", detailList);
        addDetail(prefix, "Erroroverrideind", detailList);
        addDetail(prefix, "Rtdcind", detailList);
        addDetail(prefix, "Returnoverriddenerrorsind", detailList);
        addDetail(prefix, "Restrictcoderemovalind", detailList);
        addDetail(prefix, "Suspendcoderemovalind", detailList);
        addDetail(prefix, "Dcrcreviseind", detailList);
        addDetail(prefix, "Dcrcrevisedkey", detailList);
        addDetail(prefix, "Filecontrol", detailList);
        addDetail(prefix, "BrDatabase", detailList);
        addDetail(prefix, "BrSchema", detailList);
        addDetail(prefix, "ApDatabase", detailList);
        addDetail(prefix, "ApSchema", detailList);
        addDetail(prefix, "CommitStrategy", detailList);
        addDetail(prefix, "VcslogSwitch", detailList);
        addDetail(prefix, "VcslogFormat", detailList);
        addDetail(prefix, "CompanyCode", detailList);
        addDetail(prefix, "ReturnDataAreasPassed", detailList);
        addDetail(prefix, "VcslogDest", detailList);
        addDetail(prefix, "VcslogDetailSw", detailList);
        addDetail(prefix, "ActivityId", detailList);
        addDetail(prefix, "SequenceNo", detailList);
        addDetail(prefix, "ResponseTimestamp", detailList);
        addDetail(prefix, "Returndataarealength", detailList);
        addDetail(prefix, "Outputoperationcode", detailList);
        addDetail(prefix, "Highestoutputrecordseq", detailList);
        addDetail(prefix, "Returnstatus", detailList);
        addDetail(prefix, "Errorcode", detailList);
        addDetail(prefix, "ResponseSequenceNo", detailList);
        return detailList;
	}
	
    /**
     * Return a string that contains the name and value of each property
     * contained on this CopyObject.
	 */
    @Override
    public String toString()
    {
		ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("CicsTrx", getCicsTrx());
        builder.append("Delim1", getDelim1());
        builder.append("ClientInData", getClientInData());
        builder.append("Delim2", getDelim2());
        builder.append("IcTdStartup", getIcTdStartup());
        builder.append("Delim3", getDelim3());
        builder.append("TrxDelay", getTrxDelay());
        builder.append("RequestTimestamp", getRequestTimestamp());
        builder.append("SystemId", getSystemId());
        builder.append("EventId", getEventId());
        builder.append("EventMode", getEventMode());
        builder.append("UserId", getUserId());
        builder.append("UserIp", getUserIp());
        builder.append("TrxCode", getTrxCode());
        builder.append("ProdCode", getProdCode());
        builder.append("TaskNumber", getTaskNumber());
        builder.append("TaskInvocation", getTaskInvocation());
        builder.append("Mrocontrolindicator", getMrocontrolindicator());
        builder.append("Mrocontrolaorregion", getMrocontrolaorregion());
        builder.append("Mrotsqueuename", getMrotsqueuename());
        builder.append("Mrocontroluserdata", getMrocontroluserdata());
        builder.append("Messagekey", getMessagekey());
        builder.append("Inputoperationcode", getInputoperationcode());
        builder.append("Highestinputrecordseq", getHighestinputrecordseq());
        builder.append("Erroroverrideind", getErroroverrideind());
        builder.append("Rtdcind", getRtdcind());
        builder.append("Returnoverriddenerrorsind", getReturnoverriddenerrorsind());
        builder.append("Restrictcoderemovalind", getRestrictcoderemovalind());
        builder.append("Suspendcoderemovalind", getSuspendcoderemovalind());
        builder.append("Dcrcreviseind", getDcrcreviseind());
        builder.append("Dcrcrevisedkey", getDcrcrevisedkey());
        builder.append("Filecontrol", getFilecontrol());
        builder.append("BrDatabase", getBrDatabase());
        builder.append("BrSchema", getBrSchema());
        builder.append("ApDatabase", getApDatabase());
        builder.append("ApSchema", getApSchema());
        builder.append("CommitStrategy", getCommitStrategy());
        builder.append("VcslogSwitch", getVcslogSwitch());
        builder.append("VcslogFormat", getVcslogFormat());
        builder.append("CompanyCode", getCompanyCode());
        builder.append("ReturnDataAreasPassed", getReturnDataAreasPassed());
        builder.append("VcslogDest", getVcslogDest());
        builder.append("VcslogDetailSw", getVcslogDetailSw());
        builder.append("ActivityId", getActivityId());
        builder.append("SequenceNo", getSequenceNo());
        builder.append("ResponseTimestamp", getResponseTimestamp());
        builder.append("Returndataarealength", getReturndataarealength());
        builder.append("Outputoperationcode", getOutputoperationcode());
        builder.append("Highestoutputrecordseq", getHighestoutputrecordseq());
        builder.append("Returnstatus", getReturnstatus());
        builder.append("Errorcode", getErrorcode());
        builder.append("ResponseSequenceNo", getResponseSequenceNo());
        return builder.toString();
	}
}
