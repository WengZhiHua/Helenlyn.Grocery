package dynamicdatasource.modules.userinfo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * @author brand
 * @Description: 用户信息实体类
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午3:14
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Getter
@Setter
public class UserInfoDto {
    /**
     * 用户编号
     */
    @JSONField(name="usercode")
    private String userCode;

    /**
     * 用户姓名
     */
    @JSONField(name="username")
    private String userName;

    /**
     * 用户性别：0 男，1 女
     */
    @JSONField(name="usersex")
    private Integer userSex;
}