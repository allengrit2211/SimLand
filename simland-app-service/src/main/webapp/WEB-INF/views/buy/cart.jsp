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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery.mobile-1.4.3.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/iscroll/scrollbar.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />

</head>

<body>

	<!-- scat1Page 购物车页面 start  -->
	<div data-role="page" id="cartPage">
		<div data-role="header" class="header_1 bg41AC98">
			<a data-transition="slide" href="#" data-role="button" data-rel="back"
				class="back bg41AC98" data-icon="arrow-l">&nbsp;</a>
			<h1 class="colorFFF">购物车</h1>
			<a data-transition="none"
				href="${pageContext.request.contextPath}/order/confirmOrder"
				data-role="button" class="back bg41AC98 colorFFF">结算</a>
		</div>
		<!-- /header -->
		<div data-role="content">
			<div class="wrapper top">
				<div class="scroller">
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

						<div class="shopBox">
							<div class="title">
								<div class="check">
									<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
								</div>
								<a class="a1" href="#">深圳市大本钟贸易有限...</a> 
								<a class="a2" data-transition="none" href="#preferential" data-rel="popup" data-position-to="window">优惠说明</a>
							</div>

							<div class="commodity">
								<div class="check">
									<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
								</div>
								<div class="box">
									<div class="c_info">
										<div class="c_img">
											<img width="53" alt=""
												src="${pageContext.request.contextPath}/images/commodity/c_4.jpg">
										</div>
										<div class="c_infobox">
											<div class="c_title">【十年资质】裸钻提供商 天然南非钻石 深圳水贝珠宝 一手...</div>
											<div class="c_price">
												<p>
													<span>克数:</span><span class="s_1">100</span> <span>手寸:</span><span
														class="s_2">2x2</span>
												</p>
												<p>
													<span class="s_3">$123123 </span><span class="s_4">x 1</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="commodity">
								<div class="check">
									<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
								</div>
								<div class="box">
									<div class="c_info">
										<div class="c_img">
											<img width="53" alt=""
												src="${pageContext.request.contextPath}/images/commodity/c_4.jpg">
										</div>
										<div class="c_infobox">
											<div class="c_title">【十年资质】裸钻提供商 天然南非钻石 深圳水贝珠宝 一手...</div>
											<div class="c_price">
												<p>
													<span>克数:</span><span class="s_1">100</span> <span>手寸:</span><span
														class="s_2">2x2</span>
												</p>
												<p>
													<span class="s_3">$123123 </span><span class="s_4">x 1</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						
						
						<div class="shopBox">
							<div class="title">
								<div class="check">
									<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
								</div>
								<a class="a1" href="#">深圳市大本钟贸易有限...</a> 
								<a class="a2" data-transition="none" href="#preferential" data-rel="popup" data-position-to="window">优惠说明</a>
							</div>

							<div class="commodity">
								<div class="check">
									<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
								</div>
								<div class="box">
									<div class="c_info">
										<div class="c_img">
											<img width="53" alt=""
												src="${pageContext.request.contextPath}/images/commodity/c_4.jpg">
										</div>
										<div class="c_infobox">
											<div class="c_title">【十年资质】裸钻提供商 天然南非钻石 深圳水贝珠宝 一手...</div>
											<div class="c_price">
												<p>
													<span>克数:</span><span class="s_1">100</span> <span>手寸:</span><span
														class="s_2">2x2</span>
												</p>
												<p>
													<span class="s_3">$123123 </span><span class="s_4">x 1</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="commodity">
								<div class="check">
									<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
								</div>
								<div class="box">
									<div class="c_info">
										<div class="c_img">
											<img width="53" alt=""
												src="${pageContext.request.contextPath}/images/commodity/c_4.jpg">
										</div>
										<div class="c_infobox">
											<div class="c_title">【十年资质】裸钻提供商 天然南非钻石 深圳水贝珠宝 一手...</div>
											<div class="c_price">
												<p>
													<span>克数:</span><span class="s_1">100</span> <span>手寸:</span><span
														class="s_2">2x2</span>
												</p>
												<p>
													<span class="s_3">$123123 </span><span class="s_4">x 1</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						
						
						
						
					</div>
				</div>
			</div>

		</div>
		<div data-role="footer" data-tap-toggle="false" data-cacheval="false" data-position="fixed"
			data-theme="d" style="border-top:none;">

			<div class="cartMenu">
				<div class="left ">
					<input type="checkbox" id="carCheckAll" data-role="none"/> <span>全选</span>
				</div>

				<div class="right">
					<a data-transition="none" href="#" data-role="button">移致收藏</a> <a
						data-transition="none" href="#" data-role="button">删除</a>
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


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.mobile-1.4.3.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/iscroll/iscroll.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/simland/base.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/simland/app.js"></script>

</body>
</html>