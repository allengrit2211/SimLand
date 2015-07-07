<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<meta name="description" content="A single-page template generated by MyEclipse Mobile Tools" />
	<title>Single-Page Application</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.mobile-1.4.3.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/iscroll/scrollbar.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<style type="text/css">

	</style>
</head>

<body>
	
	<!-- 商家页面 新品页面 -->
	<!-- shop1Page start  -->
	<div data-role="page" id="shop1Page" class="shop">
		<div data-role="content" id="content">
			
			<div id="wrapper">
				<div id="scroller">

					<div class="shop_top">
						<div class="b_logo">
							<img alt="" src="${pageContext.request.contextPath}/${shop.clogo}">
						</div>
						<div class="b_info">
							<h3>${shop.cname}</h3>
							<span class="star star${shop.score}"></span>
						</div>
						<div class="b_btn">
							<a  href="#"></a>
						</div>
					</div>
					<div class="slides_container">
						<div class="slides2">
							<img src="${pageContext.request.contextPath}/images/activity/shop1_r6_c1.jpg"> 
							<img src="${pageContext.request.contextPath}/images/activity/shop1_r6_c1.jpg"> 
							<img src="${pageContext.request.contextPath}/images/activity/shop1_r6_c1.jpg">
						</div>
					</div>
		
					<div class="shopTag">
						<div data-role="controlgroup" data-type="horizontal"
							data-mini="true">
							<a  i="1" class="on" data-transition="none" href="#shop1Page" data-role="button">新品</a>
							<a  i="2" data-transition="none" href="#shop2Page" data-role="button">特价</a>
							<a  i="3" data-transition="none" href="#shop3Page" data-role="button">产品</a>
							<a  i="4" data-transition="none" href="#Vip1Page" data-role="button" class="last">VIP</a>
						</div>
					</div>
		
					<div class="commodityList tags" id="page1">
						<c:forEach var="item" items="${list1}">
							<div class='commodityBox'>
								<a  data-transition='none' href='${pageContext.request.contextPath}/commodity/show?id=${item.id}'>
								<span class='s3' cid='${item.id}'>${item.collectNum}</span>
								<img width='126' alt='' src='${pageContext.request.contextPath}/${item.img}'>
								<span class='s4'>${item.name}</span>
								</a>
								<div class='price'>
									<span class='s1'>￥</span>
									<span class='s2'>${item.marketPrice}</span>
								</div>
							</div>
						</c:forEach>
					</div>
		
					<div class="commodityList tags none" id="page2">
						<c:forEach var="item" items="${list2}">
							<div class='commodityBox'>
								<a  data-transition='none' href='${pageContext.request.contextPath}/commodity/show?id=${item.id}'>
								<span class='s3' cid='${item.id}'>${item.collectNum}</span>
								<img width='126' alt='' src='${pageContext.request.contextPath}/${item.img}'>
								<span class='s4'>${item.name}</span>
								</a>
								<div class='price'>
									<span class='s1'>￥</span>
									<span class='s2'>${item.marketPrice}</span>
								</div>
							</div>
						</c:forEach>
					</div>
		
					<div class="commodityList tags none" id="page3">
						<c:forEach var="item" items="${list3}">
							<div class='commodityBox'>
								<a  data-transition='none' href='${pageContext.request.contextPath}/commodity/show?id=${item.id}'>
								<span class='s3' cid='${item.id}'>${item.collectNum}</span>
								<img width='126' alt='' src='${pageContext.request.contextPath}/${item.img}'>
								<span class='s4'>${item.name}</span>
								</a>
								<div class='price'>
									<span class='s1'>￥</span>
									<span class='s2'>${item.marketPrice}</span>
								</div>
							</div>
						</c:forEach>
					</div>
					
					<div class="vipBox tags none" id="page4">
						<div>
							<label class="l1" for="vippwd">密钥:</label> 
							<input class="t1" type="text" name="vippwd" id="vippwd"> 
						</div>
						
						<p class="p1">	
							<span>VIP客户请联系客服获取本店VIP专区动态密钥 </span>
							<span>友情提示:仅货的动态密钥才可浏览VIP区产品</span>
						</p>
		
						<div class="ui-grid-a vipboxBtn">
							<div class="ui-block-a">
								<a  data-transition="none" href="#" class="a1">申请成为VIP客户</a>
							</div>
							<div class="ui-block-b">
								<a  data-transition="none" href="#" class="a2">联系客服</a>
							</div>
						</div>
					</div>
		
					<div class="bottom_height"></div>
					
				</div>
			</div>
		</div>
		<div data-role="footer" data-tap-toggle="false" data-position="fixed"
			data-theme="d">
			
			<div class="b_bottom_menu">
				<ul>
					<li><a  href="#" data-rel="back">返回</a></li>
					<li><a  href="${pageContext.request.contextPath}/shop/info?id=${shop.id}">公司简介</a></li>
					<li><a  href="#anylink">买家评论</a></li>
					<li><a  href="${pageContext.request.contextPath}/shop/showShop?id=${shop.id}">店铺首页</a></li>
				</ul>
			</div>
			
			<div data-role="navbar" class="myfooter bgEDEDED">
				<ul>
					<li><a  data-transition="none" href="${pageContext.request.contextPath}/main" class="ui-icon-index_1 ui-btn-active1">首页</a></li>
					<li><a  data-transition="none" href="${pageContext.request.contextPath}/map/map1Page" class="ui-icon-map_1">地图</a></li>
					<li><a  data-transition="none" href="${pageContext.request.contextPath}/buy/cart" class="ui-icon-purchase_1">进货单</a></li>
					<li><a  data-transition="none" href="${pageContext.request.contextPath}/user/userCenter" class="ui-icon-my_1">我的</a></li>
				</ul>
			</div>
			
		</div>
	</div>
	<!-- shop1Page end  -->

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mobile-1.4.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/iscroll/iscroll.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/base.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/app.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/shop.js"></script>
</body>
</html>