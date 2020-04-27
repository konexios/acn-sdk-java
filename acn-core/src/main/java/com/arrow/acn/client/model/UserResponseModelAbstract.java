package com.arrow.acn.client.model;

import com.arrow.acs.client.model.StatusModelAbstract;

public abstract class UserResponseModelAbstract<T extends UserResponseModelAbstract<T>> extends StatusModelAbstract<T> {
    private static final long serialVersionUID = 8024113461039476969L;

    private UserModel user;

    public T withUser(UserModel user) {
        setUser(user);
        return self();
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        UserResponseModelAbstract<?> other = (UserResponseModelAbstract<?>) obj;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }
}
