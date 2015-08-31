package com.simland.core.module.chat.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.chat.entity.Message;

public interface MessageMapper{
	
	public Integer insertMessage(Message message);  	
	
	public Integer updateMessage(Message message);
	
	public Integer deleteMessage(Integer id);

	public Message getMessage(Map param);
	
	public List getMessageList(Map param);

	public Integer getMessageCount(Map param);
	
	public List getSplitMessageList(Map param);

}
