<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stag" uri="http://simland-tags.sf.net"  %>  

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="http://139.196.23.106:3000/socket.io/socket.io.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"  />
<link type="text/css" href="${pageContext.request.contextPath}/css/pageView.css" rel="stylesheet"  />
<title></title>
<script type="text/javascript">
$(function(){

		var sid = '${shop.id}';
		var uid = 1;

		var connection = io.connect('ws://139.196.23.106:3000', { 'reconnect': false }); 
		connection.on('connect', function (data) {  
			console.log("与服务器连接");
	    }); 
		//监听消息发送
		connection.on('message_'+sid+'_'+uid, function(re){
			$("#showMsg").append(re.message);
		});


		$("#sendMsgBtn").unbind().click(function(){
			var msg = $("#msgText").val();
			connection.emit('message_'+sid+'_'+uid, {message:msg}); //向服务器发送消息
		});

})

</script>
</head>
<body>
	<c:import url="../top.jsp"/>
	<div style="clear:both;"></div>
	<div id="content">
		<c:import url="../left.jsp"/>
		<div id="right_box">
			<c:import url="../right_top.jsp"/>
			<div id="right_font"><img src="${pageContext.request.contextPath}/images/main_14.gif"/> 您现在所在的位置：首页 → 商家管理→ <span class="bfont"> 商家横幅  </span></div>
			
			<div id="right_content">
			
				<div id="msg">
					&nbsp;&nbsp;&nbsp;&nbsp;${msg}
				</div>
				
				<div id="showMsg">
					
				</div>
				
				<input type="text" id="msgText"><input type="button" value="发送" id="sendMsgBtn">
				
			</div>
			
		</div>
	</div>

</body>
</html>

