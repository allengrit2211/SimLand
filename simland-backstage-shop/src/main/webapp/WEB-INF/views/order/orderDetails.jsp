<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stag" uri="http://simland-tags.sf.net"  %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"  />
<title></title>


</head>
	
	<c:import url="../top.jsp"/>
	<div style="clear:both;"></div>
	<div id="content">
		<c:import url="../left.jsp"/>
		<div id="right_box">
			<c:import url="../right_top.jsp"/>
			<div id="right_font"><img src="${pageContext.request.contextPath}/images/main_14.gif"/> 您现在所在的位置：订单管理 → <span class="bfont">订单明细</span></div>
			
			<div id="msg">
				&nbsp;&nbsp;&nbsp;&nbsp;${msg}
			</div>
			
			<div id="right_content">
				<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr align="left">
						<td>&nbsp;&nbsp;&nbsp;订单号:${order.id}</td>
						<td><input type="button" value="打印"></td>
					</tr>
					<tr align="left">
						<td colspan="2">
							<div style="margin-top:15px;">
								&nbsp;&nbsp;&nbsp;总金额: 1000￥
							</div>
							<div style="margin-top:15px;">
								创建时间: ${order.createTime}
							</div>

						</td>
					</tr>				
					<tr>
						<td colspan="2">
							<div style="margin-top:15px;">
								&nbsp;&nbsp;&nbsp;用户名:${order.user.uname}
							</div>
							<div style="margin-top:15px;">
								&nbsp;&nbsp;&nbsp;收货人: ${order.receiverName}
							</div>
							<div style="margin-top:15px;">
								&nbsp;&nbsp;&nbsp;手机号: ${order.receiverPhone}
							</div>
							<div style="margin-top:15px;">
								详细地址: ${order.receiverName} ${order.receiverProvince}  ${order.receiverCity}  ${order.receiverCity}  ${order.receiverDistrict}  ${order.receiverAddress} 
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2"><span>商品列表 </span></td>
					</tr>
					<tr>
						<td colspan="2">
							<table align="center" class="gridtable" width="98%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th>商品名称</th>
								<th>价格</th>
								<th>属性1</th>
								<th>属性2</th>
								<th>数量</th>
							</tr>
							<c:forEach items="${order.orderItems}" var="item">
								<tr>
									<td>${item.cname}</td>
									<td>${item.cprice}</td>
									<td>${item.attr1Val}</td>
									<td>${item.attr2Val}</td>
									<td>${item.buyNum}</td>
								</tr>
							</c:forEach>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							总金额:10000 ￥
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<label style="display: inline-block;vertical-align:top;">留言:</label> 
							${order.remark}
						</td>
					</tr>
				</table>
			</div>
			
		</div>
	</div>

	


<body>
</body>
</html>

