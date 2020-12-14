package com.example.demo.receiver;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件描述
 *
 * @author sujia
 * @date 2020年12月11日 15:46
 */
@Component
@RabbitListener(queues = "topic.saveUser")
public class TopicSaveUserReceiver {

    @Autowired
    private UserServiceImpl userService;

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicSaveUserReceiver消费者收到消息  : " + testMessage.toString());
        String messageId = testMessage.get("messageId").toString();
        String messageData = testMessage.get("messageData").toString();
        JSONObject jsonObject = JSON.parseObject(messageData);
        User user = jsonObject.toJavaObject(User.class);
        String createTime = testMessage.get("createTime").toString();
        System.out.println("this is user = "+user);
        userService.insertUser(user);
    }
}
