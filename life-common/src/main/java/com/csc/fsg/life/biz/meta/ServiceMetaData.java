package com.csc.fsg.life.biz.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* Modifications: ENH988, T0135, T0153, ENH1220 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceMetaData {
	/**
	 * @deprecated This enum is provided only to support extraction of security
	 *             attributes from older versions of the application.<br>
	 *             It is no longer used for any other purpose, and may be
	 *             removed in the next release.
	 */
	@Deprecated
    public enum ServiceEventType {
        NONE { @Override public String toString() { return ""; }},
        INQUIRY, 
        ADD, 
        UPDATE,
        DELETE
    };

	/**
	 * @deprecated This property is provided only to support extraction of
	 *             security attributes from older versions of the application.<br>
	 *             It is no longer used for any other purpose, and may be
	 *             removed in the next release.
	 */
	@Deprecated
	String functionalId() default "";

	/**
	 * @deprecated This property is provided only to support extraction of
	 *             security attributes from older versions of the application.<br>
	 *             It is no longer used for any other purpose, and may be
	 *             removed in the next release.
	 */
	@Deprecated
	String eventType() default "";

	/**
	 * @deprecated This property is provided only to support extraction of
	 *             security attributes from older versions of the application.<br>
	 *             It is no longer used for any other purpose, and may be
	 *             removed in the next release.
	 */
	@Deprecated
	ServiceEventType serviceEventType() default ServiceEventType.NONE;

    boolean validate() default false;
    boolean transaction() default false;
    int isolationLevel() default -1;
    boolean isUpdateAvailabilityRequired() default false;
}