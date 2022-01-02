package dynamicdatasource.modules.userinfo.dao;

import dynamicdatasource.modules.userinfo.dto.UserInfoDto;

/**
 * @author brand
 * @Description: 用户明细信息：Dao层接口
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午3:21
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public interface UserInfoDao {
    /**
     *  根据用户编号获取用户信息
     * @param userCode 用户工号
     * @return
     */
    UserInfoDto getUserInfo(String userCode);
}