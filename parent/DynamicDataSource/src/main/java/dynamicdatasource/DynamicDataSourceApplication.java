package dynamicdatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author brand
 * @Description:
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午12:05
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class
        }
)
@EnableScheduling
@EnableCaching
public class DynamicDataSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicDataSourceApplication.class, args);
        System.out.println("start dynamic module");
    }
}