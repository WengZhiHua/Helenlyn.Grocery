package dynamicdatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import dynamicdatasource.common.db.RoutingDataSource;
import dynamicdatasource.dto.DataSourceCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author brand
 * @Description: Druid配置
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午2:52
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Configuration
public class DruidConfig {

    @Bean(name = "basicDataSource")
    @ConfigurationProperties(prefix = "spring.mutildata.basic")
    public DruidDataSource basicDataSource() {
        return new DruidDataSource();
    }

    @Autowired
    private DataSourceCoreConfig dataSourceCoreConfig;

    /**
     * 动态集成可选的数据库路由，改掉之前硬编码的方式
     * @param basicDataSource
     * @return
     */
    @Bean(name = "routingDataSource")
    @Primary
    public RoutingDataSource routingDataSource(DruidDataSource basicDataSource) {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(16);
        HashMap<String, DataSourceCore>  mutildatacore = dataSourceCoreConfig.getMutilDataCore();
        routingDataSource.setDefaultTargetDataSource(basicDataSource);
        try {
            Iterator iter = mutildatacore.entrySet().iterator();
            while (iter.hasNext()) { // 轮询出所有的动态数据源
                Map.Entry entry = (Map.Entry) iter.next();
                String key = entry.getKey().toString();
                DataSourceCore dsc = (DataSourceCore) entry.getValue();
                DruidDataSource ds = (DruidDataSource) basicDataSource.clone();
                // 3个核心关键数据重新赋值
                ds.setUrl(dsc.getUrl());
                ds.setUsername(dsc.getUserName());
                ds.setPassword(dsc.getPassWord());
                dataSourceMap.put(key, ds);
            }
        }
        catch (Exception ex) {
            // Todo
        }
        routingDataSource.setTargetDataSources(dataSourceMap);
        return routingDataSource;
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        bean.addInitParameter("allow", "");
        bean.addInitParameter("loginUsername", "admin");
        bean.addInitParameter("loginPassword", "admin");
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>(16);
        initParams.put("exclusions", "*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);

        bean.setUrlPatterns(Collections.singletonList("/*"));

        return bean;
    }
}