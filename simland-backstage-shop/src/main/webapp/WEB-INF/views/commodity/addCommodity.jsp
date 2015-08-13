<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
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


var editor1 = null;

$(function(){
	
	var contextPath = '${pageContext.request.contextPath}';
	var images = new Array();//图片地址数组
	
	$("#categoryType").find("option").each(function(i,e){
		var id = $(e).val();
		images[id] = new Array();
	});
	
	$("#categoryType").change(function(){//类别切换
		var typeId = $(this).find("option:selected").val();
		$("#attrBox_"+typeId).removeAttr("display")
		$("#attrBox_"+typeId).siblings(".attrBox").removeClass('show').end().addClass('show');
		$("#inventoryBox_"+typeId).siblings(".inventoryBox").removeClass('show').end().addClass('show');
		loadInventoryView()
	});
	
	
	$(".addAttr").click(function(){//属性输入框添加
		var typeId = $("select[name='categoryType']").find("option:selected").val();
		var span = $(this).parent("span").next();
		span.append("<input class='_input' name='attr"+$(this).attr("index")+"_"+typeId+"' cpid='"+$(this).attr("cpid")+"' size='5' type='text'><input name='attr"+$(this).attr("index")+"Val_"+typeId+"' value='"+$(this).attr("cpid")+"' size=\"8\" type=\"hidden\">");
		span.attr("num",span.find("._input").length);
		loadInventoryView();
	});
	
	$(".removeAttr").click(function(){
	
		var typeId = $("#categoryType").find("option:selected").val();
		var span = $(this).parent("span").next();
		var last = span.find("._input:last");
		if(span.find("._input").index(last)==0){
			span.attr("num",span.find("._input").length);
			return;
		}
		
		last.remove();
		span.find("input[name='attr1Val_"+typeId+"']:last").remove();
		span.find("input[name='attr2Val_"+typeId+"']:last").remove();
		span.attr("num",span.find("._input").length);
		
		loadInventoryView();
		
		
		var trl = $("#inventoryTable_"+typeId).find("tbody tr").length;
		//清理图片数组
		images[typeId] = images[typeId].slice(0,trl);
	});

	
	$(".allPrice").bind("input propertychange",allPrice);//设置所有价格
	
	
	function allPrice(){
		var typeId = $("#categoryType").find("option:selected").val();
		var tmp = this;
		
		if(!$.isNumeric($(this).val())){
			$(this).val("");
			return;
		}
		
		$("#inventoryTable_"+typeId).find("tbody tr input[name='price_"+typeId+"']").each(function(i,e){
			$(e).val($(tmp).val());
		});
	}
	
	$(".allInventoryNum").bind("input propertychange",allInventoryNum);	///设置所有库存数量
	
	function allInventoryNum(){
		var typeId = $("#categoryType").find("option:selected").val();
		var tmp = this;
		
		if(!$.isNumeric($(this).val())){
			$(this).val("");
			return;
		}
		
		$("#inventoryTable_"+typeId).find("tbody tr input[name='nums_"+typeId+"']").each(function(i,e){
			$(e).val($(tmp).val());
		});
	}
	
	
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


		var _index = 0;
		var htmlStr = "";
		if(attr1s.length>0&&attr2s.length>0){
			$(attr1s).each(function(i,e){
				$(attr2s).each(function(ii,ee){
					images[typeId][_index] = images[typeId][_index]?(images[typeId][_index]):'';
					htmlStr+="<tr>";
					htmlStr+="<td>"+e.split("_")[0]+"<input type='hidden' name='iAttr1_"+typeId+"' value='"+e.split("_")[0]+"'><input type='hidden' name='iAttr1Val_"+typeId+"' value='"+e.split("_")[1]+"'></td>";
					htmlStr+="<td>"+ee.split("_")[0]+"<input type='hidden' name='iAttr2_"+typeId+"' value='"+ee.split("_")[0]+"'><input type='hidden' name='iAttr2Val_"+typeId+"' value='"+ee.split("_")[1]+"'></td>";
					htmlStr+="<td><input name='price_"+typeId+"' size=\"7\" type=\"text\"></td>";
					htmlStr+="<td><input name='nums_"+typeId+"' size=\"7\" type=\"text\"></td>";
					htmlStr+="<td><input name='productCode_"+typeId+"' size=\"7\" type=\"text\"></td>";
					htmlStr+="<td><input type='hidden' name='imageName_"+typeId+"' value='"+(images[typeId,_index])+"'><img src='"+((images[typeId][_index])?(contextPath+images[typeId][_index]):'')+"' width='30' height='30' id='showimg_"+typeId+"_"+_index+"'><input index='"+_index+"' name='file' id='file_"+typeId+"_"+_index+"' class='upfile' type='file'></td>";
					htmlStr+="</tr>";
					_index = _index+1;
				});
			});		
		}
		
		
		if(attr1s.length>0&&$(option).attr("attr2id")==""){
			$(attr1s).each(function(i,e){
				images[typeId][_index] = images[typeId][_index]?(images[typeId][_index]):'';
				htmlStr+="<tr>";
				htmlStr+="<td>"+e.split("_")[0]+"<input type='hidden' name='iAttr1_"+typeId+"' value='"+e.split("_")[0]+"'><input type='hidden' name='iAttr1Val_"+typeId+"' value='"+e.split("_")[1]+"'></td>";
				htmlStr+="<td><input name='price_"+typeId+"' size=\"7\" type=\"text\"></td>";
				htmlStr+="<td><input name='nums_"+typeId+"' size=\"7\" type=\"text\"></td>";
				htmlStr+="<td><input name='productCode_"+typeId+"' size=\"7\" type=\"text\"></td>";
				htmlStr+="<td><input type='hidden' name='imageName_"+typeId+"' value='"+(images[typeId][_index])+"'><img src='"+((images[typeId][_index])?(contextPath+images[typeId][_index]):'')+"' width='30' height='30' id='showimg_"+typeId+"_"+_index+"'><input index='"+_index+"' name='file' id='file_"+typeId+"_"+_index+"' class='upfile' type='file'></td>";
				htmlStr+="</tr>";
				_index = _index+1;
			});		
		}
		
		if($(option).attr("attr1id")!=""||$(option).attr("attr2id")!=""){
			$("#inventoryTable_"+typeId+" tbody").html("");
		}
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
		
		editor1.updateElement();
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
	
		var typeId = $("select[name='categoryType']").find("option:selected").val();
	
		var fileId = $(obj).attr("id");
		var index = $(obj).attr("index");
		$.ajaxFileUpload({
             url: contextPath+'/commodity/uploadImage?ajax=ajax', //用于文件上传的服务器端请求地址
             type: 'post',
             cache : false,
             data: { }, //此参数非常严谨，写错一个引号都不行
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
	                 	images[typeId][index] = data.toUrl;
	                 }else{
	                 	alert(data1.msg)
	                 }
             	}else{
             		alert("上传错误");
             	}
             },
             error: function (data, status, e)//服务器响应失败处理函数
             {
                 alert(e);
             }
		})
		
			                 
	    $("#"+fileId).bind("change",function(e){
			upCommImage(this);
		});
		
	}
	
	
	$("input[name='marketPrice']").bind("input propertychange",function(){
			if(!$.isNumeric($(this).val())){
				$(this).val("");
			}
	});
	$("input[name='realPrice']").bind("input propertychange",function(){
			if(!$.isNumeric($(this).val())){
				$(this).val("");
			}
	});
	
	
	loadInventoryView();
	var editor1 = CKEDITOR.replace('editor1',{
		height : 560,
		width : 320
	}); //参数‘content’是textarea元素的name属性值，而非id属性值
	setTimeout(function(){
		$("#cke_editor1").css({"width":"346"});
	},100);
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
							<th>相关</th>
							<td>
								<input type="checkbox" name="isNew" value="1"> <span style="vertical-align:top;">新品</span>
								<input type="checkbox" name="isSpecial" value="1"> <span style="vertical-align:top;">特价</span>
								<input type="checkbox" name="isVip" value="1"> <span style="vertical-align:top;">VIP</span>
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
												<span style="display: block;padding-bottom:10px;">  <a href="#" class="removeAttr" index="${status.index+1}" cpid="${item1.id}">-</a> ${item1.name} <a href="#"  class="addAttr" index="${status.index+1}" cpid="${item1.id}">+</a></span>
												<span num="0" class="inventoryInput"></span>
											</div>
										</c:forEach>
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
												<c:if test="${fn:length(item.categoryPropertiesList)==0}">
													<tr>
														<td>
														<input type='hidden' name='iAttr1_${item.id}' value='0'><input type='hidden' name='iAttr1Val_${item.id}' value='0'>
														<input type='hidden' name='iAttr2_${item.id}' value='0'><input type='hidden' name='iAttr2Val_${item.id}' value='0'>
														<input name="price_${item.id}" size="7" type="text"></td>
														<td><input name="nums_${item.id}" size="7" type="text"></td>
														<td><input name="productCode_${item.id}" size="7" type="text"></td>
														<td>
															<input type="hidden" name="imageName_${item.id}" value="">
															<img src="" width="30" height="30" id="showimg_${item.id}_1">
															<input index="1" name="file" id="file_${item.id}_1" class="upfile" type="file">
														</td>
													</tr>
												</c:if>
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
										<textarea  id="editor1" name="editor1" rows="10">请编辑商品图文</textarea>
									</p>
							</td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td><input type="button" id="addCommodityBtn" value="保存" style="width:80px;height:40px;font-size:16px;"></td>
						</tr>
					</table>
				
				
				</form>
				

			</div>
			
		</div>
	</div>


<body>
</body>
</html>

