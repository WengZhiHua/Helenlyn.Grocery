package samplestest;

import hystrixdemo.samples.HystrixCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author brand
 * @Description: excute() 命令
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/8 下午4:56
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class HystrixExecuteTest {
    @Test
    public void executeTest() {
        // execute()是同步堵塞式执行：先创建一个线程运行HelloWorldHystrixCommand.run()，再返回往下执行
        // 一个对象只能execute()一次
        // execute()事实上是queue().get()
//		System.out.println("mainthread:" + Thread.currentThread().getName());
        String executeResult = new HystrixCommand("Hlx").execute();
        System.out.println("execute同步结果：" + executeResult);
        assertEquals("Hello", executeResult.substring(0, 5));
    }
}
