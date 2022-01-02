package dynamicdatasource.common.annotation;

import java.lang.annotation.*;

/**
 * @author brand
 * @Description: 数据源切换注解
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午2:21
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Target({ ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}