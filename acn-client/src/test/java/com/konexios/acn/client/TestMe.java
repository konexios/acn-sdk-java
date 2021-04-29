/*******************************************************************************
 * Copyright 2021 Konexios, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.konexios.acn.client;

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
