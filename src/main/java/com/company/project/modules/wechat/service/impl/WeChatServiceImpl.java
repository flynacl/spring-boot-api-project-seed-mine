package com.company.project.modules.wechat.service.impl;

import com.company.project.modules.user.model.User;
import com.company.project.modules.wechat.service.WeChatService;
import org.springframework.stereotype.Service;

@Service
public class WeChatServiceImpl implements WeChatService {

    @Override
    public User getUserByOpenId(String openId) {
        return null;
    }
}
