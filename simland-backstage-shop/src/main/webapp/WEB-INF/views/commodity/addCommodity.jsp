<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"  />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/ckeditor/_samples/sample.css" />
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

.upfile{
	vertical-align: top;
	margin:5px 0 0 5px;
}

.inventoryInput input{
margin:2px;
}



</style>

<script type="text/javascript">

$(function(){
	
	var contextPath = '${pageContext.request.contextPath}';
	var images = new Array();//图片地址数组
	
	$("#categoryType").change(function(){
		var typeId = $(this).find("option:selected").val();
		$("#attrBox_"+typeId).removeAttr("display")
		$("#attrBox_"+typeId).siblings(".attrBox").removeClass('show').end().addClass('show');
		$("#inventoryBox_"+typeId).siblings(".inventoryBox").removeClass('show').end().addClass('show');
		loadInventoryView()
	});
	
	
	$(".addAttr").click(function(){
		var span = $(this).parent("span").next();
		alert($(this).attr("index"))
		span.append("<input class='_input' name='attr"+$(this).attr("index")+"' size='5' type='text' cpid='"+$(this).attr("cpid")+"'><input name='attr"+$(this).attr("index")+"Val' value='"+$(this).attr("cpid")+"' size=\"8\" type=\"hidden\">");
		span.attr("num",span.find("._input").length);
		loadInventoryView();
	});
	$(".removeAttr").click(function(){
		var span = $(this).parent("span").next();
		var last = span.find("._input:last");
		if(span.find("._input").index(last)==0){
			span.attr("num",span.find("._input").length);
			return;
		}
		
		last.remove();
		span.attr("num",span.find("._input").length);
		
		loadInventoryView();
		
		var typeId = $("#categoryType").find("option:selected").val();
		var trl = $("#inventoryTable_"+typeId).find("tbody tr").length;
		//清理图片数组
		images = images.slice(0,trl);
	});
	
	$(".allPrice").bind("input propertychange",function(){
		var typeId = $("#categoryType").find("option:selected").val();
		var tmp = this;
		$("#inventoryTable_"+typeId).find("tbody tr input[name='price']").each(function(i,e){
			$(e).val($(tmp).val());
		});
	});
	
	$(".allInventoryNum").bind("input propertychange",function(){
		var typeId = $("#categoryType").find("option:selected").val();
		var tmp = this;
		$("#inventoryTable_"+typeId).find("tbody tr input[name='nums']").each(function(i,e){
			$(e).val($(tmp).val());
		});
	});	
	
	
	//加载库存显示table
	function loadInventoryView(){
		var option = $("select[name='categoryType']").find("option:selected");
		var typeId = option.val();
		
		var attr1s = new Array();
		var attr2s = new Array();
		
		if($(option).attr("attr1id")!=""){
			$("#attrBox_"+typeId).find(".attr1 .inventoryInput ._input").each(function(i,e){
				attr1s.push($(e).val()+"_"+$(e).attr("cpid"));
			});
		}
		
		if($(option).attr("attr2id")!=""){
			$("#attrBox_"+typeId).find(".attr2 .inventoryInput ._input").each(function(i,e){
				attr2s.push($(e).val()+"_"+$(e).attr("cpid"));
			});
		}

		var htmlStr = "";
		var _index = 0;
		if(attr1s.length>0&&attr2s.length>0){
			$(attr1s).each(function(i,e){
				$(attr2s).each(function(ii,ee){
					htmlStr+="<tr>";
					htmlStr+="<td>"+e.split("_")[0]+"</td>";
					htmlStr+="<td>"+ee.split("_")[0]+"</td>";
					htmlStr+="<td><input name='price' size=\"7\" type=\"text\"></td>";
					htmlStr+="<td><input name='nums' size=\"7\" type=\"text\"></td>";
					htmlStr+="<td><input name='productCode' size=\"7\" type=\"text\"></td>";
					htmlStr+="<td><input type='hidden' name='imageName' value='"+(images[_index]?(images[_index]):(images[_index]=''))+"'><img src='"+(images[_index]?(contextPath+images[_index]):(images[_index]=''))+"' width='30' height='30' id='showimg"+_index+"'><input index='"+_index+"' name='file' id='file"+_index+"' class='upfile' type='file'></td>";
					htmlStr+="</tr>";
					_index = _index+1;
				});
			});		
		}
		
		if(attr1s.length>0&&attr2s.length==0){
			$(attr1s).each(function(i,e){
				htmlStr+="<tr>";
				htmlStr+="<td>"+e.split("_")[0]+"</td>";
				htmlStr+="<td><input name='price' size=\"7\" type=\"text\"></td>";
				htmlStr+="<td><input name='nums' size=\"7\" type=\"text\"></td>";
				htmlStr+="<td><input name='productCode' size=\"7\" type=\"text\"></td>";
				htmlStr+="<td><input type='hidden' name='imageName' value='"+(images[_index]?(images[_index]):(images[_index]=''))+"'><img src='"+(images[_index]?(contextPath+images[_index]):(images[_index]=''))+"' width='30' height='30' id='showimg"+_index+"'><input index='"+_index+"' name='file' id='file"+_index+"' class='upfile' type='file'></td>";
				htmlStr+="</tr>";
				_index = _index+1;
			});		
		}
		
		
		if(attr1s.length==0&&attr2s.length==0){
			htmlStr+="<tr>";
			htmlStr+="<td><input name='price' size=\"7\" type=\"text\"></td>";
			htmlStr+="<td><input name='nums' size=\"7\" type=\"text\"></td>";
			htmlStr+="<td><input name='productCode' size=\"7\" type=\"text\"></td>";
			htmlStr+="<td><input type='hidden' name='imageName' value='"+(images[_index]?(images[_index]):(images[_index]=''))+"'><img src='"+(images[_index]?(contextPath+images[_index]):(images[_index]=''))+"' width='30' height='30' id='showimg"+_index+"'><input index='"+_index+"' name='file' id='file"+_index+"' class='upfile' type='file'></td>";
			htmlStr+="</tr>";
			_index = _index+1;
		}
		
		
		$("#inventoryTable_"+typeId+" tbody").html("");
		$("#inventoryTable_"+typeId+" tbody").append(htmlStr);

		$(".inventoryInput input").unbind("input propertychange");
		$(".inventoryInput input").bind('input propertychange', function(event){
			loadInventoryView();
			event.stopPropagation(); 
		});
		
		
		
		$(".upfile").unbind("change");
		$(".upfile").bind("change",function(e){
			upCommImage(this);
		});
		
	}
	
	var flag = false;
	
	//添加商品
	$("#addCommodityBtn").click(function(){
	
		if(flag==true){
			return;
		}
		flag = true;
		$("#addCommodityBtn").attr("disabled","disabled");
		
		$.ajax({
			type : "post",
			url : contextPath + "/commodity/addCommodity",
			data : $("#commodityForm").serialize(),
			cache : false,
			async : true,
			dataType : 'jsonp',
			success : callBack,
			error : function(data, df, d) {
				flag = false;
				$("#addCommodityBtn").removeAttr("disabled");
			}
		});
		
		function callBack(data){
			flag = false;
			$("#addCommodityBtn").removeAttr("disabled");
			if(data.code==1){
				alert(data.msg);
			}else{
				alert(data.msg);
			}
		}
		
	});
	
	
	function upCommImage(obj){
	
		var fileId = $(obj).attr("id");
		var index = $(obj).attr("index");
		
		$.ajaxFileUpload({
             url: contextPath+'/commodity/uploadImage', //用于文件上传的服务器端请求地址
             type: 'post',
             cache : false,
             data: { Id: '123', name: 'lunis' }, //此参数非常严谨，写错一个引号都不行
             secureuri: false, //一般设置为false
             fileElementId: fileId, //文件上传空间的id属性  <input type="file" id="file" name="file" />
             dataType: 'json', //返回值类型 一般设置为json
             success: function (data, status)  //服务器成功响应处理函数
             {
             	if(data){
             		var data1= eval(data);
	                 if(data1.code==1){
	                 	//alert(data1.msg);
	                 	$("#"+fileId).prev().attr("src",contextPath+data.toUrl);
	                 	$("#"+fileId).prev().prev().val(data.toUrl);
	                 	images[index] = data.toUrl;
	                 }else{
	                 	alert(data1.msg)
	                 }
	                 
				    $("#"+fileId).bind("change",function(e){
						upCommImage(this);
					});
             	}else{
             		alert("上传错误");
             	}
             },
             error: function (data, status, e)//服务器响应失败处理函数
             {
                 alert(e);
             }
		})
		
	}
	
	
	
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
				<form id="commodityForm" method="post" action="${pageContext.request.contextPath}/commodity/addCommodity">
					<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th width="80">商品名称</th>
							<td><input name="cname" class="input3"> </td>
						</tr>
						<tr>
							<th>销售价格</th>
							<td><input name="marketPrice" class="input3"> </td>
						</tr>
						<tr>
							<th>实际价格</th>
							<td><input name="realPrice" class="input3"> </td>
						</tr>
						<tr>
							<th>类别属性</th>
							<td>
								<select name="categoryType" id="categoryType">
									<c:forEach items="${cplist}" var="item" varStatus="status0">
										<option value="${item.id}" attr1="${item.categoryPropertiesList[0].name}" attr1Id="${item.categoryPropertiesList[0].id}" attr2="${item.categoryPropertiesList[1].name}" attr2Id="${item.categoryPropertiesList[1].id}">${item.name}</option>
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
									<div id="attrBox_${item.id}" class="attrBox ${status0.index==0?"show":""}" >
										<c:forEach items="${item.categoryPropertiesList}" var="item1" varStatus="status">
											<div class="attr${status.index+1}">
												<span style="display: block;padding-bottom:10px;">  <a href="#" class="removeAttr" cpid="${item1.id}" index="${status.index+1}">-</a> ${item1.name} <a href="#"  class="addAttr" cpid="${item1.id}" tindex="${status0.index}" index="${status.index+1}">+</a></span>
												<span num="0" class="inventoryInput"></span>
											</div>
										</c:forEach>
										<c:if test="${fn:length(item.categoryPropertiesList)==0}">
											<span style="display: block;padding-bottom:10px;">  <a href="#" class="removeAttr" cpid="${item1.id}" index="${status.index+1}">-</a> 数量 <a href="#"  class="addAttr" cpid="${item1.id}" tindex="${status0.index}" index="${status.index+1}">+</a></span>
											<span num="0" class="inventoryInput"></span>
										</c:if>
									</div>
									<div id="inventoryBox_${item.id}" class="inventoryBox ${status0.index==0?"show":""}">
										<table class="gridtable" id="inventoryTable_${item.id}" width="90%" border="0" cellspacing="0" cellpadding="0">
											
											<thead>
												<tr>
													<c:if test="${item.categoryPropertiesList[0]!=null}">
														<th width="50">${item.categoryPropertiesList[0].name}</th>
													</c:if>
													<c:if test="${item.categoryPropertiesList[1]!=null}">
														<th width="50">${item.categoryPropertiesList[1].name}</th>
													</c:if>
													<th width="120">价格 <input type="text" size="7" class="allPrice"></th>
													<th width="120">库存 <input type="text" size="7" class="allInventoryNum"></th>
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
							
									<p>
										<label for="editor1">
											Editor 1:</label>
										<textarea cols="80" id="editor1" name="editor1" rows="10">请编辑商品图文</textarea>
									</p>
									<ckeditor:replace  replace="editor1" basePath="${pageContext.request.contextPath}/js/ckeditor/" />
							</td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td><input type="button" id="addCommodityBtn" value="添加" style="width:80px;height:40px;font-size:16px;"></td>
						</tr>
					</table>
				
				
				</form>
				

			</div>
			
		</div>
	</div>


<body>
</body>
</html>

