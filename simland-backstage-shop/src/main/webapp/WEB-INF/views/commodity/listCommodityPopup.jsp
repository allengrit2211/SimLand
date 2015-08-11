<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stag" uri="http://simland-tags.sf.net"%>

<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"  />

<table style="margin-left:0;" class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<th width="50">商品ID</th>
		<th width="50">商品名称</th>
		<th width="50">商品图片</th>
		<th width="50">属性</th>
		<th width="50">购买数量</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${pageView.records}" var="item">
		<tr>
			<td width="20">${item.id}</td>
			<td width="220">${item.name}</td>
			<td width="80"></td>
			<td width="200">
				<select name="attr1Select_${item.id}">
					<option value="0">请选择${item.attr1.name}</option>
					<c:forEach items="${item.attr1List}" var="item1" varStatus="status">
						<option value="${item1[0]}">${item1[1]}</option>
					</c:forEach>			
				</select>
				<select name="attr2Select_${item.id}">
					<option value="0">请选择${item.attr2.name}</option>
					<c:forEach items="${item.attr2List}" var="item1" varStatus="status">
						<option value="${item1[0]}">${item1[1]}</option>
					</c:forEach>
				</select>
			</td>
			<td width="20"><input type="text" size="4" value="1" name="${item.id}_buynum"></td>
			<td width="30">
			<!-- 库存信息 -->
			<c:forEach items="${item.inventoryMap}" var="item1">
				<input type="hidden" value="${item1.value[0]}" price="${item1.value[1]}" img="${item1.value[2]}" name="${item.id}${item1.key}"/>
				<c:set var="inventoryNum" value="${inventoryNum+item1.value[0]}"/>
			</c:forEach>
			<a class="choseCommodity" href="javascript:;" cid="${item.id}">[选择]</a></td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="6">
			<stag:PageViewTag baseUrl="${pageContext.request.contextPath}/commodity/list" pageView="${pageView}" />
		</td>
	</tr>
</table>

