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
			
				<form action="${pageContext.request.contextPath}/purview/roleAdd" method="post" >
				<input type="hidden" name="id" value="${id}">
				<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">						
					<tr>
						<th colspan="2"> 
							 角色添加
						</th>
					</tr>
					<tr>

						<td width="50">角色名称</td>
						<td> 
							<input type="text" name="name" class="input1" value="${role.name}"/>
						</td>
					</tr>
					<tr>
						<td width="50">权限设置</td>
						<td> 
							<table class="gridtable1" width="90%" border="0" cellspacing="0" cellpadding="0">
							<c:forEach items="${plist}" var="e">
								<tr>
									<td width="80">${e.name}</td>
									<td>
										<c:forEach items="${e.subPowerList}" var="e1" varStatus="status">
											<c:if test="${status.index!=0&&status.index%8==0}">
												<br/>
												<br/>
											</c:if>
											
											<input type="checkBox" ${checkMap[String.valueOf(e1.id)]==String.valueOf(e1.id)?"checked='checked'":""} name="powerChk" value="${e1.id}"/>${e1.name}
										
										</c:forEach>
									</td>
								</tr>
							</c:forEach>
							</table>
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
							<input type="button" onclick="javascript:location.href='${pageContext.request.contextPath}/purview/roleList';" class="btn1" value="返回列表">
						</td>
					</tr>
				</table>
				</form>
			
			</div>
			
		</div>
	</div>

</body>
</html>

