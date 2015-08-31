package com.simland.backstage.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Deprecated
public class MessageQueue {

	public static final Log logger = LogFactory.getLog(MessageQueue.class);

	public static final int POOL_LENTH = 2;

	public static final ExecutorService pool = Executors.newFixedThreadPool(POOL_LENTH);// 初始化线程

	// @Autowired
	private RedisTemplate<String, String> redisTemplate;

	// @PostConstruct
	public void init() {
		logger.info("MessageQueue start");
		for (int i = 0; i < POOL_LENTH; i++) {
			MessageQueueThread mqt = new MessageQueueThread(redisTemplate);
			pool.execute(mqt);
		}
		logger.info("MessageQueue end");
	}

	// @PreDestroy
	public void dostory() {

	}

}
