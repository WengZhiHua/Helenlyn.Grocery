package dynamicdatasource.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author brand
 * @Description: 数据源核心部分数据结构
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午2:51
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Getter
@Setter
public class DataSourceCore {
    /**
     * url地址
     */
    private String url;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String passWord;
}