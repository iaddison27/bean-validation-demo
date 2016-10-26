package org.test.joda;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.LocalDate;

public class PastJodaValidator implements ConstraintValidator<PastJoda, LocalDate> {

	// Whether the current date is valid
	private boolean nowValid;

	public void initialize(final PastJoda constraintAnnotation) {
		nowValid = constraintAnnotation.nowValid();
	}

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		LocalDate now = new LocalDate();
		if (nowValid) {
			// Today valid, so check using !isAfter
			return !((LocalDate) value).isAfter(now);
		} else {
			// Today not valid, so can check using isBefore
			return ((LocalDate) value).isBefore(now);
		}

	}

}
