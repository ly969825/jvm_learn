package cn.leafw.pubsub;

import cn.nns.pubsub.BizService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class PubsubApplicationTests {

    @Resource
    private BizService bizService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        bizService.start();
        bizService.pub(100);
    }

}
