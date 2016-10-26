package org.test.crossfield;

@AtLeastOne(attributes = {"a", "b", "c"}, errorField = "a")
public class TestForm {
	
	private String a;
	
	private String b;
	
	private String c;

	public TestForm(String a, String b, String c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public String getA() {
		return a;
	}

	public String getB() {
		return b;
	}

	public String getC() {
		return c;
	}

}
