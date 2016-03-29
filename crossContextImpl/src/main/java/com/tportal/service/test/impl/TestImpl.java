package com.tportal.service.test.impl;

import com.tportal.service.Test;

public class TestImpl implements Test {

	public String test() {
		return test("");
	}

	public String test(String s) {
		return "Hello test with " + s;
	}

}
