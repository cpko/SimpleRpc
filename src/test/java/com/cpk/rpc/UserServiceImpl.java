package com.cpk.rpc;

/**
 * Author:cp
 * Created on 10/23/15.
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findByUsername(String username) {
        //mock
        User user = new User();
        user.setId(1L);
        user.setUsername("jack");
        user.setPassword("123456");
        user.setMobile("18969973054");
        return user;
    }
}
