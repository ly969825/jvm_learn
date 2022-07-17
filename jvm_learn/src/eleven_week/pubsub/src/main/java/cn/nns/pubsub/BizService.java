package cn.nns.pubsub;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 业务处理
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/7/11
 */
@Service
@Slf4j
public class BizService {

    private static final String QUEUE_KEY = "queue";

    @Resource
    private RedissonClient redissonClient;

    public void start() {
        RTopic topic = redissonClient.getTopic(QUEUE_KEY);
        topic.addListener(String.class, new MessageListener<String>() {
            @Override
            public void onMessage(CharSequence channel, String msg) {
                log.info("received msg, msg = {}", msg);
            }
        });
    }

    /**
     * 生产者
     */
    public void pub(int count) {
        RTopic topic = redissonClient.getTopic(QUEUE_KEY);
        while (count > 0) {
            String orderId = "orderId_" + count + "_" + System.currentTimeMillis();
            topic.publish(orderId);
            count --;
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

