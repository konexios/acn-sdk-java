package com.arrow.acn.client.model;

import java.io.Serializable;
import java.util.List;

import com.arrow.acs.client.model.AddressModel;
import com.arrow.acs.client.model.ContactModel;

public class AuthResponseModel implements Serializable {
    private static final long serialVersionUID = -119973382684862979L;

    private String login;
    private ContactModel contact;
    private AddressModel address;
    private String userHid;
    private List<String> roleNames;

    public AuthResponseModel withLogin(String login) {
        setLogin(login);
        return this;
    }

    public AuthResponseModel withContact(ContactModel contact) {
        setContact(contact);
        return this;
    }

    public AuthResponseModel withAddress(AddressModel address) {
        setAddress(address);
        return this;
    }

    public AuthResponseModel withUserHid(String userHid) {
        setUserHid(userHid);
        return this;
    }

    public AuthResponseModel withRoleNames(List<String> roleNames) {
        setRoleNames(roleNames);
        return this;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getUserHid() {
        return userHid;
    }

    public void setUserHid(String userHid) {
        this.userHid = userHid;
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
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((roleNames == null) ? 0 : roleNames.hashCode());
        result = prime * result + ((userHid == null) ? 0 : userHid.hashCode());
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
        AuthResponseModel other = (AuthResponseModel) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (contact == null) {
            if (other.contact != null)
                return false;
        } else if (!contact.equals(other.contact))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (roleNames == null) {
            if (other.roleNames != null)
                return false;
        } else if (!roleNames.equals(other.roleNames))
            return false;
        if (userHid == null) {
            if (other.userHid != null)
                return false;
        } else if (!userHid.equals(other.userHid))
            return false;
        return true;
    }
}
