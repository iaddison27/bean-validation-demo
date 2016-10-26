package org.test.crossfield;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom validator for validating that at least one value from a number of attributes has been provided
 * 
 * Usage:
 * @AtLeastOne(attributes = {"searchTermAll", "searchTermExact", "searchTermAtLeast"}, errorField = "searchTermAll")
 * public class AdvancedSearchForm {
 *    private String searchTermAll;
 *    private String searchTermExact;
 *    private String searchTermAtLeast;
 * }
 * 
 * @see AtLeastOneValidator
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = AtLeastOneConstraintValidator.class)
@Documented
public @interface AtLeastOne {
    
    String message() default "{org.test.crossfield.AtLeastOne.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String errorField();
    
    String[] attributes();

    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        AtLeastOne[] value();
    }
}
