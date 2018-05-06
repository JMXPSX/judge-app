package com.judge.dredd.dto;

import java.io.Serializable;

public class AppUserDto implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    private String username;

    private String password;

    AppUserDto(){}

    public AppUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

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

}
