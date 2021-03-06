package four_week;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework {

    public static void main(String[] args) {
        Homework homework = new Homework();

        //方法1
        Thread ta = new Thread(new Runnable() {
            @Override
            public void run() {
                int result = homework.sum();
                System.out.println("异步计算结果为：" + result);
            }
        });
        ta.start();

        //方法2
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int result = homework.sum();
                System.out.println("异步计算结果为：" + result);
            }
        });
        long start = System.currentTimeMillis();


        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
