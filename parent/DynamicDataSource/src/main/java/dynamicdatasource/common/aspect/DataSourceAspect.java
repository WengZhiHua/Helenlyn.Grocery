package dynamicdatasource.common.aspect;

import dynamicdatasource.common.annotation.DataSource;
import dynamicdatasource.common.db.DynamicDataSource;
import dynamicdatasource.common.db.DynamicDataSourceRouteHolder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author brand
 * @Description:
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午2:23
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {
    /**
     * 定义一个切入点，匹配注解DataSource
     */
    @Pointcut("@annotation(dynamicdatasource.common.annotation.DataSource)")
    public void dataSourcePointCut() {

    }

    /**
     * Around 环绕方式做切面注入
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSource ds = method.getAnnotation(DataSource.class);
        String routeKey = ds.name();
        String dataSourceRouteKey = DynamicDataSourceRouteHolder.getDataSourceRouteKey();
        if (StringUtils.isNotEmpty(dataSourceRouteKey)) {
            // StringBuilder currentRouteKey = new StringBuilder(dataSourceRouteKey);
            routeKey = ds.name();
        }
        DynamicDataSourceRouteHolder.setDataSourceRouteKey(routeKey);
        try {
            return point.proceed();
        } finally { // 最后做清除，这个步骤很重要，使得程序可以回到默认的数据源
            DynamicDataSource.clearDataSource();
            DynamicDataSourceRouteHolder.clearDataSourceRouteKey();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}