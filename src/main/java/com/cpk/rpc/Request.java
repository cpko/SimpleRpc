package com.cpk.rpc;

import java.io.Serializable;

/**
 * Author:cp
 * Created on 10/26/15.
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 3612376668022194134L;

    private long id;

    private String service;

    private String method;

    private Class<?>[] parameterTypes;

    private Object[] args;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
