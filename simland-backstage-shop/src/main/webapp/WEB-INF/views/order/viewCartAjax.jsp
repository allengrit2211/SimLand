<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stag" uri="http://simland-tags.sf.net"  %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<table class="gridtable" width="90%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<th>商品ID</th>
		<th>商品名称</th>
		<th>商品价格</th>
		<th>商品图片</th>
		<th>属性</th>
		<th>购买数量</th>
		<th>操作</th>
	</tr>
<c:forEach items="${clist}" var="item">
	<tr>
		<td>${item.c.id}</td>
		<td>${item.c.name}</td>
		<td>${item.price}</td>
		<td></td>
		<td>${item.c.attr1Value} ${item.c.attr1Value}</td>
		<td>${item.buyNum}</td>
		<td><a href="javascript:;" class="delCartBtn" sku="${item.sku}">[删除]</a></td>
	</tr>
</c:forEach>
</table>