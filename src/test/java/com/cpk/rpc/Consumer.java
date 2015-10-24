package com.cpk.rpc;

/**
 * Author:cp
 * Created on 10/24/15.
 */
public class Consumer {
    public static void main(String[] args) {
        Rpc rpc = new Rpc();
        UserService userService = rpc.refer(UserService.class, "127.0.0.1", 6888);
        User user=userService.findByUsername("jack");
        System.out.println(user);
    }
}
