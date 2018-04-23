package com.company.project.modules.wechat.service;

import com.company.project.modules.user.model.User;

public interface WeChatService {
    User getUserByOpenId(String openId);
}
