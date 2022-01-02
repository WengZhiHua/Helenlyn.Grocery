package dynamicdatasource.modules.userinfo.controller;

import dynamicdatasource.common.annotation.DataSource;
import dynamicdatasource.common.base.ApiVersion;
import dynamicdatasource.common.constants.Constant;
import dynamicdatasource.modules.userinfo.dto.UserInfoDto;
import dynamicdatasource.modules.userinfo.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brand
 * @Description:
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午3:49
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@RestController
@Slf4j
@RequestMapping(ApiVersion.V10 + "/userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取配置文件信息
     * 默认情况下：数据源指向basic
     * @return
     */
    @RequestMapping(value = "/default/{user_code}", method = RequestMethod.GET)
    public UserInfoDto getUserInfo(@PathVariable("user_code") String userCode) {
        return userInfoService.getUserInfo(userCode);
    }

    /**
     * 获取配置文件信息
     * 数据源指向attend
     * @return
     */
    @DataSource(name= Constant.DATA_SOURCE_ATTEND_NAME)
    @RequestMapping(value = "/attend/{user_code}", method = RequestMethod.GET)
    public UserInfoDto getUserInfoAttend(@PathVariable("user_code") String userCode) {
        return userInfoService.getUserInfo(userCode);
    }

    /**
     * 获取配置文件信息
     * 数据源指向cloud
     * @return
     */
    @DataSource(name= Constant.DATA_SOURCE_CLOUD_NAME)
    @RequestMapping(value = "/cloud/{user_code}", method = RequestMethod.GET)
    public UserInfoDto getUserInfoCloud(@PathVariable("user_code") String userCode) {
        return userInfoService.getUserInfo(userCode);
    }
}