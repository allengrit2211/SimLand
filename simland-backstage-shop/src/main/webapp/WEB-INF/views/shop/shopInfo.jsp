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
<script type="text/javascript">

$(function(){
	$("#saveBtn").bind("click",function(){
		$(this).attr("disabled","disabled");
		var tmp = this;
		setTimeout(function(){
			$(tmp).removeAttr("disabled");
		},1000);
		$("#shopForm").submit();
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
			<div id="right_font"><img src="${pageContext.request.contextPath}/images/main_14.gif"/> 您现在所在的位置：首页 → 管选管理 → <span class="bfont"> 添加角色 </span></div>
			
			<div id="right_content">
			
				<div id="msg">
					&nbsp;&nbsp;&nbsp;&nbsp;${msg}
				</div>
			
				<form action="${pageContext.request.contextPath}/shop/editShopInfo" method="post" id="shopForm">
				<input type="hidden" name="id" value="${shop.id}">
				<input type="hidden" name="method" value="${method}">
				<table class="gridtable" width="50%" border="0" cellspacing="0" cellpadding="0">						
					<tr>
						<th> 
							 店铺名称
						</th>
						<td>
							<input name="name" type="text" value="${shop.name}">
						</td>
					</tr>
					<tr>
						<th> 
							 状态
						</th>
						<td>
							<c:if test="${shop.status==0}">待审核</c:if>
							<c:if test="${shop.status==1}">审核通过</c:if>
							
						</td>
					</tr>
					<tr>
						<th> 
							 公司名称
						</th>
						<td>
							<input name="cname" type="text" value="${shop.cname}">
						</td>
					</tr>
					<tr>
						<th> 
							 公司地址
						</th>
						<td>
							<input name="caddress" type="text" value="${shop.caddress}">
						</td>
					</tr>
					<tr>
						<th> 
							 经营模式
						</th>
						<td>
							<input name="bmodel" type="text" value="${shop.bmodel}">
						</td>
					</tr>
					<tr>
						<th> 
							 品牌名称
						</th>
						<td>
							<input name="brand" type="text" value="${shop.brand}">
						</td>
					</tr>
					<tr>
						<th> 
							 销售区域
						</th>
						<td>
							<input name="salesArea" type="text" value="${shop.salesArea}">
						</td>
					</tr>
					<tr>
						<th> 
							 客户群体
						</th>
						<td>
							<input name="clientrGroup" type="text" value="${shop.clientrGroup}">
						</td>
					</tr>
					<tr>
						<th> 
							 注册地
						</th>
						<td>
							<input name="regAddress" type="text" value="${shop.regAddress}">
						</td>
					</tr>
					<tr>
						<th> 
							 法人代表
						</th>
						<td>
							<input name="corporate" type="text" value="${shop.corporate}">
						</td>
					</tr>
					<tr>
						<th> 
							 员工人数
						</th>
						<td>
							<input name="people" type="text" value="${shop.people}">
						</td>
					</tr>
					<tr>
						<th> 
							 主营类别
						</th>
						<td>
							<input name="engage" type="text" value="${shop.engage}">
						</td>
					</tr>
					<tr>
						<th> 
							 手机号码
						</th>
						<td>
							<input name="phone" type="text" value="${shop.phone}">
						</td>
					</tr>
					<tr>
						<th> 
							 联系方式
						</th>
						<td>
							<input name="contact" type="text" value="${shop.contact}">
						</td>
					</tr>
					<tr>
						<th> 
							 联系人
						</th>
						<td>
							<input name="contactPeople" type="text" value="${shop.contactPeople}">
						</td>
					</tr>
					<tr>
						<th> 
							 注册邮箱
						</th>
						<td>
							<input name="email" type="text" value="${shop.email}">
						</td>
					</tr>
					<tr>
						<th> 
							 商家LOGO
						</th>
						<td>
							<img alt="" src="${pageContext.request.contextPath}/${shop.clogo}"><input name="clogo" type="file">
						</td>
					</tr>
					<tr>
						<th> 
							营业执照
						</th>
						<td>
							<img alt="" src="${pageContext.request.contextPath}/${shop.licenseImg}"><input name="licenseImg" type="file">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="保存" id="saveBtn">
						</td>
					</tr>
				</table>
				</form>	
			</div>	
		</div>
	</div>
</body>
</html>			
		
		