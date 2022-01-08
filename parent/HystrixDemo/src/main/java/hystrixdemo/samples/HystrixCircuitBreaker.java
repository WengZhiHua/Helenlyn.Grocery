package hystrixdemo.samples;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * @author brand
 * @Description: 熔断
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/8 下午3:41
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class HystrixCircuitBreaker extends HystrixCommand<String> {

    /**
     * CircuitBreakerRequestVolumeThreshold设置为3，意味着10s内请求超过3次就触发熔断器
     * run()中无限循环使命令超时进入fallback，执行3次run后，将被熔断，进入降级，即不进入run()而直接进入fallback
     * 如果未熔断，但是threadpool被打满，仍然会降级，即不进入run()而直接进入fallback
     */

    private final String name;

    public HystrixCircuitBreaker(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CircuitBreakerTestGroup"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("CircuitBreakerTestKey"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("CircuitBreakerTest"))
                        .andThreadPoolPropertiesDefaults(    // 配置线程池
                                HystrixThreadPoolProperties.Setter()
                                        .withCoreSize(200)    // 配置线程池里的线程数，设置足够多线程，以防未熔断却打满threadpool
                        )
                        .andCommandPropertiesDefaults(    // 配置熔断器
                                HystrixCommandProperties.Setter()
                                        .withCircuitBreakerEnabled(true)
                                        .withCircuitBreakerRequestVolumeThreshold(3)
                                        .withCircuitBreakerErrorThresholdPercentage(80)
//                		.withCircuitBreakerForceOpen(true)	// 置为true时，所有请求都将被拒绝，直接到fallback
//                		.withCircuitBreakerForceClosed(true)	// 置为true时，将忽略错误
//                		.withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)	// 信号量隔离
//                		.withExecutionTimeoutInMilliseconds(5000)
                        )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("running run():" + name);
        int num = Integer.valueOf(name);
        if (num % 2 == 0 && num < 10) {    // 直接返回
            return name;
        } else {    // 无限循环模拟超时
            int j = 0;
            while (true) {
                j++;
            }
        }
//		return name;
    }

    @Override
    protected String getFallback() {
        return "CircuitBreaker fallback: " + name;
    }
}
