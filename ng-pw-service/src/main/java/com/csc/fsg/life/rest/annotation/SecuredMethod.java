package com.csc.fsg.life.rest.annotation;

import static com.csc.fsg.life.rest.annotation.AuthorizationAction.NONE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(SecuredMethods.class)
public @interface SecuredMethod
{
	public String resource() default "";

	public AuthorizationAction action() default NONE;
}
