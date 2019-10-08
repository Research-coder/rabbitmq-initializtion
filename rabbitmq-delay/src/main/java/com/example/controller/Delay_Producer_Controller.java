package com.example.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 延时队列
 *
 * @author 杨军
 * @version 1.0
 * @date 2019/10/08 0008 下午 03:08
 */
@RestController
public class Delay_Producer_Controller {
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @RequestMapping("/dead/{msg}")
  public String delay_queue(@PathVariable String msg) {
    List<String> list=new ArrayList<>();
    list.add("卧槽");
    //queue_delay不是routingKey,而是指那个创建的超时队列名
    rabbitTemplate.convertAndSend("delay_exchange","queue_delay",msg,

      message -> {
          message.getMessageProperties().setExpiration("5000");
          return message;

    });
    return "ok";
  }
}
