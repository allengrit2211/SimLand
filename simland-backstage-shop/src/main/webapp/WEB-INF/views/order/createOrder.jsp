<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stag" uri="http://simland-tags.sf.net"  %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.lightbox_me.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cityselect/jquery.cityselect.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"  />
<link type="text/css" href="${pageContext.request.contextPath}/css/pageView.css" rel="stylesheet"  />
<title></title>
<style type="text/css">
#choseCommodityPopup{
	display: none;
}
#choseCommodityPopup .top{
	height:45px;
	background-color: #FFF;
	line-height:45px;
	text-indent:10px;
}

.table.gridtable{margin-left:0;}

</style>


<script type="text/javascript">

var contextPath = '${pageContext.request.contextPath}';

$(function(){

	
		$("#choseCommodity").click(function(e){
		    $('#choseCommodityPopup').lightbox_me({
		        closeClick:true,
		        modalCSS:{width:'800px',top:'50px'},
		        onLoad: function() { 
					searchCommodity();
		        },
		        onClose: loadCartCommodity
			});
		});
		
		
		function loadCartCommodity(){
			$.ajax({
				type : "post",
				url : contextPath + "/shop/viewCartAjax",
				cache : false,
				async : true,
				dataType : 'html',
				success : callBack,
				error : function(data, df, d) {
					alert(d)
				}
			});
			
			function callBack(data){
				$("#cartShow").html(data);
				$("#cartShow .delCartBtn").click(function(e){
				   delChoseCommodity(this)
				});				
			}
		}
		
		function searchCommodity(param){
			$.ajax({
				type : "post",
				url : contextPath + "/commodity/popupList?"+(param?param:''),
				data : $("#commodityForm").serialize(),
				cache : false,
				async : true,
				dataType : 'html',
				success : callBack,
				error : function(data, df, d) {
					alert(d)
				}
			});
			
			function callBack(data){
			
				$("#commodityList").html(data);
				$("#commodityList .pageView a").bind("click",function(event){
					searchCommodity($(this).attr("href").split("?")[1]);
					event.preventDefault(); 
				});
				$("#commodityList .choseCommodity").click(function(e){
				   choseCommodity(this)
				});
			}
		}
		
		loadCartCommodity();


		function delChoseCommodity(obj){
			
			var skus = new Array();
			skus.push($(obj).attr("sku"));
			
			$.ajax({
				type : "post",
				url : contextPath + "/shop/delCart",
				data : {skus:skus.join(",")},
				cache : false,
				async : true,
				dataType : 'jsonp',
				success : callBack,
				error : function(data, df, d) {
					alert(d)
				}
			});
			
			function callBack(data){
				if(data.code==1){
					loadCartCommodity();
				}
			}
		}
		
		function choseCommodity(obj){
			
		
				//String attr1Val = request.getParameter("attr1Val");
				//String attr2Val = request.getParameter("attr2Val");
				//String buyNum = request.getParameter("buyNum");
			
			var param = {};
			param.cid= $(obj).attr("cid");
			param.uid = $("#uid").val();
			param.attr1Val = $("select[name=attr1Select_"+param.cid+"]").val();
			param.attr2Val = $("select[name=attr2Select_"+param.cid+"]").val();
			param.buyNum = $("input[name="+param.cid+"_buynum]").val();
			
			var inventoryNum = $("input[name="+param.cid+"_inventory_"+param.attr1Val+"_"+param.attr2Val+"]");
			if(!inventoryNum.val()||parseInt(inventoryNum.val())<=0){
				alert("该款商品库存不足，请更换属性重新选择");
				return;
			}
			
			if(!param.buyNum||parseInt(param.buyNum)<=0){
				alert("购买数量不正确"); 
				return;
			}
			
			$.ajax({
				type : "post",
				url : contextPath + "/shop/addCart",
				data : param,
				cache : false,
				async : true,
				dataType : 'jsonp',
				success : callBack,
				error : function(data, df, d) {
					alert(d)
				}
			});
			
			function callBack(data){
				alert(data.msg)
			}
			
		}	

		$("#city_2").citySelect({prov:"广东",nodata:"none"});
		
		
		var flag = false;
		
		$("#createOrderBtn").bind("click",function(){
		
			if(flag==true){
				return;
			}
			flag = true;
			$("#createOrderBtn").attr("disabled","disabled");		
		
			$.ajax({
				type : "post",
				url : contextPath + "/shop/submitOrder",
				data : $("#createOrderForm").serialize(),
				cache : false,
				async : true,
				dataType : 'jsonp',
				success : callBack,
				error : function(data, df, d) {
					flag = false;
					$("#createOrderBtn").removeAttr("disabled");
					alert(d)
				}
			});
			
			function callBack(data){
				flag = false;
				$("#createOrderBtn").removeAttr("disabled");
				if(data.code==1){
					alert(data.msg);
				}else{
					alert(data.msg);
				}
			}
			
		});
		
		
		
});




</script>
</head>
	
	<c:import url="../top.jsp"/>
	<div style="clear:both;"></div>
	<div id="content">
		<c:import url="../left.jsp"/>
		<div id="right_box">
			<c:import url="../right_top.jsp"/>
			<div id="right_font"><img src="${pageContext.request.contextPath}/images/main_14.gif"/> 您现在所在的位置：订单管理 → <span class="bfont">创建订单</span></div>
			
			<div id="msg">
				&nbsp;&nbsp;&nbsp;&nbsp;${msg}
			</div>
			
			<div id="right_content">
				<c:if test="${fn:length(msg)==0}">
					<form action="" method="post" id="createOrderForm">
						<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr align="left">
								<td colspan="2">用户名:${waitOrder.username}<input type="hidden" name="uid" id="uid" value="${waitOrder.uid}"><input type="hidden" name="wid" id="wid" value="${waitOrder.id}"></td>
							</tr>
							<tr>
								<td colspan="2">
									<div style="margin-top:15px;">
										&nbsp;&nbsp;&nbsp;收货人: <input type="text" name="receiverName"> 
									</div>
									<div style="margin-top:15px;">
										&nbsp;&nbsp;&nbsp;手机号: <input type="text" name="receiverPhone"> 
									</div>
									
									    <div id="city_2" style="margin-top:15px;display:inline-block;">
									    	购货地址: 
									  		<select name="receiverProvince" class="prov"></select> 
									    	<select name="receiverCity" class="city" disabled="disabled"></select>
									        <select name="receiverDistrict" class="dist" disabled="disabled"></select>
									    </div>
										<div style="margin-top:15px;">
											详细地址: <input type="text" name="receiverAddress" value="" style="width:300px;"> 
										</div>
								</td>
							</tr>
							<tr>
								<td colspan="2"><span>商品列表 </span><span> <input type="button" value="选择商品" id="choseCommodity"></span></td>
							</tr>
							<tr>
								<td colspan="2">
									<div id="cartShow">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									总金额:10000 ￥
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<label style="display: inline-block;vertical-align:top;">留言:</label> <textarea rows="5" cols="50" name="remark"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="button" id="createOrderBtn" value="创建订单">
								</td>
							</tr>
						</table>
					</form>
				</c:if>
			</div>
			
		</div>
	</div>
	
	<div id="choseCommodityPopup">
		<div class="top">
			<form mothod="post" action="">
				商品编号:<input type="text" >
				商品名称:<input type="text">
			</form>	
		</div>
		<div id="commodityList">
			
		</div>
	</div>
	


<body>
</body>
</html>

