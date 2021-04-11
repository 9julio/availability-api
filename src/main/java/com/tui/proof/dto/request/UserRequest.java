package com.tui.proof.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class UserRequest {

    @Getter
    @Setter
    private String user;

    @Getter
    @Setter
    private String password;

    public UserRequest(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return Objects.equals(user, that.user) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, password);
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "user='" + user + '\'' +
                ", password=*****" +
                '}';
    }
}
