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
			<div id="right_font"><img src="${pageContext.request.contextPath}/images/main_14.gif"/> 您现在所在的位置：首页 → 权限管理 → <span class="bfont"> 商铺用户管理  </span></div>
			
			<div id="right_content">
			
				<div id="msg">
					&nbsp;&nbsp;&nbsp;&nbsp;${msg}
				</div>
			
				<br>
				<form action="${pageContext.request.contextPath}/purview/shopUserAdd" method="post" >
				<input type="hidden" name="id" value="${id}">
				<table class="gridtable" width="30%" border="0" cellspacing="0" cellpadding="0">						
					<tr>
						<th colspan="2"> 
							后台用户新增与编辑
						</th>
					</tr>
					<tr>
						<td width="50">用户名称</td>
						<td> 
							<input type="text" name="userName" class="input1" value="${shopUser.userName}"/>
						</td>
					</tr>
					<tr>
						<td width="50">密码</td>
						<td> 
							<input type="password" name="passWord" class="input1" value=""/>
						</td>
					</tr>
					<tr>
						<td width="50">确认密码</td>
						<td> 
							<input type="password" name="passWord1" class="input1" value=""/>
						</td>
					</tr>
					<tr>
						<td width="50">用户类型</td>
						<td> 
							<select name="type">
								<option value="0" ${shopUser.type==0?"selected='selected'":""}>普通用户</option>
								<option value="1" ${shopUser.type==1?"selected='selected'":""}>超级用户</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="50">角色ID</td>
						<td> 
							<input type="text" name="rid" class="input1" value="${shopUser.rid}"/>
						</td>
					</tr>
					<tr>
						<td colspan="2"> 
							<c:choose>
								<c:when test="${id!=null&&id!=''}">
									<input type="submit" class="btn1" value="编辑">
								</c:when>
								<c:otherwise>
									<input type="submit" class="btn1" value="新增">
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</table>
				</form>			
			
				<br>
				
				<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th width="50">编号</th>
						<th width="50">用户名</th>
						<th width="50">角色ID</th>
						<th width="50">类型</th>
						<th width="50">操作</th>
					</tr>
					
					<c:forEach items="${pageView.records}" var="e">
						<tr>
							<td>${e.id}</td>
							<td>${e.userName}</td>
					        <td>${e.rid}</td>
					        <td>
								<c:choose>
									<c:when test="${e.type==1}">
										超级管理员
									</c:when>
									<c:otherwise>
										普通用户
									</c:otherwise>
								</c:choose>
					        </td>
					        <td>
					        	<a href="${pageContext.request.contextPath}/purview/shopUserList?id=${e.id}">[编辑]</a>
					        	<a href="javascript:if(confirm('确定要删除么')){location.href='${pageContext.request.contextPath}/purview/shopUserDel?id=${e.id}'};">[删除]</a>
					        </td>
					    </tr>
					</c:forEach>
				</table>
				
				<br>
				
				<stag:PageViewTag baseUrl="${pageContext.request.contextPath}/purview/shopUserList" pageView="${pageView}" />
			</div>
			
		</div>
	</div>


<body>
</body>
</html>

