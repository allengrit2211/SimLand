<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stag" uri="http://simland-tags.sf.net"  %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
			<div id="right_font"><img src="${pageContext.request.contextPath}/images/main_14.gif"/> 您现在所在的位置：商品管理 → <span class="bfont">待下单</span></div>
			
			<div id="msg">
				&nbsp;&nbsp;&nbsp;&nbsp;${msg}
			</div>
			
			<div id="right_content">
				
				<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th width="50">编号</th>
						<th width="50">用户</th>
						<th width="50">创建时间</th>
						<th>操作</th>
					</tr>

					<c:if test="${fn:length(pageView.records)==0}">
					<tr>
						<td colspan="4">无数据</td>
					</tr>
					</c:if>
					<c:forEach items="${pageView.records}" var="item">
						<tr>
							<td width="20">${item.id}</td>
							<td width="220">${item.username}</td>
							<td width="120">2015-08-10</td>
							<td width="30"><a href="${pageContext.request.contextPath}/shop/createOrderShow?id=${item.id}">[下单]</a></td>
						</tr>
					</c:forEach>
				</table>

				<stag:PageViewTag baseUrl="${pageContext.request.contextPath}/shop/waitOrderlist" pageView="${pageView}" />

			</div>
			
		</div>
	</div>


<body>
</body>
</html>

