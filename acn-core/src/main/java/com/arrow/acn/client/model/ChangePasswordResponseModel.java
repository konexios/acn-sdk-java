package com.arrow.acn.client.model;

import java.util.List;

import com.arrow.acs.client.model.StatusModelAbstract;

public class ChangePasswordResponseModel extends StatusModelAbstract<ChangePasswordResponseModel> {
    private static final long serialVersionUID = 3763025496512207366L;

    private List<String> errorMessages;

    @Override
    protected ChangePasswordResponseModel self() {
        return this;
    }

    public ChangePasswordResponseModel withErrorMessages(List<String> errorMessages) {
        setErrorMessages(errorMessages);
        return this;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((errorMessages == null) ? 0 : errorMessages.hashCode());
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
        ChangePasswordResponseModel other = (ChangePasswordResponseModel) obj;
        if (errorMessages == null) {
            if (other.errorMessages != null)
                return false;
        } else if (!errorMessages.equals(other.errorMessages))
            return false;
        return true;
    }
}
