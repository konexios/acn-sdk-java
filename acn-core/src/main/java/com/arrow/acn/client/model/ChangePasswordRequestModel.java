package com.arrow.acn.client.model;

import java.io.Serializable;

public class ChangePasswordRequestModel implements Serializable {
    private static final long serialVersionUID = 4082386589102353036L;

    private String currentPassword;
    private String newPassword;

    public ChangePasswordRequestModel withCurrentPassword(String currentPassword) {
        setCurrentPassword(currentPassword);
        return this;
    }

    public ChangePasswordRequestModel withNewPassword(String newPassword) {
        setNewPassword(newPassword);
        return this;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
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
        result = prime * result + ((currentPassword == null) ? 0 : currentPassword.hashCode());
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
        ChangePasswordRequestModel other = (ChangePasswordRequestModel) obj;
        if (currentPassword == null) {
            if (other.currentPassword != null)
                return false;
        } else if (!currentPassword.equals(other.currentPassword))
            return false;
        if (newPassword == null) {
            if (other.newPassword != null)
                return false;
        } else if (!newPassword.equals(other.newPassword))
            return false;
        return true;
    }
}
