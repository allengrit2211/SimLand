<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 


						<input type="hidden" id="totalPageAjax" value="${totalPage}" >

						<c:if test="${fn:length(list) <= 0}">
							<p class="p5">没有查询到结果,请更换条件重新查询...</p>
							
							<h5 class="h_r">为您推荐的店铺</h5>
							
							<div class="line"></div>
							
							<c:set var="list" value="${list1}"/>
						
						</c:if>
						

						<c:forEach var="item" items="${list}" varStatus="status">
							<div class='box'>
								<p class="p4">
									<a  sid='${item.id}' href='${pageContext.request.contextPath}/shop/showShop?id=${item.id}' data-transition="slide" class='toShop a0 ui-link'>${item.cname}</a>
								</p>
								<p class="p3">
									<span class="s1">经营模式：</span><a class="s2" href='${pageContext.request.contextPath}/shop/showShop?id=${item.id}' data-transition="slide">${item.bmodel}</a>
								</p>
								<p class='p1'>
									<span class="s1">主营产品：</span><a class="s2" href='${pageContext.request.contextPath}/shop/showShop?id=${item.id}' data-transition="slide">${item.engage}</a>
								</p>
								<p class='p2'>
									<span class='s1'>${item.commodityNum}</span> <span>件产品</span> |
									<a  href='#' class='a2' sid='${item.id}'><span class='s2'>&nbsp;</span>
									<span class='s3'>${item.collectNum}</span></a> <span>次</span>
									<span class='s4 star star${item.score}'>&nbsp;</span>
								</p>
							</div>
							<div class='line'></div>
						</c:forEach> 