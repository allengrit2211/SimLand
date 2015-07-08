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

</head>

<body>
	
	<!-- scat1Page 购物车页面 start  -->
	<div data-role="page" id="#cartPage">
		<div data-role="header" class="header_1">
			<a  data-transition="none" href="#" data-role="button" data-rel="back"
				class="back" data-icon="arrow-l">&nbsp;</a>
			<h1>购物车</h1>
			<a  data-transition="none" href="${pageContext.request.contextPath}/order/confirmOrder" data-role="button"
				class="back">结算</a>
		</div>
		<!-- /header -->
		<div data-role="content">
			<div id="wrapper" class="top">
				<div id="scroller">
					<div class="cart">
						<div id="preferential" data-role="popup" data-theme="a">
							<div style="width:300px;overflow:hidden;">
								<h3>优惠说明</h3>
								<hr class="line">
								<p>单笔订单满10000元，返回样品费</p>
								<p>单笔订单满10000元，返回样品费</p>
								<p>单笔订单满10000元，返回样品费</p>
							</div>
						</div>
		
		
						<div class="ui-grid-b plist">
							<div class="ui-block-a p_0_0">
								<input type="checkbox" name="carCheck" data-cacheval="false">
							</div>
							<div class="ui-block-b p_0_1">
								<span>深圳市大本钟贸易有限...</span> |<a  data-transition="none"
									href="#preferential" data-rel="popup" data-position-to="window">优惠说明</a>
							</div>
							<div class="ui-block-c p_0_2"></div>
		
							<div class="ui-block-a p_1_0"></div>
							<div class="ui-block-b p_1_1">
								<img width="53" alt="" src="${pageContext.request.contextPath}/images/commodity/c_4.jpg">
							</div>
							<div class="ui-block-c p_1_2">
								<span>【十年资质】裸钻提供商 天然南非钻石 深圳水贝珠宝 一手...</span> <br> <span>￥29890.00</span>
							</div>
							<div class="ui-block-a p_2_0">&nbsp;</div>
							<div class="ui-block-b p_2_1">单价:￥29,890.00/克拉</div>
							<div class="ui-block-c p_2_2">
								￥29,890.00<input type="text">
							</div>
		
		
						</div>
		
						<div class="ui-grid-b plist">
							<div class="ui-block-a p_0_0">
								<input type="checkbox" name="carCheck" data-cacheval="false">
							</div>
							<div class="ui-block-b p_0_1">
								<span>深圳市大本钟贸易有限...</span> |<a  data-transition="none"
									href="#preferential" data-rel="popup" data-position-to="window">优惠说明</a>
							</div>
							<div class="ui-block-c p_0_2"></div>
		
							<div class="ui-block-a p_1_0"></div>
							<div class="ui-block-b p_1_1">
								<img width="53" alt="" src="${pageContext.request.contextPath}/images/commodity/c_4.jpg">
							</div>
							<div class="ui-block-c p_1_2">
								<span>【十年资质】裸钻提供商 天然南非钻石 深圳水贝珠宝 一手...</span> <br> <span>￥29890.00</span>
							</div>
							<div class="ui-block-a p_2_0">&nbsp;</div>
							<div class="ui-block-b p_2_1">单价:￥29,890.00/克拉</div>
							<div class="ui-block-c p_2_2">
								￥29,890.00<input type="text">
							</div>
						</div>
		
		
					</div>

				</div>
			</div>

		</div>
		<div data-role="footer" data-tap-toggle="false" data-position="fixed"
			data-theme="d" style="border-top:none;">

			<div class="cartMenu">
				<div class="left">
					<input type="checkbox" id="carCheckAll"/>
					<span>全选</span>
				</div>
				
				<div class="right">
					<a  data-transition="none" href="#" data-role="button">移致收藏</a> 
					<a  data-transition="none" href="#" data-role="button">删除</a>
				</div>
				<div class="clear"></div>
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
	<!-- scat1Page end  -->


	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mobile-1.4.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/iscroll/iscroll.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/base.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/app.js"></script>

</body>
</html>