package com.yang.springbootmultidatasource.dao.one;

import com.yang.springbootmultidatasource.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @author: Yang
 * @date: 2019/7/21 13:37
 * @description:
 */
@Mapper
public interface UserMapperOne {

    @Select("select * from t_user")
    Set<User> getUser();

    int updateUser(@Param("user") User user);

}
