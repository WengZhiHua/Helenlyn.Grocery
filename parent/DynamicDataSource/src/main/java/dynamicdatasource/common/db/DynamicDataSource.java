package dynamicdatasource.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author brand
 * @Description: 动态数据源
 * @Copyright: Copyright (c) 2021
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2021/7/16 10:33 上午
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 用来保存数据源与获取数据源
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();

    /**
     * 构造，包含一个默认数据源，和一个数据源集合
     * @param defaultTargetDataSource
     * @param targetDataSources
     */
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(new HashMap<Object, Object>(targetDataSources));
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}