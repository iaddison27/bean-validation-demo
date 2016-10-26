package org.test.crossfield;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class AtLeastOneTest {

	private Validator validator;

	@Before
	public void init() {

		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		this.validator = vf.getValidator();

	}

	@Test
	public void shouldFailWhenAllNull() {

		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new TestForm(null, null, null)
		);

		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void shouldFailWhenAllEmpty() {

		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new TestForm("", "", "")
		);

		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void shouldPassWhenOneProvided() {

		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new TestForm("a", "", null)
		);

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldPassWhenAllProvided() {

		final Set<ConstraintViolation<Object>> violations = this.validator.validate(
				new TestForm("a", "b", "c")
		);

		assertTrue(violations.isEmpty());
	}

	
}
