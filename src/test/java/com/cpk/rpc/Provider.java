package com.cpk.rpc;

import java.io.IOException;

/**
 * Author:cp
 * Created on 10/24/15.
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Rpc rpc = new Rpc();
        UserService userService = new UserServiceImpl();
        rpc.export(userService, 6888);
    }
}
