<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"  />
<title></title>
</head>
	
	<c:import url="top.jsp"/>
	<div style="clear:both;"></div>
	<div id="content">
		<c:import url="left.jsp"/>
		<div id="right_box">
			<c:import url="right_top.jsp"/>
			<div id="right_content">
				<div style="margin:10px 0 0 20px;font-size:14px;">
			</div>
			
		</div>
	</div>


<body>
</body>
</html>
