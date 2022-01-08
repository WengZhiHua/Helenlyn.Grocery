package samplestest;

import hystrixdemo.samples.HystrixException;
import hystrixdemo.samples.HystrixTimeOut;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author brand
 * @Description: 测试异常/超时 fallBack
 * @Copyright: Copyright (c) 2022
 * @Company: Baidu, Inc. All Rights Reserved.
 * @date 2022/1/8 下午5:35
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class ExceptionTimeOutFallBackTest {
    @Test
    public void testException() throws IOException {
        try {
            assertEquals("fallback: Hlx", new HystrixException("Hlx").execute());
        } catch(Exception e) {
            System.out.println("run()抛出HystrixBadRequestException时，会被捕获到这里" + e.getCause());
        }
//        	System.in.read();
    }

    @Test
    public void testTimeOut() throws IOException {
        try {
            assertEquals("fallback: Hlx", new HystrixTimeOut("Hlx").execute());
        } catch(Exception e) {
            System.out.println("run()抛出HystrixBadRequestException时，会被捕获到这里" + e.getCause());
        }
//        	System.in.read();
    }
}