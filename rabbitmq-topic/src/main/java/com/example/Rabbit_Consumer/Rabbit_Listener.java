package com.example.Rabbit_Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 杨军
 * @version 1.0
 * @date 2019/10/08 0008 下午 01:58
 */
@Component
public class Rabbit_Listener {
  //需要监听的队列
  @RabbitListener(queues = "queue-1")
  public void processQueue(String msg) {
    System.out.println(msg);
  }
}
