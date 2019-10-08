package com.example.rabbit_config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 杨军
 * @version 1.0
 * @date 2019/10/08 0008 下午 01:24
 */
@Configuration
public class config {
    //创建队列
    @Bean
    public Queue queue(){
        return QueueBuilder.durable("queue-1").build();
    }
    //创建交换机,topic通配符模式
    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange("exchange-ex-queue1").durable(true).build();
    }
    //绑定交换机,#表示尾缀可以是任意字符串
    @Bean
    public Binding binding(Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("user.#").noargs();
    }
}
