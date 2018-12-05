package com.cssl.mapper;

import com.cssl.pojo.Users;

public interface UsersMapper {

    int add(Users users);

    int getByUsername(String username);

    Users getOne(Users users);
}
