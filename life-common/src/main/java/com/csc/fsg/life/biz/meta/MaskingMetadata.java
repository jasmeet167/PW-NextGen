package com.csc.fsg.life.biz.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* Modifications: ENH1019 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaskingMetadata
{
	public enum MaskOptionType {
		MASK_ALL,		// Mask All Characters
		MASK_LEADING,	// Display Last # of Characters in clear text
		MASK_TRAILING;	// Display Beginning # of Characters in clear text
	}

	String maskRole() default "";
	MaskOptionType maskOption() default MaskOptionType.MASK_LEADING;
	short clearCharactersCount() default 0;
}
