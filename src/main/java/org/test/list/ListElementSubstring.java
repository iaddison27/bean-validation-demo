package org.test.list;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom validator for validating that each string in a list contains the specified substring
 * 
 * Usage:
 *    // All elements in list must contain the substring abc (case insensitive)
 *    @ListElementSubstring(string="abc")
 *    private List<String> list;
 *    
 *    // All elements in list must contain the substring abc (case sensitive)
 *    @ListElementSubstring(string="abc", caseSensitive=true)
 *    private List<String> list;
 *    
 * @see ListElementSubstringValidator 
 */
@Documented
@Constraint(validatedBy = ListElementSubstringValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ListElementSubstring {

	String string();
	
	boolean caseSensitive() default false;

	String message() default "{org.test.list.ListElementSubstring.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
