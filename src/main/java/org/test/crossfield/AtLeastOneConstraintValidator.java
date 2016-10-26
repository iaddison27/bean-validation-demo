package org.test.crossfield;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class AtLeastOneConstraintValidator implements ConstraintValidator<AtLeastOne, Object>{

    private String errorFieldName;
    private String message;
    private String[] attributes;
    
    @Override
    public void initialize(AtLeastOne constraintAnnotation) {
        errorFieldName = constraintAnnotation.errorField();
        message = constraintAnnotation.message();
        attributes = constraintAnnotation.attributes();

    }
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean result = false;
        
        // Iterate over the attributes
        for (String attribute : attributes) {
            // Faster than using BeanUtils.getProperty()
            // Source: http://forum.springsource.org/showthread.php?65361-beanUtils-getProperty-alternative-in-Spring
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
            Object attributeValue = wrapper.getPropertyValue(attribute);
            // Found a non empty attribute, validation passed
            if (attributeValue != null && !attributeValue.equals("")) {
                result = true;
                break;
            }
        }
        
        // Add the message so it's accessible in the view
        if (!result) {
            addConstraintViolation(context, errorFieldName);
        }
        return result;

    }
    
    private void addConstraintViolation(ConstraintValidatorContext context, String field) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addPropertyNode(field).addConstraintViolation();
    }

}
