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
 * Custom validator for validating that an org.joda.time.LocalDate is in the future
 * 
 * Usage:
 *    // Date must be in the future
 *    @Future
 *    private LocalDate date;
 *    
 *    // Date must be in the future (or today)
 *    @Future(nowValid=true)
 *    private LocalDate date;
 *    
 * @see FutureJodaValidator 
 */
@Target({ FIELD, METHOD, CONSTRUCTOR, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy=FutureJodaValidator.class)
@Documented
public @interface FutureJoda {  

	boolean nowValid() default false;
	
	String message() default "{org.test.joda.FutureJoda.message}";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {}; 
}
