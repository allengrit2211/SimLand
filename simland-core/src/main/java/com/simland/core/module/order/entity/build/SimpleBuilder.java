package com.simland.core.module.order.entity.build;

import java.util.ArrayList;
import java.util.List;

import com.simland.core.module.order.entity.Order;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.user.entity.User;

public class SimpleBuilder implements Builder {

	private Order order = new Order();

	private User user;// 用户信息

	private Commodity commodity;// 商品信息

	public SimpleBuilder(User user, int buyNum, Commodity commodity) {
		this.user = user;
		this.commodity = commodity;
		order.setBuyNum(buyNum);
	}

	@Override
	public void buildUserInfo() {
		order.setUser(user);
	}

	@Override
	public void buildCommodity() {
		List<Commodity> list = new ArrayList<Commodity>();
		list.add(commodity);
		order.setCommodityList(list);
	}

	@Override
	public Order getResult() {
		return order;
	}

}
