package hystrixdemo.samples;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * @author brand
 * @Description: 执行 Execute
 * @Copyright: Copyright (c) 2022
 * @Company: Baidu, Inc. All Rights Reserved.
 * @date 2022/1/8 下午4:20
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class HystrixCommand extends com.netflix.hystrix.HystrixCommand<String> {

    private final String name;

    public HystrixCommand(String name) {
//		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testCommandGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
                /* 使用HystrixThreadPoolKey工厂定义线程池名称*/
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("testThreadPool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
//                		.withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)	// 信号量隔离
                        .withExecutionTimeoutInMilliseconds(5000)));
//		HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true);
//		HystrixCollapserProperties.Setter()
//		HystrixThreadPoolProperties.Setter().withCoreSize(1);
        this.name = name;
    }

//	@Override
//  protected String getFallback() {
//		System.out.println("触发了降级!");
//      return "exeucute fallback";
//  }

    @Override
    protected String run() throws InterruptedException, Exception {
//		for (int i = 0; i < 10; i++) {
//			System.out.println("runing HelloWorldHystrixCommand..." + i);
//		}
//
//		TimeUnit.MILLISECONDS.sleep(2000);
        return "Hello " + name + "! thread:" + Thread.currentThread().getName();
    }
}