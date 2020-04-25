package com.arrow.acn.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuthResponseModel implements Serializable {
    private static final long serialVersionUID = -119973382684862979L;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String userHid;
    private List<String> roles = new ArrayList<>();

    public AuthResponseModel withUsername(String username) {
        setUsername(username);
        return this;
    }

    public AuthResponseModel withFirstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public AuthResponseModel withLastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public AuthResponseModel withEmail(String email) {
        setEmail(email);
        return this;
    }

    public AuthResponseModel withUserHid(String userHid) {
        setUserHid(userHid);
        return this;
    }

    public AuthResponseModel withRoles(List<String> roles) {
        setRoles(roles);
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserHid() {
        return userHid;
    }

    public void setUserHid(String userHid) {
        this.userHid = userHid;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((roles == null) ? 0 : roles.hashCode());
        result = prime * result + ((userHid == null) ? 0 : userHid.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (roles == null) {
            if (other.roles != null)
                return false;
        } else if (!roles.equals(other.roles))
            return false;
        if (userHid == null) {
            if (other.userHid != null)
                return false;
        } else if (!userHid.equals(other.userHid))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
}
