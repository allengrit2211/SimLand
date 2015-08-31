package com.simland.backstage.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@Deprecated
public class MessageQueueJobTask {

	public static final Log logger = LogFactory.getLog(MessageQueueJobTask.class);

	/**
	 * 业务逻辑处理
	 */
	public void execute() {

		logger.info("MessageQueueJobTask start");
		for (String msg : MessageQueueThread.logQueue) {
			System.out.println("msg=" + msg);
		}
		logger.info("MessageQueueJobTask end");

	}

}
