package com.css.mq.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @功能职责 topic模式配置文件
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
@Configuration
public class TopicProductConfig {
    /**
     * 声明交换机
     * Name：交换机名称
     * Durability：是否持久化。如果持久性，则RabbitMQ重启后，交换机还存在
     * Auto-delete：当所有与之绑定的消息队列都完成了对此交换机的使用后，删掉它
     * Arguments：扩展参数
     * */
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange("TOPIC_EXCHANGE",true,false);
    }
    /**
     * 声明队列
     * queue：这没什么好说的，队列名
     * durable：是否持久化，那么问题来了，这是什么意思？持久化，指的是队列持久化到数据库中。
     *          在之前的博文中也说过，如果RabbitMQ服务挂了怎么办，队列丢失了自然是不希望发生的。
     *          持久化设置为true的话，即使服务崩溃也不会丢失队列
     * exclusive：是否排外，what？ 这又是什么呢。
     *            设置了排外为true的队列只可以在本次的连接中被访问，也就是说在当前连接创建多少个channel访问都没有关系，
     *            但是如果是一个新的连接来访问,对不起，不可以，下面是我尝试访问了一个排外的queue报的错。
     *            还有一个需要说一下的是，排外的queue在当前连接被断开的时候会自动消失（清除）无论是否设置了持久化
     * autoDelete：这个就很简单了，是否自动删除。也就是说queue会清理自己。但是是在最后一个connection断开的时候
     * arguments：这个值得拿出来单讲一次，暂时不说
     * */
    @Bean
    public Queue getQueue(){
        return new Queue("Topic_Queue",true,false,false);
    }

    /**
     * 声明绑定关系
     * */
    @Bean
    public Binding getTopicBinding(){
        return BindingBuilder.bind(getQueue()).to(getTopicExchange()).with("#.topic.#");
    }
}
