package com.yang.springbootmultidatasource.service;

import com.yang.springbootmultidatasource.entity.User;

import java.util.Set;

/**
 * @author: Yang
 * @date: 2019/7/21 13:43
 * @description:
 */
public interface UserService {

    Set<User> getUser();

}
