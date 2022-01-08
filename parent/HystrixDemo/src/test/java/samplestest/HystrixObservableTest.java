package samplestest;

import hystrixdemo.samples.HystrixCommand;
import org.junit.Test;
import rx.Observable;
import rx.Observer;

/**
 * @author brand
 * @Description: observe() 命令
 * @Copyright: Copyright (c) 2022
 * @Company: Baidu, Inc. All Rights Reserved.
 * @date 2022/1/8 下午5:10
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class HystrixObservableTest {

    /**
     * HystrixCommand的observe()与toObservable()的区别：
     * 1）observe()会立即执行HelloWorldHystrixCommand.run()；toObservable()要在toBlocking().single()或subscribe()时才执行HelloWorldHystrixCommand.run()
     * 2）observe()中，toBlocking().single()和subscribe()可以共存；在toObservable()中不行，因为两者都会触发执行HelloWorldHystrixCommand.run()，这违反了同一个HelloWorldHystrixCommand对象只能执行run()一次原则
     * @throws Exception
     */

    @Test
    public void observableTest() throws Exception {

        // observe()是异步非堵塞性执行，同queue
        Observable<String> hotObservable = new HystrixCommand("Hlx").observe();

        // single()是堵塞的
        System.out.println("hotObservable single结果：" + hotObservable.toBlocking().single());

        // 注册观察者事件
        // subscribe()是非堵塞的
        hotObservable.subscribe(new Observer<String>() {

            // 先执行onNext再执行onCompleted
            // @Override
            public void onCompleted() {
                System.out.println("hotObservable completed");
            }

            // @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            // @Override
            public void onNext(String v) {
                System.out.println("hotObservable onNext: " + v);
            }

        });

        // 非堵塞
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        hotObservable.subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {

            }

            // 相当于上面的onNext()
            // @Override
            public void call(String v) {
                System.out.println("hotObservable call: " + v);
            }

        });

        // 主线程不直接退出，在此一直等待其他线程执行
        System.in.read();

    }
}
