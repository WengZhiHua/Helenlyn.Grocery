package rpcconsume.modules.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import rpccommon.dto.UserInfo;
import rpccommon.service.UserInfoService;

/**
 * @author brand
 * @Description: 消费者使用注解方式进行RPC调用
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/3/5 下午6:49
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Service
public class UserInfoConsumer implements UserInfoService {
    @Reference
    private UserInfoService userInfoService ;

    @Override
    public UserInfo getUserInfo(int userId) {
        return userInfoService.getUserInfo(userId);
    }

    @Override
    public String getHello(int userId) {
        return userInfoService.getHello(userId);
    }
}
