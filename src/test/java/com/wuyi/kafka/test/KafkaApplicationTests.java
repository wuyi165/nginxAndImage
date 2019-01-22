package com.wuyi.kafka.test;


import com.wuyi.kafka.beans.Message;
import com.wuyi.kafka.provider.KafkaSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaApplicationTests {

    @Autowired
    private KafkaSender<Message> kafkaSender;

    @Test
    public void kafkaSend() throws InterruptedException {
        //发消息
        for (int i = 0; i < 5; i++) {

            Message message = new Message();
            message.setId(System.currentTimeMillis());
            message.setMsg(UUID.randomUUID().toString());
            message.setSendTime(new Date());

            kafkaSender.send(message);
            Thread.sleep(3000);

        }
    }

}
