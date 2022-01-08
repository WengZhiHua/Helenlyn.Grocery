package hystrixdemo.samples;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * @author brand
 * @Description: 信号量隔离
 * @Copyright: Copyright (c) 2022
 * @Company: Baidu, Inc. All Rights Reserved.
 * @date 2022/1/8 下午6:04
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class HystrixSemaphore extends HystrixCommand<String> {
    /**
     * 测试信号量隔离
     * 默认执行run()用的是主线程，为了模拟并行执行command，这里我们自己创建多个线程来执行command
     * 设置ExecutionIsolationSemaphoreMaxConcurrentRequests为3，意味着信号量最多允许执行run的并发数为3，超过则触发降级，即不执行run而执行getFallback
     * 设置FallbackIsolationSemaphoreMaxConcurrentRequests为1，意味着信号量最多允许执行fallback的并发数为1，超过则抛异常fallback execution rejected
     */

    private final String name;

    public HystrixSemaphore(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SemaphoreTestGroup"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("SemaphoreTestKey"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("SemaphoreTestThreadPoolKey"))
                        .andCommandPropertiesDefaults(	// 配置信号量隔离
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)	// 信号量隔离
                                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(3)
                                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(1)
                        )
                // 设置了信号量隔离后，线程池配置将变无效
//                .andThreadPoolPropertiesDefaults(
//                		HystrixThreadPoolProperties.Setter()
//                		.withCoreSize(13)	// 配置线程池里的线程数
//                )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "run(): name="+name+"，线程名是" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "getFallback(): name="+name+"，线程名是" + Thread.currentThread().getName();
    }
}