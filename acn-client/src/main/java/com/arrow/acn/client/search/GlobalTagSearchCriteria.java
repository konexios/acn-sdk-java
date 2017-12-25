/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn.client.search;

public class GlobalTagSearchCriteria extends SortedSearchCriteria {

	private static final String NAME = "name";
	private static final String OBJECT_TYPES = "objectType";

	public GlobalTagSearchCriteria withName(String name) {
		simpleCriteria.put(NAME, name);
		return this;
	}

	public GlobalTagSearchCriteria withObjectIds(String... objectIds) {
		arrayCriteria.put(OBJECT_TYPES, objectIds);
		return this;
	}
}
