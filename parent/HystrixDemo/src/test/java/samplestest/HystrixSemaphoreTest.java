package samplestest;

import hystrixdemo.samples.HystrixCommand;
import hystrixdemo.samples.HystrixSemaphore;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author brand
 * @Description: 信号量隔离测试
 * @Copyright: Copyright (c) 2022
 * @Company: Baidu, Inc. All Rights Reserved.
 * @date 2022/1/8 下午6:06
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class HystrixSemaphoreTest {
    @Test
    public void testSynchronous() throws IOException {

        try {
            Thread.sleep(2000);
            for(int i = 0; i < 5; i++) {
                final int j = i;
                // 自主创建线程来执行command，创造并发场景
                Thread thread = new Thread(new Runnable() {
                    //	                    @Override
                    public void run() {
                        // 这里执行两类command：HystrixCommand4SemaphoreTest设置了信号量隔离、HelloWorldHystrixCommand未设置信号量
                        System.out.println("-----------" + new HystrixCommand("HLX" + j).execute());
                        System.out.println("===========" + new HystrixSemaphore("HLX" + j).execute());	// 被信号量拒绝的线程从这里抛出异常
                        System.out.println("-----------" + new HystrixCommand("HLX" + j).execute());	// 被信号量拒绝的线程不能执行到这里

                    }
                });
                thread.start();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
//        	try {
//        		TimeUnit.MILLISECONDS.sleep(2000);
//        	}catch(Exception e) {}
        System.out.println("------开始打印现有线程---------");
        Map<Thread, StackTraceElement[]> map=Thread.getAllStackTraces();
        for (Thread thread : map.keySet()) {
            System.out.println(thread.getName());
        }
        System.out.println("thread num: " + map.size());
        System.in.read();
    }
}