package com.simland.backstage.task;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Deprecated
public class MessageQueueThread extends Thread {

	public static final Log logger = LogFactory.getLog(MessageQueue.class);

	private RedisTemplate<String, String> redisTemplate;

	public MessageQueueThread(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	// 消息内存队列
	public static final ConcurrentLinkedQueue<String> logQueue = new ConcurrentLinkedQueue<String>();

	@Override
	public void run() {
		while (true) {
			String msgObj = redisTemplate.opsForList().rightPop("messageQueue");
			if (msgObj == null || msgObj.length() == 0) {
				try {
					Thread.sleep(5000);
					continue;
				} catch (InterruptedException e) {
					logger.error("MessageQueueThread error:" + e.getMessage());
					e.printStackTrace();
					break;
				}
			}
			logQueue.add(msgObj);
		}
	}
}
