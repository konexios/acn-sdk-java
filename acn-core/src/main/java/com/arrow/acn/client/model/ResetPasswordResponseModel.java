package com.arrow.acn.client.model;

import com.arrow.acs.client.model.StatusModelAbstract;

public class ResetPasswordResponseModel extends StatusModelAbstract<ResetPasswordResponseModel> {
    private static final long serialVersionUID = 7682145252516691981L;

    private String temporaryPassword;

    public ResetPasswordResponseModel withTemporaryPassword(String temporaryPassword) {
        setTemporaryPassword(temporaryPassword);
        return this;
    }

    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((temporaryPassword == null) ? 0 : temporaryPassword.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResetPasswordResponseModel other = (ResetPasswordResponseModel) obj;
        if (temporaryPassword == null) {
            if (other.temporaryPassword != null)
                return false;
        } else if (!temporaryPassword.equals(other.temporaryPassword))
            return false;
        return true;
    }

    @Override
    protected ResetPasswordResponseModel self() {
        return this;
    }
}
