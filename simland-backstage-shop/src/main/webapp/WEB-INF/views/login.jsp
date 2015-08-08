<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$("#loginBtn").bind("click",function(){
		
	
		/*
		if($("[name='userName']").val()==''){
			alert("用户名不能为空");
			$("[name='userName']").focus();
			return;
		}
		if($("[name='passWord']").val()==''){
			alert("密码不能为空");
			$("[name='passWord']").focus();
			return;
		}
		*/
		$("#loginForm").submit();
	});
});
</script>
</head>
<body>

    <div id="login">
	
	     <div id="top">
		      <div id="top_left"><img src="${pageContext.request.contextPath}/images/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 <form method="post" action="${pageContext.request.contextPath}/shop/logindo" id="loginForm">
		 <input type="hidden" name="method" value="">
		 <div id="center">
		      <div id="center_left"></div>

			  <div id="center_middle">
	      		<div id="msg">
					&nbsp;&nbsp;&nbsp;&nbsp;${msg}
				</div>
			       <div id="user">用 户
			         <input type="text" name="userName" />
			       </div>
				   <div id="password">密   码
				     <input type="password" name="passWord" />
				   </div>
				   <div id="btn"><a id="loginBtn" href="#">登录</a><a href="#" id="resetBtn">清空</a></div>
			  
			  </div>
			  <div id="center_right"></div>		 
		 </div>
		 </form>
		 <div id="down">
		      <div id="down_left">
			      <div id="inf">
                       <span class="inf_text">版本信息</span>
					   <span class="copyright">管理信息系统 2014 v1.0</span>
			      </div>
			  </div>
			  <div id="down_center"></div>		 
		 </div>

	</div>
</body>
</html>
