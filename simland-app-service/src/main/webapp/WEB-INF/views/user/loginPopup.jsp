<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="loginPopup" data-role="popup"  data-tolerance="30,15">
	<div class="loginPopupBox">
		<a href="#" data-rel="back" data-role="button" data-theme="a" data-transition="slideup" data-icon="delete" data-iconpos="notext" class="ui-btn-right"> Close</a>
		<form action="${pageContext.request.contextPath}/logindo" method="post" id="loginPage_form">
			<input type="text" name="uname" id="uname" value="zhuoer" placeholder="请输入手机号码">
			<input type="password" data-mini="true" name="upw" id="upw" value="123123" placeholder="输入密码"> 
			<input type="hidden" name="toUrl" id="toUrl" value="/user/userCenter">
			<input type="button" data-mini="true" value="登录" id="loginAjaxBtn"/>
		</form>
	</div>
</div>