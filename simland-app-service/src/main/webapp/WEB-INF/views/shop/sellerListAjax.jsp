<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
				
						<c:if test="${fun:length(list) <= 0}">
							<p class="p5">没有查询到结果,请更换条件重新查询...</p>
							<br>
							<h4>为您推荐的店铺</h4>
							<div class="line"></div>
							<c:forEach var="item" items="${list1}" varStatus="status">   
								<div class='box'>
								<p class="p4">
									<a sid='${item.id}' href='${pageContext.request.contextPath}/shop/showShop?id=${item.id}' data-transition="slide" class='toShop a0 ui-link'>${item.cname}</a>
									<a  href='#' class='a1 ui-link'>260M</a>
								</p>
								<p class="p3">
									<a href='${pageContext.request.contextPath}/shop/showShop?id=${item.id}' data-transition="slide">
										${item.caddress}
									</a>
								</p>
								<p class='p1'>
									<span>主营产品:</span><span>${item.engage}</span>
								</p>
								<div class='line'></div>
								<p class='p2'>
									<span class='s1'>${item.commodityNum}</span> <span>件产品</span> |
									<a  href='#' class='a2' sid='${item.id}'><span class='s2'>&nbsp;</span>
									<span class='s3'>${item.collectNum}</span></a> <span>次</span>
									<span class='s4 star star${item.score}'>&nbsp;</span>
								</p>
								</div>
							</c:forEach> 
							
						</c:if>

						<c:forEach var="item" items="${list}" varStatus="status">   
							<div class='box'>
							<p class="p4">
								<a  sid='${item.id}' href='${pageContext.request.contextPath}/shop/showShop?id=${item.id}' data-transition="slide" class='toShop a0 ui-link'>${item.cname}</a>
								<a  href='#' class='a1 ui-link'>260M</a>
							</p>
							<p class="p3">
								<a href='${pageContext.request.contextPath}/shop/showShop?id=${item.id}' data-transition="slide">
									${item.caddress}
								</a>
							</p>
							<p class='p1'>
								<span>主营产品:</span><span>${item.engage}</span>
							</p>
							<div class='line'></div>
							<p class='p2'>
								<span class='s1'>${item.commodityNum}</span> <span>件产品</span> |
								<a  href='#' class='a2' sid='${item.id}'><span class='s2'>&nbsp;</span>
								<span class='s3'>${item.collectNum}</span></a> <span>次</span>
								<span class='s4 star star${item.score}'>&nbsp;</span>
							</p>
							</div>
						</c:forEach> 