package dynamicdatasource.modules.userinfo.service;

import dynamicdatasource.modules.userinfo.dto.UserInfoDto;

/**
 * @author brand
 * @Description: 用户明细信息：Service层接口
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午3:08
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public interface UserInfoService {
    /**
     *  根据用户编号获取用户信息
     * @param userCode 用户编号
     * @return
     */
    UserInfoDto getUserInfo(String userCode);
}
