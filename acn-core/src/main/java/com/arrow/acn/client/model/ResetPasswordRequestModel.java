package com.arrow.acn.client.model;

import java.io.Serializable;

public class ResetPasswordRequestModel implements Serializable {
    private static final long serialVersionUID = -4001682057856614908L;

    private boolean sendEmail;
    private String url;

    public ResetPasswordRequestModel withSendEmail(boolean sendEmail) {
        setSendEmail(sendEmail);
        return this;
    }

    public ResetPasswordRequestModel withUrl(String url) {
        setUrl(url);
        return this;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (sendEmail ? 1231 : 1237);
        result = prime * result + ((url == null) ? 0 : url.hashCode());
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
        ResetPasswordRequestModel other = (ResetPasswordRequestModel) obj;
        if (sendEmail != other.sendEmail)
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }
}
