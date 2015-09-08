var order = {
	initialize : function() {

		// 提交订单按钮
		$("#submitOrderBtn").unbind().click(order.submitOrder);
		
		$("#cancelOrderBtn").unbind().click(order.cancelOrder);
		

	},
	cancelOrder : function(){//取消订单
		var tmp = this;
		$.ajax({
			type : "get",
			url : app.servicerURL + "/order/cancelOrder",
			data :{oid:$(this).attr("oid")},
			cache : false,
			async : true,
			dataType : 'jsonp',
			success : callBack,
			error : function(data, df, d) {
				app.message("网络请求失败，请检查您的网络设置")
			}
		});
		
		function callBack(data){
			if(data.code==1){
				$(tmp).next().remove();
				$(tmp).remove();
			}
			app.message(data.msg);
		}
	},
	submitOrder : function() {
		$.ajax({
			type : "get",
			url : app.servicerURL + "/order/submitOrder",
			data : $.mobile.activePage.find("#confirmOrderForm").serialize(),
			cache : false,
			async : true,
			dataType : 'jsonp',
			success : submitOrderCallBack,
			error : function(data, df, d) {
				app.message("网络请求失败，请检查您的网络设置")
			}
		});

		function submitOrderCallBack(data) {
			if (data.code == 1) {
				app.message(data.msg);
				$.mobile.changePage(app.servicerURL + "/user/orders", {
					transition : "slide"
				});
			} else if(data.code == -200){
				
				var dd = eval('('+data.msg+')');
				var html = "";
				//$(dd).each(function(i,e){
					//html += e.cname;
					//html +="<br/>";
				//});
				html +="商品库存数量不足，请减少商品数量或更换其他商品";
				
				app.message(html);
			}else {
				app.message(data.msg)
			}
		}
	}
}