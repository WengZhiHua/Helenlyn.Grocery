package dynamicdatasource.modules.userinfo.service.impl;

import dynamicdatasource.modules.userinfo.dao.UserInfoDao;
import dynamicdatasource.modules.userinfo.dto.UserInfoDto;
import dynamicdatasource.modules.userinfo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author brand
 * @Description: 用户明细信息：Service层实现类
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午3:15
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    /**
     * 自动装配UserInfo的Dao层
     */
    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 根据用户工号获取用户信息
     *
     * @param userCode 用户工号
     * @return
     */
    @Override
    public UserInfoDto getUserInfo(String userCode) {
        return userInfoDao.getUserInfo(userCode);
    }
}