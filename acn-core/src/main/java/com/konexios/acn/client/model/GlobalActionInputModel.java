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

import java.io.Serializable;

public class GlobalActionInputModel implements Serializable {
		private static final long serialVersionUID = -2068273575053334836L;
		
		private String name;
		private String type;
		private boolean required = false;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public boolean isRequired() {
			return required;
		}

		public void setRequired(boolean required) {
			this.required = required;
		}
		
		public GlobalActionInputModel withName(String name) {
			setName(name);
			return this;
		}
		
		public GlobalActionInputModel withType(String type) {
			setType(type);
			return this;
		}
		
		public GlobalActionInputModel withRequired(boolean required) {
			setRequired(required);
			return this;
		}
	}
