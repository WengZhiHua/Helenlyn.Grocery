package hystrixdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author brand
 * @Description:
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/8 下午2:21
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class HystrixDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDemoApplication.class, args);
        System.out.println("start hystrix module");
    }
}