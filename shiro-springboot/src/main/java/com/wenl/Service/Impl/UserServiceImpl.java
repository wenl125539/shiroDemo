package com.wenl.Service.Impl;

import com.wenl.Service.UserService;
import com.wenl.mapper.UserMapper;
import com.wenl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    public User findUserByName(String name){
        return mapper.queryUserByName(name);
    }
}
