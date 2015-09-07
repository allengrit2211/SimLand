<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

						<div class="cart">
							<c:forEach items="${cart.cartItems}" var="item">
								<div class="shopBox">
									<div class="title">
										<div class="check">
											<input type="checkbox" name="carShopCheck" data-cacheval="false" data-role="none">
										</div>
										<a class="a1" data-transition="slide" href="${pageContext.request.contextPath}/shop/showShop?id=${item.key.id}">${item.key.cname}</a>
										<a class="a2" data-transition="none" href="#preferential" data-rel="popup" data-position-to="window">优惠说明</a>
									</div>
									<c:forEach items="${item.value}" var="item1">
										<div class="commodity" id="commodity_${item1.sku}">
											<div class="check">
												<input value="${item1.sku}" type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
											</div>
											<div class="box">
												<div class="c_info">
													<div class="c_img">
														<a data-transition="slide" href="${pageContext.request.contextPath}/commodity/show?id=${item1.c.id}"> <img width="53" alt="" src="${pageContext.request.contextPath}/${item1.c.img}">
														</a>
													</div>
													<div class="c_infobox c_skuBox_${item1.sku}">
														<div class="c_title">
															<a data-transition="slide" href="${pageContext.request.contextPath}/commodity/show?id=${item1.c.id}">${item1.c.name}</a>
														</div>
														<div class="c_attr">
															<p>
																<span>${item1.c.attr1.name}  </span><span class="s_1">${item1.c.attr1Value}</span> <span>${item1.c.attr2.name}  </span><span class="s_2">${item1.c.attr2Value}</span>
															</p>
														</div>
														<div class="c_price">
															<p>
																<span class="s_3 red">￥ ${item1.price} </span> x <span class="s_4">${item1.buyNum}</span>
															</p>
														</div>
													</div>
													<div class="c_infoboxEdit">
													
														<div class="c_attr">
															<a id="attrShowBtn_${item1.sku}" class="cartPopupBtn" data-rel="popup" data-position-to="window"  data-transition="pop" href="#addCartPopup${item1.sku}">
															<c:if test="${item1.c.attr1.name!=null}">
																${item1.c.attr1.name}:${item1.c.attr1Value}
															</c:if>
															<c:if test="${item1.c.attr2.name!=null}">
															 	${item1.c.attr2.name}:${item1.c.attr2Value}
															</c:if>
															</a>
														</div>
														<div class="c_price">
															<span>${item1.price}</span> x <span>${item1.buyNum}</span>
														</div>
														<div id="addCartPopup${item1.sku}" data-role="popup" class="popupBox" data-transition="pop">
															<a href="#" data-rel="back" data-role="button" data-theme="a" data-icon="delete" data-iconpos="notext" class="ui-btn-right"> Close</a>
															<div class="popup">
																<form action="${pageContext.request.contextPath}/buy/addCart" class="cartForm" method="post" data-transition="slide">
																	<input type="hidden" name="_stype" value="ajax">
																	<div class="box">
																		<div class="img">
																			<img alt="" src="${pageContext.request.contextPath}/${item1.c.img}">
																		</div>
																		<div class="info inventoryShowBox_${item1.c.id}">
																			<span class="s1">￥<span class="price">${item1.price}</span></span> 
																			<span class="s2">库存 <strong class="inventoryNum">[${item1.c.inventoryMap[_inventory_+(item1.c.attr1Val)+_+(item1.c.attr2Val)][0]}] </strong> 件</span> 
																			<span class="s3">已选 <span class="attr1ValShow">${item1.c.attr1Value}</span> <span class="attr2ValShow">${item1.c.attr2Value}</span></span> 

																			<input type="hidden" value="${item1.c.id}" name="cid" /> 
																			<input type="hidden" value="" id="buyTypeHid" /> 
																			<input type="hidden" value="${item1.buyNum}" name="buyNum"/>
																			<input type="hidden" value="${item1.sku}" name="sku" />
																			<input type="hidden" class="attr1Val" name="attr1Val" tit="${item1.c.attr1.name}" value="${item1.c.attr1Val}" /> 
																			<input type="hidden" class="attr2Val" name="attr2Val" tit="${item1.c.attr2.name}" value="${item1.c.attr2Val}" />
																		</div>
																		<div class="number inventoryShowBox_${item1.c.id}">
																			<!-- 库存信息 -->
																			<c:forEach items="${item1.c.inventoryMap}" var="item2">
																				<input type="hidden" value="${item2.value[0]}" price="${item2.value[1]}" img="${item2.value[2]}" name="${item2.key}"/>
																			</c:forEach>
																			<a href="#" class="a1 revNum" sku="${item1.sku}" cid="${item1.c.id}"></a> <input sku="${item1.sku}" cid="${item1.c.id}" name="buyNum" class="input buyNum" type="number" data-role="none" value="${item1.buyNum}" /> <a sku="${item1.sku}" cid="${item1.c.id}" href="#" class="a2 addNum"></a>
																		</div>
																		
																	</div>
																	<div class="clear"></div>
																	<div class="line"></div>
																	<div class="box">
																		<span class="title">${item1.c.attr1.name}</span>
																		<div class="attr1">
																			<c:forEach items="${item1.c.attr1List}" var="item6" varStatus="status">
																				<a class="choseAttr1 ${(item1.c.attr1Val==item6[0])?"attrOn":""}" aid="${item6[0]}" cid="${item1.c.id}" href="#">${item6[1]}</a>
																			</c:forEach>
																		</div>
																	</div>
																	<div class="line"></div>
																	<div class="box">
																		<span class="title">${item1.c.attr2.name}</span>
																		<div class="attr2">
																			<c:forEach items="${item1.c.attr2List}" var="item6" varStatus="status">
																				<a class="choseAttr2 ${(item1.c.attr2Val==item6[0])?"attrOn":""}" aid="${item6[0]}" cid="${item1.c.id}" href="#">${item6[1]}</a>
																			</c:forEach>
																		</div>
																	</div>
																	<div class="line"></div>
																	<div class="buyBtn">
																		<a sku="${item1.sku}" class="confirmCommodityBtn a1" href="#">确定</a>
																	</div>
																</form>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</c:forEach>
							<!-- 
							<div class="shopBox">
								<div class="title">
									<div class="check">
										<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
									</div>
									<a class="a1" href="#">深圳市大本钟贸易有限...</a> 
									<a class="a2" data-transition="none" href="#preferential" data-rel="popup" data-position-to="window">优惠说明</a>
								</div>
	
								<div class="commodity">
									<div class="check">
										<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
									</div>
									<div class="box">
										<div class="c_info">
											<div class="c_img">
												<img width="53" alt=""
													src="${pageContext.request.contextPath}/images/commodity/c_4.jpg">
											</div>
											<div class="c_infobox">
												<div class="c_title">【十年资质】裸钻提供商 天然南非钻石 深圳水贝珠宝 一手...</div>
												<div class="c_price">
													<p>
														<span>克数:</span><span class="s_1">100</span> <span>手寸:</span><span
															class="s_2">2x2</span>
													</p>
													<p>
														<span class="s_3">$123123 </span><span class="s_4">x 1</span>
													</p>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="commodity">
									<div class="check">
										<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
									</div>
									<div class="box">
										<div class="c_info">
											<div class="c_img">
												<img width="53" alt=""
													src="${pageContext.request.contextPath}/images/commodity/c_4.jpg">
											</div>
											<div class="c_infobox">
												<div class="c_title">【十年资质】裸钻提供商 天然南非钻石 深圳水贝珠宝 一手...</div>
												<div class="c_price">
													<p>
														<span>克数:</span><span class="s_1">100</span> <span>手寸:</span><span
															class="s_2">2x2</span>
													</p>
													<p>
														<span class="s_3">$123123 </span><span class="s_4">x 1</span>
													</p>
												</div>
											</div>
										</div>
									</div>
								</div>
								
							</div>
							
							
							<div class="shopBox">
								<div class="title">
									<div class="check">
										<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
									</div>
									<a class="a1" href="#">深圳市大本钟贸易有限...</a> 
									<a class="a2" data-transition="none" href="#preferential" data-rel="popup" data-position-to="window">优惠说明</a>
								</div>
	
								<div class="commodity">
									<div class="check">
										<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
									</div>
									<div class="box">
										<div class="c_info">
											<div class="c_img">
												<img width="53" alt=""
													src="${pageContext.request.contextPath}/images/commodity/c_4.jpg">
											</div>
											<div class="c_infobox">
												<div class="c_title">【十年资质】裸钻提供商 天然南非钻石 深圳水贝珠宝 一手...</div>
												<div class="c_price">
													<p>
														<span>克数:</span><span class="s_1">100</span> <span>手寸:</span><span
															class="s_2">2x2</span>
													</p>
													<p>
														<span class="s_3">$123123 </span><span class="s_4">x 1</span>
													</p>
												</div>
											</div>
										</div>
									</div>
								</div>
	
								<div class="commodity">
									<div class="check">
										<input type="checkbox" name="carCheck" data-cacheval="false" data-role="none">
									</div>
									<div class="box">
										<div class="c_info">
											<div class="c_img">
												<img width="53" alt=""
													src="${pageContext.request.contextPath}/images/commodity/c_4.jpg">
											</div>
											<div class="c_infobox">
												<div class="c_title">【十年资质】裸钻提供商 天然南非钻石 深圳水贝珠宝 一手...</div>
												<div class="c_price">
													<p>
														<span>克数:</span><span class="s_1">100</span> <span>手寸:</span><span
															class="s_2">2x2</span>
													</p>
													<p>
														<span class="s_3">$123123 </span><span class="s_4">x 1</span>
													</p>
												</div>
											</div>
										</div>
									</div>
								</div>
								
							</div>
							
							 -->
							 
							 <div id="preferential" data-role="popup" data-theme="a">
								<div style="width:300px;overflow:hidden;">
									<h3>优惠说明</h3>
									<hr class="line">
									<p>单笔订单满10000元，返回样品费</p>
									<p>单笔订单满10000元，返回样品费</p>
									<p>单笔订单满10000元，返回样品费</p>
								</div>
							</div>
							 
						</div>
					
					
					