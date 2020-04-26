package com.arrow.acn.client.model;

import java.io.Serializable;
import java.util.List;

import com.arrow.acs.client.model.AddressModel;
import com.arrow.acs.client.model.ContactModel;

public class CreateUserRequestModel implements Serializable {
    private static final long serialVersionUID = 7295843313022587061L;

    private String login;
    private String password;
    private ContactModel contact;
    private AddressModel address;
    private List<String> roleNames;

    public CreateUserRequestModel withLogin(String login) {
        setLogin(login);
        return this;
    }

    public CreateUserRequestModel withPassword(String password) {
        setPassword(password);
        return this;
    }

    public CreateUserRequestModel withContact(ContactModel contact) {
        setContact(contact);
        return this;
    }

    public CreateUserRequestModel withAddress(AddressModel address) {
        setAddress(address);
        return this;
    }

    public CreateUserRequestModel withRoleNames(List<String> roleNames) {
        setRoleNames(roleNames);
        return this;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
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
        CreateUserRequestModel other = (CreateUserRequestModel) obj;
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
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (roleNames == null) {
            if (other.roleNames != null)
                return false;
        } else if (!roleNames.equals(other.roleNames))
            return false;
        return true;
    }
}
