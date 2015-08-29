package com.simland.core.module.chat;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

//@Configuration
//@EnableWebSocketMessageBroker
/***
 * Spring webSocket 两个配置方式，@see spring-servlet.xml
 * @author Administrator
 *
 */
@Deprecated
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 添加这个Endpoint，这样在网页中就可以通过websocket连接上服务了
		registry.addEndpoint("/coordination").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		System.out.println("服务器启动成功");
		// 这里设置的simple broker是指可以订阅的地址，也就是服务器可以发送的地址
		/**
		 * userChat 用于用户聊天
		 */
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration channelRegistration) {
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration channelRegistration) {
	}

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		// TODO Auto-generated method stub
		return true;
	}
}