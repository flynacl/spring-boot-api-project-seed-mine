package com.company.project.modules.user.dao;

import com.company.project.core.Mapper;
import com.company.project.modules.user.model.User;

public interface UserMapper extends Mapper<User> {

    com.company.project.configurer.security.model.User selectUserByUsername(String username);

    com.company.project.configurer.security.model.User selectUserByOpenId(String openId);
}