package dynamicdatasource.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author brand
 * @Description: 路由到数据源：核心
 * @Copyright: Copyright (c) 2021
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2021/7/16 10:17 上午
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class RoutingDataSource extends AbstractRoutingDataSource {
    private Map<Object, Object> targetDataSources;
    private Object defaultTargetDataSource;
    private boolean lenientFallback = true;
    private DataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
    private Map<Object, DataSource> resolvedDataSources;
    private DataSource resolvedDefaultDataSource;

    public RoutingDataSource() {
    }

    /**
     * 设置目标数据源集合
     * @param targetDataSources
     */
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this.targetDataSources = targetDataSources;
    }

    /**
     * 获取目标数据源
     * @return
     */
    public Map<Object, Object> getTargetDataSources() {
        return this.targetDataSources;
    }

    /**
     * 设置默认数据源
     * @param defaultTargetDataSource
     */
    @Override
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        this.defaultTargetDataSource = defaultTargetDataSource;
    }

    @Override
    public void setLenientFallback(boolean lenientFallback) {
        this.lenientFallback = lenientFallback;
    }

    @Override
    public void setDataSourceLookup(DataSourceLookup dataSourceLookup) {
        this.dataSourceLookup = (DataSourceLookup)
                (dataSourceLookup != null ? dataSourceLookup : new JndiDataSourceLookup());
    }

    @Override
    public void afterPropertiesSet() {
        if (this.targetDataSources == null) {
            throw new IllegalArgumentException("Property 'targetDataSources' is required");
        } else {
            this.resolvedDataSources = new HashMap(this.targetDataSources.size());
            Iterator var1 = this.targetDataSources.entrySet().iterator();

            while (var1.hasNext()) {
                Map.Entry<Object, Object> entry = (Map.Entry) var1.next();
                Object lookupKey = this.resolveSpecifiedLookupKey(entry.getKey());
                DataSource dataSource = this.resolveSpecifiedDataSource(entry.getValue());
                this.resolvedDataSources.put(lookupKey, dataSource);
            }

            if (this.defaultTargetDataSource != null) {
                this.resolvedDefaultDataSource = this.resolveSpecifiedDataSource(this.defaultTargetDataSource);
            }

        }
    }

    @Override
    protected Object resolveSpecifiedLookupKey(Object lookupKey) {
        return lookupKey;
    }

    @Override
    protected DataSource resolveSpecifiedDataSource(Object dataSource) throws IllegalArgumentException {
        if (dataSource instanceof DataSource) {
            return (DataSource) dataSource;
        } else if (dataSource instanceof String) {
            return this.dataSourceLookup.getDataSource((String) dataSource);
        } else {
            throw new IllegalArgumentException("Illegal data source value - " +
                    "only [javax.sql.DataSource] and String supported: " + dataSource);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.determineTargetDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return this.determineTargetDataSource().getConnection(username, password);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return iface.isInstance(this) || this.determineTargetDataSource().isWrapperFor(iface);
    }

    /**
     * 根据 lookupkey 获取到真正的目标数据源
     * @return
     */
    @Override
    protected DataSource determineTargetDataSource() {
        Assert.notNull(this.targetDataSources, "DataSource router not initialized");
        Object lookupKey = this.determineCurrentLookupKey();
        DataSource dataSource = (DataSource) this.targetDataSources.get(lookupKey);
        if (dataSource == null && (this.lenientFallback || lookupKey == null)) {
            dataSource = this.resolvedDefaultDataSource;
        }

        if (dataSource == null) {
            throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
        } else {
            return dataSource;
        }
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceRouteHolder.getDataSourceRouteKey();
    }
}