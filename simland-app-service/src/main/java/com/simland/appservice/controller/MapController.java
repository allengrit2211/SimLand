package com.simland.appservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {

	/***
	 * 一览图 
	 * @return
	 */
	@RequestMapping(value = "/map/map1Page")
	public String map1Page() {
		return "map/map1Page";
	}

	/***
	 * 水贝分区域后商家
	 * @return
	 */
	@RequestMapping(value = "/map/map2Page")
	public String map2Page() {
		return "map/map2Page";
	}
	
	/***
	 * 楼层列表(水牌)
	 * @return
	 */
	@RequestMapping(value = "/map/map3Page")
	public String map3Page() {
		return "map/map3Page";
	}
	
	@RequestMapping(value = "/map/map4Page")
	public String map4Page() {
		return "map/map4Page";
	}
	
}
