<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"  />
<style type="text/css">
.attr1,.attr2,.inventoryBox{
margin-bottom:8px;
}
.attrBox{
display: none;}

.attrBox.show{
display: block;}

.inventoryBox {
	display: none;
}

.inventoryBox.show{
display: block;
}


.inventoryInput input{
margin:2px;
}

</style>

<script type="text/javascript">

$(function(){
	
	
	
	$("select[name='category']").change(function(){
		var option = $(this).find("option:selected");
		var index = option.attr("index")
		$(".attrBox:eq("+index+")").removeAttr("display")
		$(".attrBox:eq("+index+")").siblings(".attrBox").removeClass('show').end().addClass('show');
		$(".inventoryBox:eq("+index+")").siblings(".inventoryBox").removeClass('show').end().addClass('show');
		loadInventoryView()
	});
	
	
	$(".addAttr").click(function(){
		var span = $(this).parent("span").next();
		span.append("<input size='5' type='text'>");
		span.attr("num",span.find("input").length);
		loadInventoryView();
	});
	$(".removeAttr").click(function(){
		var span = $(this).parent("span").next();
		var last = span.find("input:last");
		if(span.find("input").index(last)==0){
			span.attr("num",span.find("input").length);
			return;
		}
		
		last.remove();
		span.attr("num",span.find("input").length);
		loadInventoryView();
	});
	
	$(".allPrice").bind("input propertychange",function(){
		var tmp = this;
		$(this).parents(".inventoryBox table").find("tbody tr").each(function(i,e){
			$(e).find("td:eq(2) input").val($(tmp).val());
		});
	});
	
	$(".allInventoryNum").bind("input propertychange",function(){
		var tmp = this;
		$(this).parents(".inventoryBox table").find("tbody tr").each(function(i,e){
			$(e).find("td:eq(3) input").val($(tmp).val());
		});
	});	
	
	
	//加载库存显示table
	function loadInventoryView(){
		var option = $("select[name='category']").find("option:selected");
		var index = option.attr("index");
		
		var attr1s = new Array();
		var attr2s = new Array();
		if($(option).attr("attr1id")!=""){
			$(".attrBox:eq("+index+") .attr1 .inventoryInput input").each(function(i,e){
				attr1s.push($(e).val()+"");
			});
		}
		if($(option).attr("attr2id")!=""){
			$(".attrBox:eq("+index+") .attr2 .inventoryInput input").each(function(i,e){
				attr2s.push($(e).val()+"");
			});
		}
		

		var htmlStr = "";
		if(attr1s.length>0&&attr2s.length>0){
			$(attr1s).each(function(i,e){
				$(attr2s).each(function(ii,ee){
					htmlStr+="<tr>";
					htmlStr+="<td>"+e+"</td>";
					htmlStr+="<td>"+ee+"</td>";
					htmlStr+="<td><input size=\"8\" type=\"text\"></td>";
					htmlStr+="<td><input size=\"8\" type=\"text\"></td>";
					htmlStr+="<td><input size=\"8\" type=\"text\"></td>";
					htmlStr+="<td></td>";
					htmlStr+="</tr>";
				});
			});		
		}
		
		$("#inventoryTable_"+index+" tbody").html("");
		$("#inventoryTable_"+index+" tbody").append(htmlStr);

		$(".inventoryInput input").unbind("input propertychange");
		$(".inventoryInput input").bind('input propertychange', function(event){
			loadInventoryView();
			event.stopPropagation(); 
		});
	}
	

		/*
		$(".inventoryInput input").bind('input propertychange', function() {
			alert(attr1s.length)
			alert(attr2s.length)
			var htmlStr = "";
			if(attr1s.length>0&&attr2s.length>0){
				$(attr1s).each(function(i,e){
					$(attr2s).each(function(ii,ee){
						htmlStr+="<tr><td>"+e+"</td><td>"+ee+"</td><td><input size=\"8\" type=\"text\"></td><td><input size=\"8\" type=\"text\"></td><td><input size=\"8\" type=\"text\"></td><td></td></tr>";
					});
				});		
			}
			$("#inventoryTable tbody").html("");
			$("#inventoryTable tbody").append(htmlStr);
		});
	
		*/
	
	
	loadInventoryView();
	
});


