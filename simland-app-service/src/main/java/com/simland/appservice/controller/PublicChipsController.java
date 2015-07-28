package com.simland.appservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**     
 * @Title: PublicChipsController.java   
 * @Package com.simland.appservice.controller   
 * @Description: 众筹控制类 
 * @author Gavin     
 * @date 2015年7月21日 下午7:38:51   
 * @version V1.0     
 */
@Controller
public class PublicChipsController {

	@RequestMapping(value = "/publicChips/publicChipsView")
	public String publicChipsView(){
		return "publicChips/publicChipsView";
	}
	
}
