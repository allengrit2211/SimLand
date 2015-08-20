<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stag" uri="http://simland-tags.sf.net"  %>  

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"  />
<link type="text/css" href="${pageContext.request.contextPath}/css/pageView.css" rel="stylesheet"  />
<title></title>
<script type="text/javascript">
$(function(){

	var contextPath = '${pageContext.request.contextPath}';

	$("input[name='file']").bind("change",function(){
		upCommImage(this);
	});

	function upCommImage(obj){
	
		var fileId = $(obj).attr("id");
		$.ajaxFileUpload({
             url: contextPath+'/commodity/uploadImage?ajax=ajax', //用于文件上传的服务器端请求地址
             type: 'post',
             cache : false,
             data: { }, //此参数非常严谨，写错一个引号都不行
             secureuri: false, //一般设置为false
             fileElementId: fileId, //文件上传空间的id属性  <input type="file" id="file" name="file" />
             dataType: 'json', //返回值类型 一般设置为json
             success: function (data, status)  //服务器成功响应处理函数
             {
             	if(data){
             		var data1= eval(data);
	                 if(data1.code==1){
	                 	//alert(data1.msg);
	                 	$("#"+fileId).prev().attr("src",contextPath+data.toUrl);
	                 	$("#"+fileId).prev().prev().val(data.toUrl);
	                 }
             	}else{
             		alert("上传错误");
             	}
             },
             error: function (data, status, e)//服务器响应失败处理函数
             {
                 alert(e);
             }
		})
	
	    $("input[name='file']").bind("change",function(e){
			upCommImage(this);
		});
	}


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
				
				
				<form action="${pageContext.request.contextPath}/shop/bannerEdit" method="post" id="shopForm">
				<input type="hidden" name="method" value="${method}">
				<table class="gridtable" width="50%" border="0" cellspacing="0" cellpadding="0">						
					<c:forEach begin="0" end="2" var="item">
						<tr>
							<th colspan="2"> 
								 横幅${item+1}<input type="hidden" name="ids" value="${list[item].id}">
							</th>
						</tr>
						<tr>
							<td>
							<input type="hidden" name="picUrl">
							<img width="30" height="30" alt="" src="/simland-app-service/${list[item].picUrl}">
							<input style="vertical-align:top;margin-top:5px;" type="file" id="picUrl_${item}" name="file"/></td>
							<td>图片链接<input type="text" name="linkUrl" value="${list[item].linkUrl}"></td>
						</tr>
					</c:forEach>
					
					<tr>
						<td colspan="2">
							<input type="submit" value="保存" id="saveBtn">
						</td>
					</tr>
				</table>
				</form>	
			</div>
			
		</div>
	</div>

</body>
</html>