</script>
<title>添加商品</title>
</head>
	
	<c:import url="../top.jsp"/>
	<div style="clear:both;"></div>
	<div id="content">
		<c:import url="../left.jsp"/>
		<div id="right_box">
			<c:import url="../right_top.jsp"/>
			<div id="right_font"><img src="${pageContext.request.contextPath}/images/main_14.gif"/> 您现在所在的位置：商品管理 → <span class="bfont">添加商品 </span></div>
			
			<div id="msg">
				&nbsp;&nbsp;&nbsp;&nbsp;${msg}
			</div>
			
			<div id="right_content">
				<form>
					<table class="gridtable" width="80%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th width="80">商品名称</th>
							<td><input name="name" class="input3"> </td>
						</tr>
						<tr>
							<th>销售价格</th>
							<td><input name="name" class="input3"> </td>
						</tr>
						<tr>
							<th>实际价格</th>
							<td><input name="name" class="input3"> </td>
						</tr>
						<tr>
							<th>类别属性</th>
							<td>
								<select name="category">
									<c:forEach items="${cplist}" var="item" varStatus="status0">
										<option index="${status0.index}" value="${item.id}" attr1="${item.categoryPropertiesList[0].name}" attr1Id="${item.categoryPropertiesList[0].id}" attr2="${item.categoryPropertiesList[1].name}" attr2Id="${item.categoryPropertiesList[1].id}">${item.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								库存信息
							</th>
							<td>
							
								<c:forEach items="${cplist}" var="item" varStatus="status0">
									<div class="attrBox ${status0.index==0?"show":""}" >
										<c:forEach items="${item.categoryPropertiesList}" var="item1" varStatus="status">
											<div class="attr${status.index+1}">
												<span style="display: block;padding-bottom:10px;">  <a href="#" class="removeAttr">-</a> ${item1.name} <a href="#"  class="addAttr">+</a></span>
												<span num="0" class="inventoryInput"></span>
											</div>
										</c:forEach>
									</div>
									<div class="inventoryBox ${status0.index==0?"show":""}">
										<table class="gridtable" id="inventoryTable_${status0.index}" width="80%" border="0" cellspacing="0" cellpadding="0">
											
											<thead>
												<tr>
													<c:if test="${item.categoryPropertiesList[0]!=null}">
														<th>${item.categoryPropertiesList[0].name}</th>
													</c:if>
													<c:if test="${item.categoryPropertiesList[1]!=null}">
														<th>${item.categoryPropertiesList[1].name}</th>
													</c:if>
													<th>价格 <input type="text" size="2" class="allPrice"></th>
													<th>库存 <input type="text" size="2" class="allInventoryNum"></th>
													<th>商品编码</th>
													<th>商品图片</th>
												</tr>
											</thead>
											<tbody>
												
											</tbody>

											
											<!-- 
											<tr>
												<td>100g</td>
												<td>2x2</td>
												<td><input type="text" size="4"></td>
												<td><input type="text" size="4"></td>
												<td><input type="text" size="8"></td>
												<td>商品图片</td>
											</tr>
											<tr>
												<td>100g</td>
												<td>3x3</td>
												<td><input type="text" size="4"></td>
												<td><input type="text" size="4"></td>
												<td><input type="text" size="8"></td>
												<td>商品图片</td>
											</tr>
											 -->
										</table>
									</div>
								</c:forEach>

							</td>
						</tr>
						<tr>
							<th>
								图文编辑
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td><input type="button" value="添加" style="width:80px;height:40px;font-size:16px;"></td>
						</tr>
					</table>
				
				
				</form>
				

			</div>
			
		</div>
	</div>


<body>
</body>
</html>

