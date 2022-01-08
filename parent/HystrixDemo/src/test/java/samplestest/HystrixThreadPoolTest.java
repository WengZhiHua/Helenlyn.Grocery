package samplestest;

import hystrixdemo.samples.HystrixThreadPool;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author brand
 * @Description: 线程池隔离测试
 * @Copyright: Copyright (c) 2022
 * @Company: Baidu, Inc. All Rights Reserved.
 * @date 2022/1/8 下午6:01
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class HystrixThreadPoolTest {
    @Test
    public void testSynchronous() throws IOException {
        for(int i = 0; i < 10; i++) {
            try {
//	        		assertEquals("fallback: Hlx", new HystrixCommand4ThreadPoolTest("Hlx").execute());
//	        		System.out.println("===========" + new HystrixCommand4ThreadPoolTest("Hlx").execute());
                Future<String> future = new HystrixThreadPool("Hlx"+i).queue();
//	        		System.out.println("===========" + future);
            } catch(Exception e) {
                System.out.println("run()抛出HystrixBadRequestException时，被捕获到这里" + e.getCause());
            }
        }
        for(int i = 0; i < 20; i++) {
            try {
                //        		assertEquals("fallback: Hlx", new HystrixCommand4ThreadPoolTest("Hlx").execute());
                System.out.println("===========" + new HystrixThreadPool("Hlx").execute());
//	        		Future<String> future = new HystrixCommand4ThreadPoolTest("Hlx1"+i).queue();
//	        		System.out.println("===========" + future);
            } catch(Exception e) {
                System.out.println("run()抛出HystrixBadRequestException时，被捕获到这里" + e.getCause());
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        }catch(Exception e) {}
        System.out.println("------开始打印现有线程---------");
        Map<Thread, StackTraceElement[]> map=Thread.getAllStackTraces();
        for (Thread thread : map.keySet()) {
            System.out.println(thread.getName());
        }
        System.out.println(map);
        System.out.println("thread num: " + map.size());
//        	int numExecuted = HystrixRequestLog.getCurrentRequest().getAllExecutedCommands().size();
//            System.out.println("num executed: " + numExecuted);
        System.in.read();
    }
}
