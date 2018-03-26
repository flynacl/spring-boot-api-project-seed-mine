package com.company.project.modules.user.service.impl;

import com.company.project.configurer.security.SecurityModelFactory;
import com.company.project.configurer.security.model.UserDetailImpl;
import com.company.project.core.AbstractService;
import com.company.project.modules.user.dao.UserMapper;
import com.company.project.modules.user.model.User;
import com.company.project.modules.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author flynacl
 * @date 2018-03-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends AbstractService<User> implements UserService, UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> login(User user) {
        Condition condition = new Condition(user.getClass());
        condition.createCriteria().andEqualTo(user);
        return userMapper.selectByCondition(condition);
    }

    /**
     * 获取 userDetail
     *
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailImpl userDetail = new UserDetailImpl();
        userDetail.setUsername(username);
        com.company.project.configurer.security.model.User user = this.userMapper.selectUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return SecurityModelFactory.create(user);
        }
    }
}
