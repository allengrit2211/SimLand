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
	
	<!-- 一览图 -->
	<!-- map1Page start  -->
	<div data-role="page" id="map1Page">
		<div data-role="header" class="header_1">
			<a  data-transition="none" href="#" data-role="button" data-rel="back"
				class="back" data-icon="arrow-l">&nbsp;</a>
			<h1>水贝一览图</h1>
		</div>
		<!-- /header -->
		<div data-role="content">
			<div class="map1">
				<a  data-transition="none" href="${pageContext.request.contextPath}/map/map2Page"><img alt=""
					src="${pageContext.request.contextPath}/images/tmp/map_1.jpg"></a>
				<h3>点击字母直接查看板块地图的大图</h3>
			</div>
		</div>
		<div data-role="footer" data-tap-toggle="false" data-position="fixed"
			data-theme="d">
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
	<!-- map1Page end  -->

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mobile-1.4.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/base.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/simland/app.js"></script>

</body>
</html>