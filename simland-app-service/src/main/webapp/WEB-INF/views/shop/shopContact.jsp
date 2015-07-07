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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

</head>

<body>
	
<!-- 店铺联系方式页面 -->
	<!-- contact start  -->
	<div data-role="page" id="contactPage" class="shop">
		<div data-role="content">
		
		
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
			
			
			<div class="shopTag">
				<div data-role="controlgroup"
					data-type="horizontal" data-mini="true">
					<a  data-transition="none" href="${pageContext.request.contextPath}/shop/info?id=${shop.id}" data-role="button">基本信息</a> <a 
						data-transition="none" href="#" data-role="button">认证信息</a> <a 
						data-transition="none" href="${pageContext.request.contextPath}/shop/contact?id=${shop.id}" data-role="button" class="on">联系方式</a>
				</div>
			</div>
			<div class="info">

				<div class="ui-grid-a contactBox">
					<div class="ui-block-a t_line">
						<span>联系人：</span>
					</div>
					<div class="ui-block-b t_line">
						<span class="s1"></span>
					</div>
					<div class="ui-block-a">
						<span>电话：</span>
					</div>
					<div class="ui-block-b">
						<span class="s2"></span>
					</div>
				</div>

				<a  class="btn1" data-transition="none" href="#" data-role="button">保存联系方式倒通讯录</a>

				<div class="contactMap">
					<div id="contactAllmap"></div>
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
	<!-- contact end  -->

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mobile-1.4.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/base.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/app.js"></script>

</body>
</html>