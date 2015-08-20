<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


			
<div id="left">
  
	   <div class="menuShow">
	       <div class="left_menu"><strong class="strong_1">店铺管理</strong></div>
		   <div class="left_tree">
				<div class="tree_text">
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/shop/editShopInfo">我的认证</a></li>
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/customer/bulletinList">消息推送</a></li>
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/shop/bannerList">店铺横幅</a></li>
				</div>
		   </div>
		   <div id="tree_down"></div>
	   </div>
	   
	   <div class="menuShow">
	       <div class="left_menu"><strong class="strong_1">商品管理</strong></div>
		   <div class="left_tree">
				<div class="tree_text">
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/commodity/addShow">新增商品</a></li>
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/commodity/list">商品列表</a></li>	
					<li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/commodity/issueCommodityList">发布商品</a></li>
				</div>
		   </div>
		   <div id="tree_down"></div>
	   </div>
	   
	   <div class="menuShow">
	       <div class="left_menu"><strong class="strong_1">活动管理</strong></div>
		   <div class="left_tree">
				<div class="tree_text">
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="#">团购</a></li>
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="#">清仓</a></li>
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="#">拍卖</a></li>
				</div>
		   </div>
		   <div id="tree_down"></div>
	   </div>
	   
	   <div class="menuShow">
	       <div class="left_menu"><strong class="strong_1">客户管理</strong></div>
		   <div class="left_tree">
				<div class="tree_text">
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="#">所有会员</a></li>
				</div>
		   </div>
		   <div id="tree_down"></div>
	   </div>
	   
	   <div class="menuShow">
	       <div class="left_menu"><strong class="strong_1">订单管理</strong></div>
		   <div class="left_tree">
				<div class="tree_text">
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/shop/waitOrderList">待下单</a></li>
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/shop/orderList">已卖出商品</a></li>	
				</div>
		   </div>
		   <div id="tree_down"></div>
	   </div>
	   <div class="menuShow">
	       <div class="left_menu"><strong class="strong_1">权限管理</strong></div>
		   <div class="left_tree">
				<div class="tree_text">
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/purview/powerList"> 权限管理 </a></li>
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/purview/roleList"> 角色管理 </a></li>
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/purview/shopUserList"> 用户管理 </a></li>
				</div>
		   </div>
		   <div id="tree_down"></div>
	   </div>	   		
</div>
