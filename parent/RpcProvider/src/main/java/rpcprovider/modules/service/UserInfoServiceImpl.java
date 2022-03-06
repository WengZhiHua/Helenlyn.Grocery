package rpcprovider.modules.service;

import com.alibaba.dubbo.config.annotation.Service;
import rpccommon.dto.UserInfo;
import rpccommon.service.UserInfoService;

/**
 * @author brand
 * @Description:
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/3/5 下午5:43
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public UserInfo getUserInfo(int userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setAge(18);
        userInfo.setSex("男");
        userInfo.setUserName("Brand");
        return userInfo;
    }

    @Override
    public String getHello(int userId) {
        return " Hello, " + userId;
    }
}
