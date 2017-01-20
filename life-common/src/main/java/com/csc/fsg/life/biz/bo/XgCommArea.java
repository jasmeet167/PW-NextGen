package com.csc.fsg.life.biz.bo;

import com.csc.fsg.life.biz.meta.MetaData;

/* Modifications: ENH988, P0158, P0200, T0175, T0166 */

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
 * Interface for XgCommArea object.
 */
public interface XgCommArea
	extends CommArea
{
    
    /**
     * Get the value of cicsTrx.
     * @return the value of cicsTrx.
	 * @see #setCicsTrx
     */
    @MetaData (size=4, scale=0, offset=0, required=false)
    public String getCicsTrx();
    
    /**
     * Set the value of cicsTrx.
	 * @param value the new value of cicsTrx.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getCicsTrx
     */
    public void setCicsTrx(String value) throws BusinessObjectException;
    
    /**
     * Get the value of delim1.
     * @return the value of delim1.
	 * @see #setDelim1
     */
    @MetaData (size=1, scale=0, offset=4, required=false)
    public String getDelim1();
    
    /**
     * Set the value of delim1.
	 * @param value the new value of delim1.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getDelim1
     */
    public void setDelim1(String value) throws BusinessObjectException;
    
    /**
     * Get the value of clientInData.
     * @return the value of clientInData.
	 * @see #setClientInData
     */
    @MetaData (size=35, scale=0, offset=5, required=false)
    public String getClientInData();
    
    /**
     * Set the value of clientInData.
	 * @param value the new value of clientInData.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getClientInData
     */
    public void setClientInData(String value) throws BusinessObjectException;
    
    /**
     * Get the value of delim2.
     * @return the value of delim2.
	 * @see #setDelim2
     */
    @MetaData (size=1, scale=0, offset=40, required=false)
    public String getDelim2();
    
    /**
     * Set the value of delim2.
	 * @param value the new value of delim2.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getDelim2
     */
    public void setDelim2(String value) throws BusinessObjectException;
    
    /**
     * Get the value of icTdStartup.
     * @return the value of icTdStartup.
	 * @see #setIcTdStartup
     */
    @MetaData (size=2, scale=0, offset=41, required=false)
    public String getIcTdStartup();
    
    /**
     * Set the value of icTdStartup.
	 * @param value the new value of icTdStartup.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getIcTdStartup
     */
    public void setIcTdStartup(String value) throws BusinessObjectException;
    
    /**
     * Get the value of delim3.
     * @return the value of delim3.
	 * @see #setDelim3
     */
    @MetaData (size=1, scale=0, offset=43, required=false)
    public String getDelim3();
    
    /**
     * Set the value of delim3.
	 * @param value the new value of delim3.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getDelim3
     */
    public void setDelim3(String value) throws BusinessObjectException;
    
    /**
     * Get the value of trxDelay.
     * @return the value of trxDelay.
	 * @see #setTrxDelay
     */
    @MetaData (size=6, scale=0, offset=44, signed=false, required=false)
    public Long getTrxDelay();
    
    /**
     * Set the value of trxDelay.
	 * @param value the new value of trxDelay.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getTrxDelay
     */
    public void setTrxDelay(Long value) throws BusinessObjectException;
    
    /**
     * Get the value of requestTimestamp.
     * @return the value of requestTimestamp.
	 * @see #setRequestTimestamp
     */
    @MetaData (size=26, scale=0, offset=50, required=false)
    public String getRequestTimestamp();
    
    /**
     * Set the value of requestTimestamp.
	 * @param value the new value of requestTimestamp.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getRequestTimestamp
     */
    public void setRequestTimestamp(String value) throws BusinessObjectException;
    
    /**
     * Get the value of systemId.
     * @return the value of systemId.
	 * @see #setSystemId
     */
    @MetaData (size=5, scale=0, offset=76, required=false)
    public String getSystemId();
    
    /**
     * Set the value of systemId.
	 * @param value the new value of systemId.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getSystemId
     */
    public void setSystemId(String value) throws BusinessObjectException;
    
    /**
     * Get the value of eventId.
     * @return the value of eventId.
	 * @see #setEventId
     */
    @MetaData (size=5, scale=0, offset=81, required=false)
    public String getEventId();
    
    /**
     * Set the value of eventId.
	 * @param value the new value of eventId.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getEventId
     */
    public void setEventId(String value) throws BusinessObjectException;
    
    /**
     * Get the value of eventMode.
     * @return the value of eventMode.
	 * @see #setEventMode
     */
    @MetaData (size=1, scale=0, offset=86, required=false)
    public String getEventMode();
    
    /**
     * Set the value of eventMode.
	 * @param value the new value of eventMode.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getEventMode
     */
    public void setEventMode(String value) throws BusinessObjectException;
    
    /**
     * Get the value of userId.
     * @return the value of userId.
	 * @see #setUserId
     */
    @MetaData (size=20, scale=0, offset=87, required=false)
    public String getUserId();
    
    /**
     * Set the value of userId.
	 * @param value the new value of userId.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getUserId
     */
    public void setUserId(String value) throws BusinessObjectException;
    
    /**
     * Set the value of userPwd.
	 * @param value the new value of userPwd.
	 * @throws BusinessObjectException If there is a problem setting the value.
     */
    public void setUserPwd(char[] value) throws BusinessObjectException;
    
    /**
     * Get the value of userIp.
     * @return the value of userIp.
	 * @see #setUserIp
     */
    @MetaData (size=15, scale=0, offset=127, required=false)
    public String getUserIp();
    
    /**
     * Set the value of userIp.
	 * @param value the new value of userIp.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getUserIp
     */
    public void setUserIp(String value) throws BusinessObjectException;
    
    /**
     * Get the value of trxCode.
     * @return the value of trxCode.
	 * @see #setTrxCode
     */
    @MetaData (size=4, scale=0, offset=142, required=false)
    public String getTrxCode();
    
    /**
     * Set the value of trxCode.
	 * @param value the new value of trxCode.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getTrxCode
     */
    public void setTrxCode(String value) throws BusinessObjectException;
    
    /**
     * Get the value of prodCode.
     * @return the value of prodCode.
	 * @see #setProdCode
     */
    @MetaData (size=2, scale=0, offset=146, required=false)
    public String getProdCode();
    
    /**
     * Set the value of prodCode.
	 * @param value the new value of prodCode.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getProdCode
     */
    public void setProdCode(String value) throws BusinessObjectException;
    
    /**
     * Get the value of taskNumber.
     * @return the value of taskNumber.
	 * @see #setTaskNumber
     */
    @MetaData (size=4, scale=0, offset=148, required=false)
    public String getTaskNumber();
    
    /**
     * Set the value of taskNumber.
	 * @param value the new value of taskNumber.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getTaskNumber
     */
    public void setTaskNumber(String value) throws BusinessObjectException;
    
    /**
     * Get the value of taskInvocation.
     * @return the value of taskInvocation.
	 * @see #setTaskInvocation
     */
    @MetaData (size=1, scale=0, offset=152, required=false)
    public String getTaskInvocation();
    
    /**
     * Set the value of taskInvocation.
	 * @param value the new value of taskInvocation.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getTaskInvocation
     */
    public void setTaskInvocation(String value) throws BusinessObjectException;
    
    /**
     * Get the value of mrocontrolindicator.
     * @return the value of mrocontrolindicator.
	 * @see #setMrocontrolindicator
     */
    @MetaData (size=1, scale=0, offset=153, required=false)
    public String getMrocontrolindicator();
    
    /**
     * Set the value of mrocontrolindicator.
	 * @param value the new value of mrocontrolindicator.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getMrocontrolindicator
     */
    public void setMrocontrolindicator(String value) throws BusinessObjectException;
    
    /**
     * Get the value of mrocontrolaorregion.
     * @return the value of mrocontrolaorregion.
	 * @see #setMrocontrolaorregion
     */
    @MetaData (size=4, scale=0, offset=154, required=false)
    public String getMrocontrolaorregion();
    
    /**
     * Set the value of mrocontrolaorregion.
	 * @param value the new value of mrocontrolaorregion.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getMrocontrolaorregion
     */
    public void setMrocontrolaorregion(String value) throws BusinessObjectException;
    
    /**
     * Get the value of mrotsqueuename.
     * @return the value of mrotsqueuename.
	 * @see #setMrotsqueuename
     */
    @MetaData (size=8, scale=0, offset=158, required=false)
    public String getMrotsqueuename();
    
    /**
     * Set the value of mrotsqueuename.
	 * @param value the new value of mrotsqueuename.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getMrotsqueuename
     */
    public void setMrotsqueuename(String value) throws BusinessObjectException;
    
    /**
     * Get the value of mrocontroluserdata.
     * @return the value of mrocontroluserdata.
	 * @see #setMrocontroluserdata
     */
    @MetaData (size=10, scale=0, offset=166, required=false)
    public String getMrocontroluserdata();
    
    /**
     * Set the value of mrocontroluserdata.
	 * @param value the new value of mrocontroluserdata.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getMrocontroluserdata
     */
    public void setMrocontroluserdata(String value) throws BusinessObjectException;
    
    /**
     * Get the value of messagekey.
     * @return the value of messagekey.
	 * @see #setMessagekey
     */
    @MetaData (size=20, scale=0, offset=176, required=false)
    public String getMessagekey();
    
    /**
     * Set the value of messagekey.
	 * @param value the new value of messagekey.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getMessagekey
     */
    public void setMessagekey(String value) throws BusinessObjectException;
    
    /**
     * Get the value of inputoperationcode.
     * @return the value of inputoperationcode.
	 * @see #setInputoperationcode
     */
    @MetaData (size=1, scale=0, offset=196, required=false)
    public String getInputoperationcode();
    
    /**
     * Set the value of inputoperationcode.
	 * @param value the new value of inputoperationcode.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getInputoperationcode
     */
    public void setInputoperationcode(String value) throws BusinessObjectException;
    
    /**
     * Get the value of highestinputrecordseq.
     * @return the value of highestinputrecordseq.
	 * @see #setHighestinputrecordseq
     */
    @MetaData (size=4, scale=0, offset=197, signed=false, required=false)
    public Long getHighestinputrecordseq();
    
    /**
     * Set the value of highestinputrecordseq.
	 * @param value the new value of highestinputrecordseq.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getHighestinputrecordseq
     */
    public void setHighestinputrecordseq(Long value) throws BusinessObjectException;
    
    /**
     * Get the value of erroroverrideind.
     * @return the value of erroroverrideind.
	 * @see #setErroroverrideind
     */
    @MetaData (size=1, scale=0, offset=201, required=false)
    public String getErroroverrideind();
    
    /**
     * Set the value of erroroverrideind.
	 * @param value the new value of erroroverrideind.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getErroroverrideind
     */
    public void setErroroverrideind(String value) throws BusinessObjectException;
    
    /**
     * Get the value of rtdcind.
     * @return the value of rtdcind.
	 * @see #setRtdcind
     */
    @MetaData (size=1, scale=0, offset=202, required=false)
    public String getRtdcind();
    
    /**
     * Set the value of rtdcind.
	 * @param value the new value of rtdcind.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getRtdcind
     */
    public void setRtdcind(String value) throws BusinessObjectException;
    
    /**
     * Get the value of returnoverriddenerrorsind.
     * @return the value of returnoverriddenerrorsind.
	 * @see #setReturnoverriddenerrorsind
     */
    @MetaData (size=1, scale=0, offset=203, required=false)
    public String getReturnoverriddenerrorsind();
    
    /**
     * Set the value of returnoverriddenerrorsind.
	 * @param value the new value of returnoverriddenerrorsind.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getReturnoverriddenerrorsind
     */
    public void setReturnoverriddenerrorsind(String value) throws BusinessObjectException;
    
    /**
     * Get the value of restrictcoderemovalind.
     * @return the value of restrictcoderemovalind.
	 * @see #setRestrictcoderemovalind
     */
    @MetaData (size=1, scale=0, offset=204, required=false)
    public String getRestrictcoderemovalind();
    
    /**
     * Set the value of restrictcoderemovalind.
	 * @param value the new value of restrictcoderemovalind.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getRestrictcoderemovalind
     */
    public void setRestrictcoderemovalind(String value) throws BusinessObjectException;
    
    /**
     * Get the value of suspendcoderemovalind.
     * @return the value of suspendcoderemovalind.
	 * @see #setSuspendcoderemovalind
     */
    @MetaData (size=1, scale=0, offset=205, required=false)
    public String getSuspendcoderemovalind();
    
    /**
     * Set the value of suspendcoderemovalind.
	 * @param value the new value of suspendcoderemovalind.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getSuspendcoderemovalind
     */
    public void setSuspendcoderemovalind(String value) throws BusinessObjectException;
    
    /**
     * Get the value of dcrcreviseind.
     * @return the value of dcrcreviseind.
	 * @see #setDcrcreviseind
     */
    @MetaData (size=1, scale=0, offset=206, required=false)
    public String getDcrcreviseind();
    
    /**
     * Set the value of dcrcreviseind.
	 * @param value the new value of dcrcreviseind.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getDcrcreviseind
     */
    public void setDcrcreviseind(String value) throws BusinessObjectException;
    
    /**
     * Get the value of dcrcrevisedkey.
     * @return the value of dcrcrevisedkey.
	 * @see #setDcrcrevisedkey
     */
    @MetaData (size=56, scale=0, offset=207, required=false)
    public String getDcrcrevisedkey();
    
    /**
     * Set the value of dcrcrevisedkey.
	 * @param value the new value of dcrcrevisedkey.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getDcrcrevisedkey
     */
    public void setDcrcrevisedkey(String value) throws BusinessObjectException;
    
    /**
     * Get the value of filecontrol.
     * @return the value of filecontrol.
	 * @see #setFilecontrol
     */
    @MetaData (size=6, scale=0, offset=263, required=false)
    public String getFilecontrol();
    
    /**
     * Set the value of filecontrol.
	 * @param value the new value of filecontrol.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getFilecontrol
     */
    public void setFilecontrol(String value) throws BusinessObjectException;
    
    /**
     * Get the value of brDatabase.
     * @return the value of brDatabase.
	 * @see #setBrDatabase
     */
    @MetaData (size=8, scale=0, offset=269, required=false)
    public String getBrDatabase();
    
    /**
     * Set the value of brDatabase.
	 * @param value the new value of brDatabase.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getBrDatabase
     */
    public void setBrDatabase(String value) throws BusinessObjectException;
    
    /**
     * Get the value of brSchema.
     * @return the value of brSchema.
	 * @see #setBrSchema
     */
    @MetaData (size=8, scale=0, offset=277, required=false)
    public String getBrSchema();
    
    /**
     * Set the value of brSchema.
	 * @param value the new value of brSchema.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getBrSchema
     */
    public void setBrSchema(String value) throws BusinessObjectException;
    
    /**
     * Get the value of apDatabase.
     * @return the value of apDatabase.
	 * @see #setApDatabase
     */
    @MetaData (size=8, scale=0, offset=285, required=false)
    public String getApDatabase();
    
    /**
     * Set the value of apDatabase.
	 * @param value the new value of apDatabase.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getApDatabase
     */
    public void setApDatabase(String value) throws BusinessObjectException;
    
    /**
     * Get the value of apSchema.
     * @return the value of apSchema.
	 * @see #setApSchema
     */
    @MetaData (size=8, scale=0, offset=293, required=false)
    public String getApSchema();
    
    /**
     * Set the value of apSchema.
	 * @param value the new value of apSchema.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getApSchema
     */
    public void setApSchema(String value) throws BusinessObjectException;
    
    /**
     * Get the value of commitStrategy.
     * @return the value of commitStrategy.
	 * @see #setCommitStrategy
     */
    @MetaData (size=1, scale=0, offset=301, required=false)
    public String getCommitStrategy();
    
    /**
     * Set the value of commitStrategy.
	 * @param value the new value of commitStrategy.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getCommitStrategy
     */
    public void setCommitStrategy(String value) throws BusinessObjectException;
    
    /**
     * Get the value of vcslogSwitch.
     * @return the value of vcslogSwitch.
	 * @see #setVcslogSwitch
     */
    @MetaData (size=2, scale=0, offset=302, required=false)
    public String getVcslogSwitch();
    
    /**
     * Set the value of vcslogSwitch.
	 * @param value the new value of vcslogSwitch.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getVcslogSwitch
     */
    public void setVcslogSwitch(String value) throws BusinessObjectException;
    
    /**
     * Get the value of vcslogFormat.
     * @return the value of vcslogFormat.
	 * @see #setVcslogFormat
     */
    @MetaData (size=1, scale=0, offset=304, required=false)
    public String getVcslogFormat();
    
    /**
     * Set the value of vcslogFormat.
	 * @param value the new value of vcslogFormat.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getVcslogFormat
     */
    public void setVcslogFormat(String value) throws BusinessObjectException;
    
    /**
     * Get the value of companyCode.
     * @return the value of companyCode.
	 * @see #setCompanyCode
     */
    @MetaData (size=3, scale=0, offset=305, required=false)
    public String getCompanyCode();
    
    /**
     * Set the value of companyCode.
	 * @param value the new value of companyCode.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getCompanyCode
     */
    public void setCompanyCode(String value) throws BusinessObjectException;
    
    /**
     * Get the value of returnDataAreasPassed.
     * @return the value of returnDataAreasPassed.
	 * @see #setReturnDataAreasPassed
     */
    @MetaData (size=1, scale=0, offset=308, signed=false, required=false)
    public Long getReturnDataAreasPassed();
    
    /**
     * Set the value of returnDataAreasPassed.
	 * @param value the new value of returnDataAreasPassed.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getReturnDataAreasPassed
     */
    public void setReturnDataAreasPassed(Long value) throws BusinessObjectException;
    
    /**
     * Get the value of vcslogDest.
     * @return the value of vcslogDest.
	 * @see #setVcslogDest
     */
    @MetaData (size=1, scale=0, offset=309, required=false)
    public String getVcslogDest();
    
    /**
     * Set the value of vcslogDest.
	 * @param value the new value of vcslogDest.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getVcslogDest
     */
    public void setVcslogDest(String value) throws BusinessObjectException;
    
    /**
     * Get the value of vcslogDetailSw.
	 * @return the value of vcslogDetailSw.
	 * @see #setVcslogDetailSw
     */
    @MetaData (size=1, scale=0, offset=310, required=false)
    public String getVcslogDetailSw();
    	
    /**
     * Set the value of vcslogDetailSw.
	 * @param value the new value of vcslogDetailSw.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getVcslogDetailSw
     */
    public void setVcslogDetailSw(String value) throws BusinessObjectException;

    /**
     * Get the value of activityId.
     * @return the value of activityId.
	 * @see #setActivityId
     */
    @MetaData (size=20, scale=0, offset=311, required=false)
    public String getActivityId();
    
    /**
     * Set the value of activityId.
	 * @param value the new value of activityId.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getActivityId
     */
    public void setActivityId(String value) throws BusinessObjectException;
    
    /**
     * Get the value of sequenceNo.
     * @return the value of sequenceNo.
	 * @see #setSequenceNo
     */
    @MetaData (size=6, scale=0, offset=331, signed=false, required=false)
    public Long getSequenceNo();
    
    /**
     * Set the value of sequenceNo.
	 * @param value the new value of sequenceNo.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getSequenceNo
     */
    public void setSequenceNo(Long value) throws BusinessObjectException;
    
    /**
     * Get the value of responseTimestamp.
     * @return the value of responseTimestamp.
	 * @see #setResponseTimestamp
     */
    @MetaData (size=26, scale=0, offset=352, required=false)
    public String getResponseTimestamp();
    
    /**
     * Set the value of responseTimestamp.
	 * @param value the new value of responseTimestamp.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getResponseTimestamp
     */
    public void setResponseTimestamp(String value) throws BusinessObjectException;
    
    /**
     * Get the value of returndataarealength.
     * @return the value of returndataarealength.
	 * @see #setReturndataarealength
     */
    @MetaData (size=9, scale=0, offset=378, signed=false, required=false)
    public Long getReturndataarealength();
    
    /**
     * Set the value of returndataarealength.
	 * @param value the new value of returndataarealength.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getReturndataarealength
     */
    public void setReturndataarealength(Long value) throws BusinessObjectException;
    
    /**
     * Get the value of outputoperationcode.
     * @return the value of outputoperationcode.
	 * @see #setOutputoperationcode
     */
    @MetaData (size=1, scale=0, offset=387, required=false)
    public String getOutputoperationcode();
    
    /**
     * Set the value of outputoperationcode.
	 * @param value the new value of outputoperationcode.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getOutputoperationcode
     */
    public void setOutputoperationcode(String value) throws BusinessObjectException;
    
    /**
     * Get the value of highestoutputrecordseq.
     * @return the value of highestoutputrecordseq.
	 * @see #setHighestoutputrecordseq
     */
    @MetaData (size=4, scale=0, offset=388, signed=false, required=false)
    public Long getHighestoutputrecordseq();
    
    /**
     * Set the value of highestoutputrecordseq.
	 * @param value the new value of highestoutputrecordseq.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getHighestoutputrecordseq
     */
    public void setHighestoutputrecordseq(Long value) throws BusinessObjectException;
    
    /**
     * Get the value of returnstatus.
     * @return the value of returnstatus.
	 * @see #setReturnstatus
     */
    @MetaData (size=4, scale=0, offset=392, required=false)
    public String getReturnstatus();
    
    /**
     * Set the value of returnstatus.
	 * @param value the new value of returnstatus.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getReturnstatus
     */
    public void setReturnstatus(String value) throws BusinessObjectException;
    
    /**
     * Get the value of errorcode.
     * @return the value of errorcode.
	 * @see #setErrorcode
     */
    @MetaData (size=4, scale=0, offset=396, required=false)
    public String getErrorcode();
    
    /**
     * Set the value of errorcode.
	 * @param value the new value of errorcode.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getErrorcode
     */
    public void setErrorcode(String value) throws BusinessObjectException;
    
    /**
     * Get the value of responseSequenceNo.
     * @return the value of responseSequenceNo.
	 * @see #setResponseSequenceNo
     */
    @MetaData (size=6, scale=0, offset=400, signed=false, required=false)
    public Long getResponseSequenceNo();
    
    /**
     * Set the value of responseSequenceNo.
	 * @param value the new value of responseSequenceNo.
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getResponseSequenceNo
     */
    public void setResponseSequenceNo(Long value) throws BusinessObjectException;
}
