package com.ntt.smartglass.rest.mapper;


import com.ntt.smartglass.common.mapper.TableMapper;
import com.ntt.smartglass.rest.entity.SmartGlass;
import com.ntt.smartglass.rest.entity.User;

/**
 * user table mapper
 */
public interface UserMapper extends TableMapper<User> {
    User selectByUserName(String name);


}