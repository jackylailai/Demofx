package org.example.modelDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {


    private String username;

    private String password;

    private String ctxId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCtxId() {
        return ctxId;
    }

    public void setCtxId(String ctxId) {
        this.ctxId = ctxId;
    }
}

