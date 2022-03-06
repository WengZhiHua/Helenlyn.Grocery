package rpccommon.service;

import rpccommon.dto.UserInfo;

/**
 * @author brand
 * @Description: 用户信息接口
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/3/5 下午5:29
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public interface UserInfoService {
    UserInfo getUserInfo (int userId) ;
    String getHello (int userId) ;
}
