package dynamicdatasource.common.db;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author brand
 * @Description: 动态数据源
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午2:42
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Component
public class DynamicDataSourceRouteHolder {

    /**
     * 按照 routingDataSource 名称注入依赖
     * 注入 数据库路由列表
     */
    @Resource(name = "routingDataSource")
    private RoutingDataSource routingDataSource;

    /**
     * 当前上下文路由列表
     */
    private static RoutingDataSource basicDataSource;

    @PostConstruct
    public void init() {
        basicDataSource = routingDataSource;
    }

    private static ThreadLocal<String> dataSourceRouteKey = new ThreadLocal<>();

    /**
     * 获取key
     * @return
     */
    public static String getDataSourceRouteKey() {
        return dataSourceRouteKey.get();
    }

    /**
     * 设置key
     * @param key
     */
    public static void setDataSourceRouteKey(String key) {
        dataSourceRouteKey.set(key);
    }

    /**
     * 清理key
     */
    public static void clearDataSourceRouteKey() {
        dataSourceRouteKey.remove();
    }

    /**
     * 添加数据源
     * @param key
     * @param dataSource
     */
    public static void addDataSource(String key, Object dataSource) {
        Map<Object, Object> map = basicDataSource.getTargetDataSources();
        if (map != null && !map.containsKey(key)) {
            map.put(key, dataSource);
        }
    }

    /**
     * 判断数据源是否存在
     * @param key
     * @return
     */
    public static Boolean existDataSource(String key) {
        Map<Object, Object> map = basicDataSource.getTargetDataSources();
        if (map != null && map.containsKey(key)) {
            return true;
        }
        return false;
    }
}