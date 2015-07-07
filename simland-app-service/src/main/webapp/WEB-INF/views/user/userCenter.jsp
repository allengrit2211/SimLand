<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description"
	content="A single-page template generated by MyEclipse Mobile Tools" />

<title>Single-Page Application</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.mobile-1.4.3.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

</head>

<body>
	
<!-- 用户中心 -->
	<!-- userCenter page start -->
	<div data-role="page" id="userCenterPage">
		<div data-role="content">
			<div id="wrapper">
				<div id="scroller">
					<div class="uInfo">
						<div class="uInfoBox">
							<div class="u_img">
								<img alt="" width="60" src="${pageContext.request.contextPath}/images/user/myindex_r2_c2.jpg">
							</div>
							<div class="u_info">
								<p class="u_p_1">我是大富翁</p>
								<p class="u_p_2">
									<img alt="" src="${pageContext.request.contextPath}/images/user/myindex_r3_c10.jpg">
								</p>
								<p class="u_p_3">
									<span>总订单:2笔</span> <span>总金额:12589元</span>
								</p>
							</div>
						</div>
		
						<div class="ui-grid-b collect">
							<div class="ui-block-a">
								<a  data-transition="none" href="${pageContext.request.contextPath}/user/collectCommodityShow"><span>8</span><span>收藏的宝贝</span></a>
							</div>
							<div class="ui-block-b">
								<a  data-transition="none" href="${pageContext.request.contextPath}/user/collectShopShow"><span>8</span><span>收藏的店铺</span></a>
							</div>
							<div class="ui-block-c">
								<a  data-transition="none" href="#"><span>7</span><span>浏览历史</span></a>
							</div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="ui-grid-d orderMenu" style="text-align:center;">
						<div class="ui-block-a">
							<a  data-transition="none" href="#"><img alt=""
								src="${pageContext.request.contextPath}/images/bg/order_menu_1.jpg"><span>待付款</span></a>
						</div>
						<div class="ui-block-b">
							<a  data-transition="none" href="#"><img alt=""
								src="${pageContext.request.contextPath}/images/bg/order_menu_2.jpg"><span>待发货</span></a>
						</div>
						<div class="ui-block-c">
							<a  data-transition="none" href="#"><img alt=""
								src="${pageContext.request.contextPath}/images/bg/order_menu_3.jpg"><span>待收货</span></a>
						</div>
						<div class="ui-block-d">
							<a  data-transition="none" href="${pageContext.request.contextPath}/commodity/assess"><img alt=""
								src="${pageContext.request.contextPath}/images/bg/order_menu_4.jpg"><span>待评价</span></a>
						</div>
						<div class="ui-block-e">
							<a  data-transition="none" href="#"><img alt=""
								src="${pageContext.request.contextPath}/images/bg/order_menu_5.jpg"><span>退货/售后</span></a>
						</div>
						<hr class="line">
						<div class="ucMenuList">
							<ul data-role="listview" data-inset="false" data-theme="d">
								<li><a  class="icon0" href="#">全部订单</a></li>
							</ul>
						</div>
					</div>
					<div class="ucMenuList" style="min-height:200px;">
						<ul data-role="listview" data-inset="false" data-theme="d">
							<li><a  class="icon1" href="#">我的账户与安全</a></li>
							<li><a  class="icon2" href="#">我的收货地址</a></li>
							<li><a  class="icon3" href="#">我的银行卡管理</a></li>
							<li><a  class="icon4" href="#">个人信息修改</a></li>
						</ul>
					</div>
				</div>
		
			</div>
		</div>
		
		<c:import url="../footer.jsp"/>
	</div>
	<!-- userCenter page end -->

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mobile-1.4.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/iscroll/iscroll.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/base.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/app.js"></script>

</body>
</html>