package rpccommon.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author brand
 * @Description: 用户信息实体
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/3/5 下午3:59
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Getter
@Setter
public class UserInfo implements Serializable {
    private Integer userId;
    private String userName;
    private Integer age;
    private String sex;
}
