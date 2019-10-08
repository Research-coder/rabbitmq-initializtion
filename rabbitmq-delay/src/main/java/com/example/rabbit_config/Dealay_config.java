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
public class Dealay_config {
    //创建超时队列
    @Bean
    public Queue queue_delay(){
        return QueueBuilder.durable("queue_delay")
                .withArgument("x-dead-letter-exchange","delay_exchange")
                .withArgument("x-dead-letter-routing-key","queue_dead")
                .build();
    }
    //真正监听消息的队列
    @Bean
    public Queue queue_dead(){
        return QueueBuilder.durable("queue_dead").build();
    }
    //创建交换机,topic通配符模式
    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange("delay_exchange").durable(true).build();
    }
    //绑定交换机,#表示尾缀可以是任意字符串
    @Bean
    public Binding binding(Queue queue_dead, Exchange delay_exchange){
        return BindingBuilder.bind(queue_dead).to(delay_exchange).with("queue_dead").noargs();
    }
}
