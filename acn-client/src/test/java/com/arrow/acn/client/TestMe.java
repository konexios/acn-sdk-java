package com.arrow.acn.client;

import org.junit.Test;

public class TestMe {

	@Test
	public void test() throws Exception {
		String input = "attachment;filename=selene-engine.jar";

		String[] tokens = input.split(";", -1);
		for (String token : tokens) {
			if (token.contains("=")) {
				String[] values = token.split("=");
				if (values.length == 2 && values[0].trim().equalsIgnoreCase("filename")) {
					System.out.println(values[1].trim());
				}
			}
		}
	}
}
