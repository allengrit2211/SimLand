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
	
<!-- 我的收藏商品页面 -->
	<!-- myCollectCommodityPage start-->
	<div data-role="page" id="myCollectCommodityPage">
		<div data-role="header" class="header_1">
			<a  data-transition="none" href="#" data-role="button" data-rel="back"
				class="back" data-icon="arrow-l">&nbsp;</a>
			<h1>
				<div class="tags">
					<a  class="left" data-transition="none" href="${pageContext.request.contextPath}/user/collectShopShow" data-role="button">店铺</a><a  class="right on" data-transition="none" href="${pageContext.request.contextPath}/user/collectCommodityShow" data-role="button">商品</a>
				</div>
			</h1>
			<a  data-transition="none" href="#" data-role="button" class="back">筛选</a>
		</div>
		<!-- /header -->
		<div data-role="content">
		
			<div id="wrapper" class="top">
				<div id="scroller">
		
					<div class="commodity_list">
					
						<c:forEach var="item" items="${list}">
							<div class='commodityBox'>
								<div class='img'>
									<a  href='${pageContext.request.contextPath}/commodity/show?id=${item.id}' class='ui-link a1'><img width='61' height='61' alt='' src='${pageContext.request.contextPath}/${item.commodity.img}'></a>
								</div>
								<div class='info'>
									<p class='p1'><a  href='${pageContext.request.contextPath}/commodity/show?id=${item.id}' class='ui-link'>${item.commodity.name}</a></p>
									<p class='p2'><span class='s1'>￥${item.commodity.marketPrice}</span><span>成交:</span><span class='s3'>562</span></p>
								</div>
							</div>	
						</c:forEach>
					
					</div>
					
					<p class="p_last">已经没有更多数据了</p>
					
					<div class="bottom_height"></div>
					
				</div>	
			</div>		
		</div>
		<div data-role="footer" data-tap-toggle="false" data-position="fixed" data-theme="d">
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
	<!-- myCollectCommodityPage end-->

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mobile-1.4.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/iscroll/iscroll.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/base.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/app.js"></script>

</body>
</html>