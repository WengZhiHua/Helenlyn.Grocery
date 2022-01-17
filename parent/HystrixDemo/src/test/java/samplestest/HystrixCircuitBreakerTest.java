package samplestest;

import hystrixdemo.samples.HystrixCircuitBreaker;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author brand
 * @Description: 熔断相关
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/8 下午5:50
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class HystrixCircuitBreakerTest {
    @Test
    public void testSynchronous() throws IOException {
        for(int i = 0; i < 50; i++) {
            try {
                System.out.println("===========" + new HystrixCircuitBreaker(String.valueOf(i)).execute());
//	        		try {
//	            		TimeUnit.MILLISECONDS.sleep(1000);
//	            	}catch(Exception e) {}
//	        		Future<String> future = new HystrixCommand4CircuitBreakerTest("Hlx"+i).queue();
//	        		System.out.println("===========" + future);
            } catch(Exception e) {
                System.out.println("run()抛出HystrixBadRequestException时，被捕获到这里" + e.getCause());
            }
        }

        System.out.println("------开始打印现有线程---------");
        Map<Thread, StackTraceElement[]> map=Thread.getAllStackTraces();
        for (Thread thread : map.keySet()) {
            System.out.println(thread.getName());
        }
        System.out.println("thread num: " + map.size());

        System.in.read();
    }
}
