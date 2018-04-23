package com.company.project.modules.user.service;

import com.company.project.core.Service;
import com.company.project.modules.user.model.User;

import java.util.List;

/**
 * @author flynacl
 * @date 2018-03-26
 */
public interface UserService extends Service<User> {

    List<User> login(User user);

    com.company.project.configurer.security.model.User getUserByOpenId(String openId);
}
