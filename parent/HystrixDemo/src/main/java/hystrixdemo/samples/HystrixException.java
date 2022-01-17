package hystrixdemo.samples;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * @author brand
 * @Description: 故障fallBack
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/8 下午5:32
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class HystrixException extends HystrixCommand<String> {

    /**
     *
     * 以下四种情况将触发getFallback调用：
     * 1）run()方法抛出非HystrixBadRequestException异常
     * 2）run()方法调用超时
     * 3）熔断器开启拦截调用
     * 4）线程池/队列/信号量是否跑满
     *
     * 实现getFallback()后，执行命令时遇到以上4种情况将被fallback接管，不会抛出异常或其他
     * 下面演示的是异常的情况
     */

    private final String name;

    public HystrixException(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("Command Group:fallbackGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {

        /*---------------以下三种情况触发fallback-------------------*/
        // 1.循环+等待，超时fallBack
//    	int i = 0;
//    	while (true) {
//    		i++;
//            Thread.currentThread().sleep(1000);
//    	}

        // 2.除零导致异常
    	 int i = 1/0;

        // 3.主动抛出异常
        // throw new Exception("command trigger fallback");


        /*---------------直接抛出HystrixBadRequestException，不触发fallback-----------------*/
        // HystrixBadRequestException,这个是非法参数或非系统错误引起，不触发fallback，也不被计入熔断器
        // throw new HystrixBadRequestException("HystrixBadRequestException not trigger fallback");

        return "success";
    }

    @Override
    protected String getFallback() {
        return "fallback: " + name;
    }

}
