package com.simland.core.module.chat.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.chat.entity.Message;

@SuppressWarnings("unchecked")
public interface IMessageService {
	
	public Integer insertMessage(Message message);

	public Integer updateMessage(Message message);

	public Integer deleteMessage(Integer id);
	
	public Message getMessage(Map param);
	
	public List<Message> getMessageList(Map param);

	public Integer getMessageCount(Map param);
	
	public List<Message> getSplitMessageList(Map param);
	
}
