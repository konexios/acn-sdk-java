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

import com.konexios.acs.client.model.DefinitionModelAbstract;

public class NodeModel extends DefinitionModelAbstract<NodeModel> {
	private static final long serialVersionUID = 3412346788005924813L;

	private boolean enabled;
	private String parentNodeHid;
	private String nodeTypeHid;

	@Override
	protected NodeModel self() {
		return this;
	}

	public NodeModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public NodeModel withParentNodeHid(String parentNodeHid) {
		setParentNodeHid(parentNodeHid);
		return this;
	}

	public NodeModel withNodeTypeHid(String nodeTypeHid) {
		setNodeTypeHid(nodeTypeHid);
		return this;
	}

	public String getParentNodeHid() {
		return parentNodeHid;
	}

	public void setParentNodeHid(String parentNodeHid) {
		this.parentNodeHid = parentNodeHid;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getNodeTypeHid() {
		return nodeTypeHid;
	}

	public void setNodeTypeHid(String nodeTypeHid) {
		this.nodeTypeHid = nodeTypeHid;
	}
}
