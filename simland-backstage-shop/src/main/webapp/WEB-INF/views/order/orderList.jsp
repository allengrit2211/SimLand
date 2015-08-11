<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stag" uri="http://simland-tags.sf.net"  %>  

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>

<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"  />
<link type="text/css" href="${pageContext.request.contextPath}/css/pageView.css" rel="stylesheet"  />
<title></title>
</head>
	
	<c:import url="../top.jsp"/>
	<div style="clear:both;"></div>
	<div id="content">
		<c:import url="../left.jsp"/>
		<div id="right_box">
			<c:import url="../right_top.jsp"/>
			<div id="right_font"><img src="${pageContext.request.contextPath}/images/main_14.gif"/> 您现在所在的位置：订单管理 → <span class="bfont">订单列表 </span></div>
			
			<div id="msg">
				&nbsp;&nbsp;&nbsp;&nbsp;${msg}
			</div>
			
			<div id="right_content">
			
				<form action="${pageContext.request.contextPath}/onlineandloss/distributedMap" method="get" >
				<table width="75%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="50"></td>
						<td> 
						</td>
					</tr>
				</table>
				</form>
			
				<br/>
				
				<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th width="50">订单号</th>
						<th width="50">用户名</th>
						<th width="50">收货人</th>
						<th width="50">手机号</th>
						<th width="50">地址</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageView.records}" var="item">
						<tr>
							<td width="20">${item.id}</td>
							<td width="20">${item.user.uname}</td>
							<td width="20">${item.receiverName}</td>
							<td width="20">${item.receiverPhone}</td>
							<td width="20">${item.receiverAddress}</td>
							<td width="30"><a target="_blank" href="${pageContext.request.contextPath}/shop/orderDetails?id=${item.id}">[明细]</a>
								<a href="javascript:;">[修改]</a>
							</td>
						</tr>
					</c:forEach>
				</table>

				<stag:PageViewTag baseUrl="${pageContext.request.contextPath}/shop/orderList" pageView="${pageView}" />

			</div>
			
		</div>
	</div>


<body>
</body>
</html>

