package com.csc.fsg.life.biz.service;


/**
   Interface to represent a class that validates object data for a business service.
   Implement this interface to plug into a service for validation.
**/
public interface ServiceValidator
{
    /**
     * Called to validate the business objects of a service call.
     * @param param
     * @throws ServiceValidationException
     */
    public void validate(ServiceParam param)
		throws ServiceValidationException;
}
