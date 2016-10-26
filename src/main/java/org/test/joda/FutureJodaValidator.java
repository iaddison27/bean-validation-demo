package org.test.joda;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.LocalDate;

public class FutureJodaValidator implements ConstraintValidator<FutureJoda, LocalDate> {

	// Whether the current date is valid
	private boolean nowValid;

	public void initialize(final FutureJoda constraintAnnotation) {
		nowValid = constraintAnnotation.nowValid();
	}

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		LocalDate now = LocalDate.now();
		if (nowValid) {
			// Today valid, so check using !isBefore
			return !((LocalDate) value).isBefore(now);
		} else {
			// Today not valid, so can check using isAfter
			return ((LocalDate) value).isAfter(now);
		}

	}

}
