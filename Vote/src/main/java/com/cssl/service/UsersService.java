package com.cssl.service;

import com.cssl.pojo.Users;

public interface UsersService {

    boolean register(Users users);

    Users login(Users users);

    boolean isExists(String username);
}
