package com.yang.springbootmultidatasource.controller;

import com.yang.springbootmultidatasource.dao.one.UserMapperOne;
import com.yang.springbootmultidatasource.dao.two.UserMapperTwo;
import com.yang.springbootmultidatasource.entity.User;
import com.yang.springbootmultidatasource.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author: Yang
 * @date: 2019/7/21 11:08
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserMapperOne userMapperOne;

    @Resource
    private UserMapperTwo userMapperTwo;

    @GetMapping("/getUser")
    public Object getUser() {
        Set<User> userSet = this.userService.getUser();
        userSet.addAll(this.userMapperTwo.getUser(

        ));
        return userSet;
    }

    @GetMapping("/updateUser/{name}/{age}/{isMarried}")
    public Object updateUser(@PathVariable("name") String name, @PathVariable("age") Integer age, @PathVariable("isMarried") Boolean isMarried) {
        User user = User.builder().name(name).age(age).isMarried(isMarried).build();
        int first = this.userMapperOne.updateUser(user);
        int second = this.userMapperTwo.updateUser(user);
        return second;
    }

}
