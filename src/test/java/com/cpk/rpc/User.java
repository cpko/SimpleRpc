package com.cpk.rpc;

import java.io.Serializable;

/**
 * Author:cp
 * Created on 10/23/15.
 */
public class User implements Serializable {

    private static final long serialVersionUID = -49293661687511188L;

    private Long id;

    private String username;

    private String password;

    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "{" + "id:" + id + ",username:" + username + ",password:" + password + ",mobile:" + mobile + "}";
    }
}
