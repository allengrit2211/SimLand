<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stag" uri="http://simland-tags.sf.net"  %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

	$("#batchUp").bind("click",function(){//上架
		$("#issueType").val(1);
		$("#issueForm").submit();
	});
	$("#batchDown").bind("click",function(){//下架
		$("#issueType").val(2);
		$("#issueForm").submit();
	});	
	
	
	
	
	$(".batchUpBtn").bind("click",function(){
		$(this).parents("tr").find("input[name='ids']").attr("checked","checked");
		$("#issueType").val(1);
		$("#issueForm").submit();
	});
	$(".batchDownBtn").bind("click",function(){
		$(this).parents("tr").find("input[name='ids']").attr("checked","checked");
		$("#issueType").val(2);
		$("#issueForm").submit();
	});
	
	
	$("input[name='idsAll']").bind("click",function(){
		$(this).prop("checked") ? check(true) : check(false);

		function check(flag) {
			$("input[name='ids']").each(function() {
				$(this).prop("checked", flag);
			});

			$("input[name='ids']").each(function() {
				$(this).prop("checked", flag);
			});
		}	
	});
	


});
</script>

</head>
<body>
	<c:import url="../top.jsp"/>
	<div style="clear:both;"></div>
	<div id="content">
		<c:import url="../left.jsp"/>
		<div id="right_box">
			<c:import url="../right_top.jsp"/>
			<div id="right_font"><img src="${pageContext.request.contextPath}/images/main_14.gif"/> 您现在所在的位置：商品管理 → <span class="bfont">发布商品列表 </span></div>
			
			<div id="msg">
				&nbsp;&nbsp;&nbsp;&nbsp;${msg}
			</div>
			
			<div id="right_content">
			
				<form action="${pageContext.request.contextPath}/" method="get" >
				<table width="75%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="50"></td>
						<td> 
						</td>
					</tr>
				</table>
				</form>
			
				<br/>
				
				<form action="${pageContext.request.contextPath}/commodity/issueCommodity" method="post" id="issueForm">
					<input type="hidden" name="issueType" id='issueType' value="0">
					&nbsp;&nbsp;
					<input type="button" id="batchUp" value="批量发布">
					<input type="button" id="batchDown" value="批量下架">
					<input type="hidden" name="currentPage" value="${pageView.currentPage}">
					
				
					<br/>
					<br/>
				
					<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th><input name="idsAll" type="checkbox"></th>
							<th width="50">商品ID</th>
							<th width="50">商品名称</th>
							<th width="50">类别</th>
							<th colspan="5">
								库存信息
							</th>
							<th>状态</th>
							<th>发布时间</th>
							<th>下架原因</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${pageView.records}" var="item">
							<tr>
								<td align="center"><input name="ids" value="${item.id}" type="checkbox"></td>
								<td width="20">${item.id}</td>
								<td width="220">${item.name}</td>
								<td width="30">${item.type}</td>
								<td colspan="5">
									<c:if test="${fn:length(item.cInventoryList)>0}">
										<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<th>属性</th>
												<th>库存</th>
												<th>价格</th>
											</tr>
											<c:forEach items="${item.cInventoryList}" var="item1">
											<tr>
												<td>${item.attr1.name}:${item1.cpvalue1} ${item.attr2.name}:${item1.cpvalue2}</td>
												<td>${item1.nums}</td>
												<td>${item1.price}</td>
											</tr>
											</c:forEach>
										</table>
									</c:if>
								</td>
								<td>
									<c:if test="${item.status==0}">新建</c:if>
									<c:if test="${item.status==1}">已上架</c:if>
									<c:if test="${item.status==2}">已下架</c:if>
								</td>
								<td width="120">
									${item.inventory.issueTime}
								</td>
								<td>${item.inventory.cause}</td>
								<td width="80"><a class="batchUpBtn" href="javascript:;">[发布]</a> <a class="batchDownBtn" href="javascript:;">[下架]</a></td>
							</tr>
						</c:forEach>
					</table>
				
				</form>

				<stag:PageViewTag baseUrl="${pageContext.request.contextPath}/commodity/issueCommodityList" pageView="${pageView}" />

			</div>
			
		</div>
	</div>



</body>
</html>

