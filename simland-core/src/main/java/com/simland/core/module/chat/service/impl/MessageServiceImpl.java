package com.simland.core.module.chat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.chat.mapper.MessageMapper;
import com.simland.core.module.chat.entity.Message;
import com.simland.core.module.chat.service.IMessageService;

@Transactional(readOnly=true)
@Service("messageService")
public class MessageServiceImpl implements IMessageService{

	@Autowired
	private MessageMapper messageMapper;
	
	@Transactional(readOnly=false)
	public Integer insertMessage(Message message) {
		return messageMapper.insertMessage(message);
	}

	@Transactional(readOnly=false)
	public Integer updateMessage(Message message) {
		return messageMapper.updateMessage(message);
	}
	
	@Transactional(readOnly=false)
	public Integer deleteMessage(Integer id) {
		return messageMapper.deleteMessage(id);
	}
	
	public Message getMessage(Map param) {
		return (Message)messageMapper.getMessage(param);
	}
	
	public List<Message> getMessageList(Map param) {
		return messageMapper.getMessageList(param);
	}
	
	public Integer getMessageCount(Map param) {
		return (Integer)messageMapper.getMessageCount(param);
	}
	
	public List<Message> getSplitMessageList(Map param) {
		return messageMapper.getSplitMessageList(param);
	}
	
}
