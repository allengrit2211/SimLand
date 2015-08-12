<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'permissions.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}

ul,li {
	list-style-type: none;
}

.dialogDiv {
	margin: 0 auto;
	text-align: center;
	width: 500px;
}

.dialogBox {
	margin: auto 0;
	margin-top: 160px;
	text-align: center;
	border: 1px solid #d2d2d2;
	background: #ffffff;
}

.dialogBox .header {
	background: #37596D;
	margin: 2px;
	height: 26px;
	text-align: left;
}

.dialogBox .header h3 {
	font-size: 14px;
	color: #ffffff;
	padding-left: 6px;
	padding-top: 4px;
}

.dialogBox .content {
	font-size: 22px;
	color: #6e6d6d;
	background: #ffffff;
	margin: auto 0;
	margin-top: 8px;
	min-height:120px;
	padding-top:45px;
}

.dialogBox .content ul {
	font-size: 12px;
	color: #6e6d6d;
	background: #ffffff;
	margin-bottom: 8px;
}

.dialogBox .content ul li {
	font-size: 12px;
	color: #6e6d6d;
	background: #ffffff;
	margin: 6px;
}
</style>
</head>

<body>
	<div class="dialogDiv">
		<div class="dialogBox">
			<div class="header">
				<h3>提示信息!</h3>
			</div>
			<div class="content">
				<div>
				您没有权限请联系管理员!
				</div>
				<div style="margin-top:30px;">
					<input type="button" value="重新登录" onclick="javascript:location.href='<%=basePath%>shop/login';" style="padding:5px 10px;"/>
					<input type="button" value="返 回" onclick="history.back()" style="padding:5px 10px;"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
