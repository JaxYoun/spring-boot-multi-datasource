package com.yang.springbootmultidatasource.service.impl;

import com.yang.springbootmultidatasource.dao.one.UserMapperOne;
import com.yang.springbootmultidatasource.entity.User;
import com.yang.springbootmultidatasource.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author: Yang
 * @date: 2019/7/21 13:44
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapperOne userMapperOne;

    @Override
    public Set<User> getUser() {
        return this.userMapperOne.getUser();
    }
}
