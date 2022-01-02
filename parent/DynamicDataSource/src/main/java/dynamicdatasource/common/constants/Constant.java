package dynamicdatasource.common.constants;

/**
 * @author brand
 * @Description: 常量值
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午3:58
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public final class Constant {

    private Constant() {
        // nothing
    }

    /**
     * 数据源枚举，有多少个数据源，这边就配置多少个，
     * 值须与yaml配置中的保持一致
     */
    public static final String DATA_SOURCE_BASIC_NAME = "basic";
    public static final String DATA_SOURCE_ATTEND_NAME = "attend";
    public static final String DATA_SOURCE_CLOUD_NAME = "cloud";
}