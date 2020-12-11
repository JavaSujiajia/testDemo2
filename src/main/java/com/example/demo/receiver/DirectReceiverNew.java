package com.example.demo.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 文件描述
 *
 * @author sujia
 * @date 2020年12月10日 16:56
 */

@Component
@RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
public class DirectReceiverNew {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("DirectReceiverNew 消费者收到消息  : " + testMessage.toString());
    }

}