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
			<div id="right_font"><img src="${pageContext.request.contextPath}/images/main_14.gif"/> 您现在所在的位置：首页 → 权限管理→ <span class="bfont"> 权限列表  </span></div>
			
			<div id="right_content">
			
				<div id="msg">
					&nbsp;&nbsp;&nbsp;&nbsp;${msg}
				</div>
			
				<form action="${pageContext.request.contextPath}/purview/powerAdd" method="post" >
				<input type="hidden" name="id" value="${id}">
				<table class="gridtable" width="30%" border="0" cellspacing="0" cellpadding="0">						
					<tr>
						<th colspan="2"> 
							权限新增与编辑
						</th>
					</tr>
					<tr>

						<td width="50">权限名称</td>
						<td> 
							<input type="text" name="name" class="input1" value="${power.name}"/>
						</td>
					</tr>
					<tr>
						<td width="50">管理地址</td>
						<td> 
							<input type="text" name="url" class="input3" value="${power.url}"/>
						</td>
					</tr>
					<tr>
						<td width="50">父级ID</td>
						<td> 
							<input type="text" name="pid" class="input1" value="${power.pid}"/>
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
				
				<form action="${pageContext.request.contextPath}/purview/powerList" method="get" >
				<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th width="50">搜索</th>
						<td> 
							权限名称 <input type="text" name="nameLike" class="input1" value="${nameLike}"/>
							<input type="submit" class="btn1" value="查询">
						</td>
					</tr>
				</table>
				</form>
				
				<br>
				
				<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th width="50">编号</th>
						<th width="50">模块名称</th>
						<th width="50">管理地址</th>
						<th width="50">父级模块</th>
						<th width="50">操作</th>
					</tr>
					<c:forEach items="${pageView.records}" var="e">
						<tr>
							<td>${e.id}</td>
							<td>${e.name}</td>
					        <td>${e.url}</td>
					        <td>${e.pid}</td>
					        <td>
					        	<a href="${pageContext.request.contextPath}/purview/powerList?id=${e.id}">[编辑]</a>
					        </td>
					    </tr>
					 </c:forEach>   
				</table>
				
				<br>
				
				<table width="80%" border="0" cellspacing="0" cellpadding="0">
						<tr>
					        <td colspan="2">
					        	<jsp:useBean id='parameterMap' class='java.util.HashMap' scope="page"/>
					        	<c:set target="${parameterMap}" property="nameLike" value="${nameLike}"/>
         						<stag:PageViewTag baseUrl="${pageContext.request.contextPath}/purview/powerList" pageView="${pageView}" parameterMap="${parameterMap}" />
					        </td>
					    </tr>
				</table>
			</div>
			
		</div>
	</div>


<body>
</body>
</html>

