var cart = {
	initialize : function() {

		// 添加购物车按钮
		$("#addCartBtn").unbind().click(cart.addCart);

		// 直接购买按钮
		$("#goBuyBtn").unbind().click(cart.goBuy);

		// 确认按钮
		$("#confirmOrderBtn").unbind().click(cart.confirmOrder);

		// 选择attr1
		$("#CommodityPage .popup .box .attr1 a").unbind().click(cart.choseAttr1);

		// 选择attr2
		$("#CommodityPage .popup .box .attr2 a").unbind().click(cart.choseAttr2);

		/***********************************************************************
		 * 购物车数量控制
		 */
		$("#revNum").unbind().click(function() {
			var num = parseInt($("#buyNum").val()) - 1;
			$("#buyNum").val((!$.isNumeric(num) || num <= 0) ? 1 : num);
		});

		$("#addNum").unbind().click(function() {
			var num = parseInt($("#buyNum").val()) + 1;
			$("#buyNum").val(cart.checkInventoryNum($.isNumeric(num) ? num : 1));
		});

		$("#buyNum").unbind().change(function() {
			$("#buyNum").val(cart.checkInventoryNum($("#buyNum").val()));
		});

		/***********************************************************************
		 * 购物车数量控制 END
		 */

		// 购物车商家商品选择checkbox
		$("input[name='carShopCheck']").unbind().click(cart.carShopCheckAll);
		// 全选
		$("#carCheckAll").unbind().click(cart.carCheckAll);
		// 编辑购物车
		$("#cartEditBtn").unbind().click(cart.cartEdit);

	},
	addCart : function() {// 添加购物车
		$("#buyTypeHid").val(0);
	},
	goBuy : function() {// 直接购买
		$("#buyTypeHid").val(1);
	},
	confirmOrder : function() {

		if ($("#attr1Val").val() == '') {
			app.message("请选择" + $("#attr1Val").attr("tit"))
			return;
		}

		if ($("#attr2Val").val() == '') {
			app.message("请选择" + $("#attr2Val").attr("tit"))
			return;
		}

		addCartAjax();

		function addCartAjax() {
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

		function addCartAjaxCallBack(data) {
			if (data.code == -100) {
				$.mobile.changePage(app.servicerURL + "loginPage", {
					transition : "slide"
				});
			} else if (data.code == 1) {

				var type = $("#buyTypeHid").val();
				if (type == 0) {
					$.mobile.changePage(app.servicerURL + "buy/cart", {
						transition : "slide"
					});
				} else {
					$.mobile.changePage(app.servicerURL + "order/confirmOrder", {
						transition : "slide"
					});
				}
			} else {
				alert(data.msg);
			}
		}

	},
	choseAttr1 : function() {// 选择颜色尺码
		$(this).siblings().removeClass('attrOn').end().addClass('attrOn');
		$("#attr1Val").val($(this).attr("aid"));
		$("#attr1ValShow").html($(this).text());
		// 库存显示
		cart.inventoryShow();

		$("#buyNum").val(cart.checkInventoryNum($("#buyNum").val()));
	},
	choseAttr2 : function() {// 选择颜色尺码
		$(this).siblings().removeClass('attrOn').end().addClass('attrOn');
		$("#attr2Val").val($(this).attr("aid"));
		$("#attr2ValShow").html($(this).text());

		cart.inventoryShow();

		$("#buyNum").val(cart.checkInventoryNum($("#buyNum").val()));
	},
	inventoryShow : function() {
		if ($("#_inventory_" + $("#attr1Val").val() + "_" + $("#attr2Val").val()).length > 0) {
			$("#inventoryNum").html($("#_inventory_" + $("#attr1Val").val() + "_" + $("#attr2Val").val()).val());
		}
	},
	carShopCheckAll : function() {// 商家商品全选

		var tmp = this;

		$(this).prop("checked") ? check(true) : check(false);

		function check(flag) {
			$(tmp).parents(".shopBox").find(".commodity input[name='carCheck']").each(function(i, e) {
				$(this).prop("checked", flag);
			});
		}
	},
	carCheckAll : function() {// 选择所有

		$(this).prop("checked") ? check(true) : check(false);

		function check(flag) {
			$(".commodity input[name='carCheck']").each(function() {
				$(this).prop("checked", flag);
			});

			$(".check input[name='carShopCheck']").each(function() {
				$(this).prop("checked", flag);
			});
		}
	},
	checkInventoryNum : function(num) {// 商品库存前台检测
		
		if (!$.isNumeric(num)||num <= 0)
			return 1;
			
		if ($("#_inventory_" + $("#attr1Val").val() + "_" + $("#attr2Val").val()).length > 0) {

			var val = $("#_inventory_" + $("#attr1Val").val() + "_" + $("#attr2Val").val()).val();
			if (num > parseInt(val)) {
				return parseInt(val);
			}
		}
		return num;
	},
	getCurrentInventory : function() {// 获取选择属性后库存数s

	},
	cartEdit : function() {// 编辑购物车
		if($("#cartEditBtn").text()=='编辑'){
			$(".cartEdit").attr("style","display:inline-block");
			$(".cartNormal").attr("style","display:none");
			$("#cartEditBtn").text("完成");
			
			$(".c_infobox").attr("style","display:none");
			$(".c_infoboxEdit").attr("style","display:inline-block");
			
			
		}else if($("#cartEditBtn").text()=='完成'){
			$(".cartEdit").attr("style","display:none");
			$(".cartNormal").attr("style","display:inline-block");
			$("#cartEditBtn").text("编辑");
			
			$(".c_infobox").attr("style","display:inline-block");
			$(".c_infoboxEdit").attr("style","display:none");
		}
	},
	cartEditComplete : function(){

	}
}
