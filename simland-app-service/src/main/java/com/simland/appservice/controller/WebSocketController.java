package com.simland.appservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simland.appservice.controller.security.SessionManager;
import com.simland.core.base.Utils;
import com.simland.core.module.chat.entity.ChatMessage;
import com.simland.core.module.chat.entity.ChatResult;
import com.simland.core.module.user.entity.User;

@Controller
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate template;

	@RequestMapping("/buy/chatMessage")
	public String chatMessage(HttpServletRequest request, Model model) {
		User user = SessionManager.getUser();
		model.addAttribute("sid", request.getParameter("sid"));
		model.addAttribute("uid", user.getId());
		return "buy/chatMessage";
	}

	@MessageMapping("/chat/sendMsg")
	// @SendTo("/topic/showResult")
	public void showResult(ChatMessage chatMessage) throws Exception {
		// Thread.sleep(2000);
		chatMessage.setTime(Utils.getToday("yyyy-MM-dd HH:mm:ss"));

		// 找到需要发送的地址
		String dest = "/topic/showResult/" + chatMessage.getSid() + "/" + chatMessage.getUid();
		// 发送用户的聊天记录
		this.template.convertAndSend(dest, chatMessage);

	}

	/**
	 * 初始化，初始化聊天记录
	 *
	 * @param coordinationId
	 *            协同空间的id
	 */
	@SubscribeMapping("/chat/init/{shopId}/${userId}")
	public ChatResult init(@DestinationVariable("shopId") Integer shopId, @DestinationVariable("userId") Integer userId) {

		ChatResult chatResult = new ChatResult();

		return chatResult;
	}
}