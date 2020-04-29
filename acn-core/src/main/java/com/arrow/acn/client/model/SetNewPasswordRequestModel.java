package com.arrow.acn.client.model;

import java.io.Serializable;

public class SetNewPasswordRequestModel implements Serializable {
    private static final long serialVersionUID = 4082386589102353036L;

    private String newPassword;

    public SetNewPasswordRequestModel withNewPassword(String newPassword) {
        setNewPassword(newPassword);
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
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
        SetNewPasswordRequestModel other = (SetNewPasswordRequestModel) obj;
        if (newPassword == null) {
            if (other.newPassword != null)
                return false;
        } else if (!newPassword.equals(other.newPassword))
            return false;
        return true;
    }
}
