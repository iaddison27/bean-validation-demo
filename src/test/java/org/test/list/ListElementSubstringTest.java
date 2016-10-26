package org.test.list;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class ListElementSubstringTest {

	private Validator validator;

	@Before
	public void init() {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		this.validator = vf.getValidator();

	}

	@Test
	public void shouldHandleNullList() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc")
			private List<String> values = null;

		});

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldHandleEmptyList() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc")
			private List<String> values = Arrays.asList(new String[] {});

		});

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldHandleListElementsShorterThanTheString() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc")
			private List<String> values = Arrays.asList(new String[] { "ab", "cd" });

		});

		assertFalse(violations.isEmpty());
	}
	
	/* Case insensitive tests */
	@Test
	public void shouldFailWhenListContainsNull() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc")
			private List<String> values = Arrays.asList(new String[] { "abc", null, "abc" });

		});

		assertFalse(violations.isEmpty());
	}

	@Test
	public void shouldPassWhenAllListElementsEqualsTheString() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc")
			private List<String> values = Arrays.asList(new String[] { "ABC", "abc" });

		});

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldPassWhenAllListElementsContainTheString() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc")
			private List<String> values = Arrays.asList(new String[] { "abc1", "2ABC2", "3abc" });

		});

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldFailWhenNotAllListElementsContainTheString() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc")
			private List<String> values = Arrays.asList(new String[] { "ABC1", "2abc2", "def", "3abc" });

		});

		assertFalse(violations.isEmpty());
	}
	
	/* Case sensitive tests */
	@Test
	public void shouldFailWhenListContainsNullAndCaseSensitive() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc", caseSensitive=true)
			private List<String> values = Arrays.asList(new String[] { "abc", null, "abc" });

		});

		assertFalse(violations.isEmpty());
	}

	@Test
	public void shouldPassWhenAllListElementsEqualsTheStringAndCaseSensitive() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc", caseSensitive=true)
			private List<String> values = Arrays.asList(new String[] { "abc", "abc" });

		});

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldPassWhenAllListElementsContainTheStringAndCaseSensitive() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc", caseSensitive=true)
			private List<String> values = Arrays.asList(new String[] { "abc1", "2abc2", "3abc" });

		});

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldFailWhenNotAllListElementsContainTheStringAndCaseSensitive() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc", caseSensitive=true)
			private List<String> values = Arrays.asList(new String[] { "abc1", "2abc2", "def", "3abc" });

		});

		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void shouldFailWhenNotAllListElementsEqualTheStringDueToCaseSensitivity() {
		final Set<ConstraintViolation<Object>> violations = this.validator.validate(new Object() {
			@ListElementSubstring(string="abc", caseSensitive=true)
			private List<String> values = Arrays.asList(new String[] { "abc", "abc", "ABC" });

		});

		assertFalse(violations.isEmpty());
	}
	
}
