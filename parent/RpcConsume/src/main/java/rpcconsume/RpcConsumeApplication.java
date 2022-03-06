package rpcconsume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
/**
 * @author brand
 * @Description:
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/3/5 下午6:50
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableDubbo
public class RpcConsumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcConsumeApplication.class, args);
        System.out.println("start rpc consumer module");
    }
}
