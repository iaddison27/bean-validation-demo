package org.test.list;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ListElementSubstringValidator implements ConstraintValidator<ListElementSubstring, List<String>> {

	// String that must be a substring of all elements in the List
	private String string;
	
	// Case sensitive? (true = yes, false = no)
	private boolean caseSensitive;

	@Override
	public void initialize(ListElementSubstring listElementSubstring) {
		this.string = listElementSubstring.string();
		this.caseSensitive = listElementSubstring.caseSensitive();
	}

	@Override
	public boolean isValid(List<String> values, ConstraintValidatorContext context) {
		// Null or empty list is valid
		if (values != null && !values.isEmpty()) {
			for (String value : values) {
				// Null value is invalid
				if (value == null) {
					return false;
				}
				
				if (caseSensitive) {
					// Case sensitive comparison
					if (!value.contains(string)) {
						// This element does not contain the string
						return false;
					}
				} else {
					// Case insensitive comparison
					if (!value.toLowerCase().contains(string.toLowerCase())) {
						// This element does not contain the string
						return false;
					}
				}
			}
		}

		return true;
	}
}
