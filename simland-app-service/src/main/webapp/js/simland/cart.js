var cart = {
	initialize : function() {
		
		//添加购物车按钮
		$("#addCartBtn").click(cart.addCart);
		
		//直接购买按钮
		$("#goBuyBtn").click(cart.goBuy);
		
		//确认按钮
		$("#confirmOrderBtn").click(cart.confirmOrder);
		
		//选择attr1
		$("#CommodityPage .popup .box .attr1 a").click(cart.choseAttr1);
		
		//选择attr2
		$("#CommodityPage .popup .box .attr2 a").click(cart.choseAttr2);
		
		/***
		 * 购物车数量控制
		 */
		$("#revNum").click(function(){
			var num = parseInt($("#buyNum").val())+1;
			$("#buyNum").val($.isNumeric(num)?num:1);
		});
		
		$("#addNum").click(function(){
			var num = parseInt($("#buyNum").val())-1;
			$("#buyNum").val((!$.isNumeric(num)||num<=0)?1:num);
		});
		
		$("#buyNum").change(function(){
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
			alert("请选择"+$("#attr1Val").attr("tit"))
			return;
		}
		
		if($("#attr2Val").val()==''){
			alert("请选择"+$("#attr2Val").attr("tit"))
			return;
		}
		
		var type = $("#buyTypeHid").val();
		if(type==0){
			addCartAjax();
		}
		
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
				$.mobile.changePage(app.servicerURL +"loginPage", "slideup");
			}else if(data.code==1){
				$.mobile.changePage(app.servicerURL +"buy/cart", "slideup");
			}else{
				alert(data.msg);
			}
		}
		
		
	},
	choseAttr1 : function(){//选择颜色尺码
		$(this).siblings().removeClass('attrOn').end().addClass('attrOn');
		$("#attr1Val").val($(this).attr("aid"));
		$("#attr1ValShow").html($(this).text());
	},
	choseAttr2 : function(){//选择颜色尺码
		$(this).siblings().removeClass('attrOn').end().addClass('attrOn');
		$("#attr2Val").val($(this).attr("aid"));
		$("#attr2ValShow").html($(this).text());
	}
}
cart.initialize();


