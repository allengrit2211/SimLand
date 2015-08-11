<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


			
<div id="left">
  
	   <div class="menuShow">
	       <div class="left_menu"><strong class="strong_1">基本信息</strong></div>
		   <div class="left_tree">
				<div class="tree_text">
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/customer/bulletinList">店铺信息</a></li>
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
				</div>
		   </div>
		   <div id="tree_down"></div>
	   </div>
	   <div class="menuShow">
	       <div class="left_menu"><strong class="strong_1">订单管理</strong></div>
		   <div class="left_tree">
				<div class="tree_text">
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/shop/waitOrderList">待下单</a></li>
				     <li class="tree_li"> <img src="${pageContext.request.contextPath}/images/list_img.gif" /> <a class="list_img" href="${pageContext.request.contextPath}/shop/orderList">订单列表</a></li>	
				</div>
		   </div>
		   <div id="tree_down"></div>
	   </div>			
</div>
