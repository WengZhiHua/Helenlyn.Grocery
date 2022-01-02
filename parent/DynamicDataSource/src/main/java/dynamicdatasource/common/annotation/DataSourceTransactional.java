package dynamicdatasource.common.annotation;

import java.lang.annotation.*;

/**
 * @author brand
 * @Description: 数据源切换注解,带事务
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午2:22
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceTransactional {
    String name() default "";
}