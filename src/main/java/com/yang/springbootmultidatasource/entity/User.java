package com.yang.springbootmultidatasource.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Yang
 * @date: 2019/7/21 13:38
 * @description:
 */
@Data
@Builder
public class User {

    private Integer id;

    private String name;

    private Integer age;

    private Boolean isMarried;

}
