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
package com.konexios.acn.client.model;

import com.konexios.acs.AcsUtils;
import com.konexios.acs.KeyValuePair;

public enum TelemetryItemType {
	// @formatter:off
    System("_|", true, false, "strValue"),
    String("s|", false, false, "strValue"),
    Integer("i|", false, true, "intValue"),
    Float("f|", false, true, "floatValue"),
    Boolean("b|", false, false, "boolValue"),
    Date("d|", false, false, "dateValue"),
    DateTime("dt|", false, false, "dateTimeValue"),
    IntegerSquare("i2|", false, false, "intSqrValue"),
    IntegerCube("i3|", false, false, "intCubeValue"),
    FloatSquare("f2|", false, false, "floatSqrValue"),
    FloatCube("f3|", false, false, "floatCubeValue"),
    Binary("bi|", false, false, "binaryValue");
    // @formatter:on

	private final String prefix;
	private final boolean system;
	private final boolean chartable;
	private final String fieldName;

	private TelemetryItemType(String prefix, boolean system, boolean chartable, String fieldName) {
		this.prefix = prefix;
		this.system = system;
		this.chartable = chartable;
		this.fieldName = fieldName;
	}

	public String getPrefix() {
		return prefix;
	}

	public boolean isSystem() {
		return system;
	}

	public boolean isChartable() {
		return chartable;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String buildName(String name) {
		return java.lang.String.format("%s%s", prefix, name);
	}

	public static KeyValuePair<TelemetryItemType, String> parse(String name) {
		if (AcsUtils.isNotEmpty(name))
			for (TelemetryItemType type : TelemetryItemType.values())
				if (name.startsWith(type.getPrefix()))
					return new KeyValuePair<>(type, name.substring(type.getPrefix().length()));
		return null;
	}
}
