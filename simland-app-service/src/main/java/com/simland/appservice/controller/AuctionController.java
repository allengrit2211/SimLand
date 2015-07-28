package com.simland.appservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: AuctionController.java
 * @Package com.simland.appservice.controller
 * @Description: 拍卖控制类
 * @author Gavin
 * @date 2015年7月21日 下午5:54:42
 * @version V1.0
 */
@Controller
public class AuctionController {

	@RequestMapping(value = "/auction/auctionView")
	public String auctionView() {
		return "auction/auctionView";
	}

}
