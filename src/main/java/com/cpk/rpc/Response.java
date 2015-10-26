package com.cpk.rpc;

import java.io.Serializable;

/**
 * Author:cp
 * Created on 10/26/15.
 */
public class Response implements Serializable {

    private static final long serialVersionUID = -2833774168527693254L;

    private long requestId;

    private Object result;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
