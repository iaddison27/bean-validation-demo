package org.test.joda;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class PastJodaTest {

	private Validator validator;

	@Before
	public void init() {
		DateTimeUtils.setCurrentMillisFixed(new DateTime(2014, 7, 1, 10, 13, 15, 0).getMillis());

		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		this.validator = vf.getValidator();
	}

	@Test
	public void shouldPassWithNullDate() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new Object() {
					@PastJoda
					private LocalDate date = null;
				}
		);

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldPassWithNullDateWhenNowIsValid() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new Object() {
					@PastJoda(nowValid=true)
					private LocalDate date = null;
				}
		);

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldFailWithFutureDate() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new Object() {
					@PastJoda
					private LocalDate date = LocalDate.now().plusDays(1);
				}
		);

		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void shouldFailWithFutureDateWhenNowIsValid() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new Object() {
					@PastJoda(nowValid=true)
					private LocalDate date = LocalDate.now().plusDays(1);
				}
		);

		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void shouldFailWithCurrentDate() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new Object() {
					@PastJoda
					private LocalDate date = LocalDate.now();
				}
		);

		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void shouldPassWithCurrentDateWhenNowIsValid() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new Object() {
					@PastJoda(nowValid=true)
					private LocalDate date = LocalDate.now();
				}
		);

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldPassWithHistoricDate() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new Object() {
					@PastJoda
					private LocalDate date = LocalDate.now().minusDays(1);
				}
		);

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldPassWithHistoricDateWhenNowIsValid() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new Object() {
					@PastJoda(nowValid=true)
					private LocalDate date = LocalDate.now().minusDays(1);
				}
		);

		assertTrue(violations.isEmpty());
	}
}
