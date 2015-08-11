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
			<div id="right_font"><img src="${pageContext.request.contextPath}/images/main_14.gif"/> 您现在所在的位置：商品管理 → <span class="bfont">商品列表 </span></div>
			
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
						<th width="50">商品ID</th>
						<th width="50">商品名称</th>
						<th width="50">商品图片</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageView.records}" var="item">
						<tr>
							<td width="20">${item.id}</td>
							<td width="220">${item.name}</td>
							<td width="120">${pageContext.request.contextPath}${item.img}</td>
							<td width="30"><a href="javascript:;">[编辑]</a></td>
						</tr>
					</c:forEach>
				</table>

				<stag:PageViewTag baseUrl="${pageContext.request.contextPath}/commodity/list" pageView="${pageView}" />

			</div>
			
		</div>
	</div>


<body>
</body>
</html>

