package com.example.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨军
 * @version 1.0
 * @date 2019/10/08 0008 下午 01:50
 */
@RestController
public class Provider_Controller {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/hello/{msg}")
    public String bestow(@PathVariable String msg){
        rabbitTemplate.convertAndSend("exchange-ex-queue1", "user.a", msg);
        return "ok";
    }
}
