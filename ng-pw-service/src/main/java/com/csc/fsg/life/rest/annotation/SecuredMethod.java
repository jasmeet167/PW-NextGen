package com.csc.fsg.life.rest.annotation;

import static com.csc.fsg.life.rest.param.AuthorizationAction.NONE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.csc.fsg.life.rest.param.AuthorizationAction;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecuredMethod
{
	public AuthorizationAction action() default NONE;
}
