package org.test.joda;

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
 * Custom validator for validating that an org.joda.time.LocalDate is historic
 * 
 * Usage:
 *    // Date must be historic, today is not valid
 *    @Past
 *    private LocalDate date;
 *    
 *    // Date must be historic (or today)
 *    @Past(nowValid=true)
 *    private LocalDate date;
 *    
 * @see PastJodaValidator 
 */
@Target({ FIELD, METHOD, CONSTRUCTOR, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy=PastJodaValidator.class)
@Documented
public @interface PastJoda {  

	boolean nowValid() default false;
	
	String message() default "{org.test.joda.PastJoda.message}";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {}; 
}
