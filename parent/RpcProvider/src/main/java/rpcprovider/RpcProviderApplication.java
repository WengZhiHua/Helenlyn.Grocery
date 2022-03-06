package rpcprovider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author brand
 * @Description:
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/3/5 下午5:42
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@EnableDubbo
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class RpcProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcProviderApplication.class, args);
        System.out.println("start rpc provider module");
    }
}
