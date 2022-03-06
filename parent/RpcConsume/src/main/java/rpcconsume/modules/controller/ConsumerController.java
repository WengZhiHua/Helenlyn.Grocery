package rpcconsume.modules.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rpccommon.dto.UserInfo;
import rpcconsume.modules.service.UserInfoConsumer;

/**
 * @author brand
 * @Description: 测试消费者效果
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/3/5 下午6:48
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@RestController
@Slf4j
@RequestMapping("/v1.0/consumer")
public class ConsumerController {
    @Autowired
    private UserInfoConsumer userInfoConsumer ;

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/userinfo/{user_id}", method = RequestMethod.GET)
    public UserInfo getUserInfo(@PathVariable("user_id") int userId) {
        return userInfoConsumer.getUserInfo(userId);
    }


    /**
     * 获取问候信息
     * @return
     */
    @RequestMapping(value = "/hello/{user_id}", method = RequestMethod.GET)
    public String getHello(@PathVariable("user_id") int userId) {
        return userInfoConsumer.getHello(userId);
    }
}
