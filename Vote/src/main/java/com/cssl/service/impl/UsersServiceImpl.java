package com.cssl.service.impl;

import com.cssl.mapper.UsersMapper;
import com.cssl.pojo.Users;
import com.cssl.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper um;

    @Override
    public boolean register(Users users) {
        int row=um.add(users);
        if(row>0)
            return true;
        return false;
    }

    @Override
    public Users login(Users users) {
        return um.getOne(users);
    }

    @Override
    public boolean isExists(String username) {
        int row=um.getByUsername(username);
        if(row>0)
            return true;
        return false;
    }
}
