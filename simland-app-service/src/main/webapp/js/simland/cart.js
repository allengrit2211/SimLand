var cart = {
	initialize : function() {
		
		//添加购物车按钮
		$("#addCartBtn").unbind().click(cart.addCart);
		
		//直接购买按钮
		$("#goBuyBtn").unbind().click(cart.goBuy);
		
		//确认按钮
		$("#confirmOrderBtn").unbind().click(cart.confirmOrder);
		
		//选择attr1
		$("#CommodityPage .popup .box .attr1 a").unbind().click(cart.choseAttr1);
		
		//选择attr2
		$("#CommodityPage .popup .box .attr2 a").unbind().click(cart.choseAttr2);
		
		/***
		 * 购物车数量控制
		 */
		$("#revNum").unbind().click(function(){
			var num = parseInt($("#buyNum").val())+1;
			$("#buyNum").val($.isNumeric(num)?num:1);
		});
		
		$("#addNum").unbind().click(function(){
			var num = parseInt($("#buyNum").val())-1;
			$("#buyNum").val((!$.isNumeric(num)||num<=0)?1:num);
		});
		
		$("#buyNum").unbind().change(function(){
			if(!$.isNumeric($("#buyNum").val()))
				$("#buyNum").val(1);
		});
		/***
		 * 购物车数量控制 END
		 */
		
	},
	addCart : function(){//添加购物车
		$("#buyTypeHid").val(0);
	},
	goBuy : function(){//直接购买
		$("#buyTypeHid").val(1);
	},
	confirmOrder : function(){
		
		if($("#attr1Val").val()==''){
			app.message("请选择"+$("#attr1Val").attr("tit"))
			return;
		}
		
		if($("#attr2Val").val()==''){
			app.message("请选择"+$("#attr2Val").attr("tit"))
			return;
		}
		
		addCartAjax();
		
		function addCartAjax(){
			$.ajax({
				type : "get",
				url : app.servicerURL + "buy/addCart",
				data : $("#cartForm").serialize(),
				cache : false,
				async : true,
				dataType : 'jsonp',
				success : addCartAjaxCallBack,
				error : function(data, df, d) {
					app.message("数据加载失败")
				}
			});
		}
		
		function addCartAjaxCallBack(data){
			if(data.code==-100){
				$.mobile.changePage(app.servicerURL +"loginPage", {transition:"slide"});
			}else if(data.code==1){
				
				var type = $("#buyTypeHid").val();
				if(type==0){
					$.mobile.changePage(app.servicerURL +"buy/cart",{transition:"slide"});
				}else{
					$.mobile.changePage(app.servicerURL +"order/confirmOrder",{transition:"slide"});
				}
			}else{
				alert(data.msg);
			}
		}
		
		
	},
	choseAttr1 : function(){//选择颜色尺码
		$(this).siblings().removeClass('attrOn').end().addClass('attrOn');
		$("#attr1").val($(this).attr("aid"));
		$("#attr1Val").val($(this).text());
		$("#attr1ValShow").html($(this).text());
	},
	choseAttr2 : function(){//选择颜色尺码
		$(this).siblings().removeClass('attrOn').end().addClass('attrOn');
		$("#attr2").val($(this).attr("aid"));
		$("#attr2Val").val($(this).text());
		$("#attr2ValShow").html($(this).text());
	}
}
