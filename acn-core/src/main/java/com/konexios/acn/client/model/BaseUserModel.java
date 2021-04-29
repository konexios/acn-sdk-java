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
import java.util.List;

import com.konexios.acs.client.model.AddressModel;
import com.konexios.acs.client.model.ContactModel;

public class BaseUserModel implements Serializable {
    private static final long serialVersionUID = 8873306041230872384L;

    private ContactModel contact;
    private AddressModel address;
    private List<String> roleNames;

    public BaseUserModel withContact(ContactModel contact) {
        setContact(contact);
        return this;
    }

    public BaseUserModel withAddress(AddressModel address) {
        setAddress(address);
        return this;
    }

    public BaseUserModel withRoleNames(List<String> roleNames) {
        setRoleNames(roleNames);
        return this;
    }

    public ContactModel getContact() {
        return contact;
    }

    public void setContact(ContactModel contact) {
        this.contact = contact;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((roleNames == null) ? 0 : roleNames.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseUserModel other = (BaseUserModel) obj;
        if (roleNames == null) {
            if (other.roleNames != null)
                return false;
        } else if (!roleNames.equals(other.roleNames))
            return false;
        return true;
    }
}
