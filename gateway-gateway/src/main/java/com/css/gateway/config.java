package com.css.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
@Configuration
public class config {
    /**
     * 配置一个id为route-name的路由规则，
     * 当访问地址http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        List<Routes> list = new ArrayList<>();
        //动态路由数据源从redis，es，mongo等地方获取，实现动态路由
        list.add(new Routes("path","/api/**","lb://PRODUCTOR-SERVICE"));
        list.stream().forEach((m)->{
            routes.route(m.getId(),r->r.path(m.getPath()).uri(m.getUri()));
        });
        return routes.build();
    }

    /**
     * redis 监听配置
     *
     * @param redisConnectionFactory redis 配置
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(redisConnectionFactory);
        listenerContainer.addMessageListener((message, bytes) -> {
            System.out.println(("接收到重新JVM 重新加载路由事件"));
            System.out.println(message.getBody());
            System.out.println(message.getChannel());
            System.out.println(bytes);
        }, new ChannelTopic("*"));
        return listenerContainer;
    }
}
