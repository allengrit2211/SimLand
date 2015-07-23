var cart = {
	initialize : function() {

		// 添加购物车按钮
		$.mobile.activePage.find("#addCartBtn").unbind().click(cart.addCart);
		// 直接购买按钮
		$.mobile.activePage.find("#goBuyBtn").unbind().click(cart.goBuy);

		// 确认按钮 商品页面
		$.mobile.activePage.find("#confirmOrderBtn").unbind().click(cart.confirmOrder);

		$.mobile.activePage.find("#cartConfirmOrder").unbind().click(cart.cartConfirmOrder);

		cart.cartChoseAttr();// 购物车选择属性

		cart.cartBuyNum();// 购物车数量控制

		cart.cartCheck();// 选择提交

		// 编辑购物成商品，填出窗口确定按钮
		$.mobile.activePage.find(".confirmCommodityBtn").unbind().click(cart.confirmCommodityBtn);

		// 删除购物车商品
		$.mobile.activePage.find("#delCartBtn").unbind().click(cart.delCart);

	},
	cartChoseAttr : function() {// 购物车选择属性
		// 选择attr1
		$.mobile.activePage.find(".choseAttr1").unbind().click(cart.choseAttr1);

		// 选择attr2
		$.mobile.activePage.find(".choseAttr2").unbind().click(cart.choseAttr2);
	},
	choseAttr1 : function() {// 选择颜色尺码
		$(this).siblings().removeClass('attrOn').end().addClass('attrOn');
		$.mobile.activePage.find(".inventoryShowBox .attr1Val").val($(this).attr("aid"));
		$.mobile.activePage.find(".inventoryShowBox .attr1ValShow").html($(this).text());
		// 库存显示
		cart.inventoryShow();

		$.mobile.activePage.find(".buyNum").val(cart.checkInventoryNum($.mobile.activePage.find(".buyNum").val()));
	},
	choseAttr2 : function() {// 选择颜色尺码

		$(this).siblings().removeClass('attrOn').end().addClass('attrOn');
		$.mobile.activePage.find(".inventoryShowBox .attr2Val").val($(this).attr("aid"));
		$.mobile.activePage.find(".inventoryShowBox .attr2ValShow").html($(this).text());

		cart.inventoryShow();

		$.mobile.activePage.find(".buyNum").val(cart.checkInventoryNum($.mobile.activePage.find(".buyNum").val()));
	},
	cartBuyNum : function() {// 购物车购买数量控制
		$.mobile.activePage.find(".addNum").unbind().click(function() {
			var num = parseInt($(this).prev().val()) + 1;
			var val = cart.checkInventoryNum($.isNumeric(num) ? num : 1);
			$(this).prev().val(val);

			var buyNum = $("#addCartPopup" + $(this).attr("sku") + " input[name='buyNum']");
			if (buyNum.length > 0) {
				buyNum.val(val);
			}
		});

		$.mobile.activePage.find(".revNum").unbind().click(function() {
			var num = parseInt($(this).next().val()) - 1;
			var val = (!$.isNumeric(num) || num <= 0) ? 1 : num;
			$(this).next().val(val);

			var buyNum = $("#addCartPopup" + $(this).attr("sku") + " input[name='buyNum']");
			if (buyNum.length > 0) {
				buyNum.val(val);
			}
		});

		$.mobile.activePage.find(".buyNum").unbind().change(function() {
			var val = cart.checkInventoryNum($(this).val());
			$(this).val(val);

			var buyNum = $("#addCartPopup" + $(this).attr("sku") + " input[name='buyNum']");
			if (buyNum.length > 0) {
				buyNum.val(val);
			}
		});
	},
	cartCheck : function() {// 购物车选择提交确认订单
		// 购物车商家商品选择checkbox
		$.mobile.activePage.find("input[name='carShopCheck']").unbind().click(cart.carShopCheckAll);
		// 全选
		$.mobile.activePage.find("#carCheckAll").unbind().click(cart.carCheckAll);
		// 编辑购物车
		$.mobile.activePage.find("#cartEditBtn").unbind().click(cart.cartEdit);
	},
	addCart : function() {// 添加购物车
		$.mobile.activePage.find("#buyTypeHid").val(0);
	},
	goBuy : function() {// 直接购买
		$.mobile.activePage.find("#buyTypeHid").val(1);
	},
	confirmOrder : function() {

		var attr1Val = $.mobile.activePage.find(".attr1Val");
		if (attr1Val.attr("tit")!=''&&attr1Val.val() == '') {
			app.message("请选择" + attr1Val.attr("tit"))
			return;
		}

		var attr2Val = $.mobile.activePage.find(".attr2Val");
		if (attr2Val.attr("tit")!=''&&attr2Val.val() == '') {
			app.message("请选择" + attr2Val.attr("tit"))
			return;
		}

		cart.addCartAjax($(".cartForm"));

	},
	cartConfirmOrder : function() {// 购物车结算按钮

		// ${pageContext.request.contextPath}/order/confirmOrder

		var carChecks = new Array();
		$.mobile.activePage.find("input[name=carCheck]:checked").each(function(i, e) {
			carChecks.push($(e).val());
		});
		
		if(carChecks.length<=0){
			app.message("请选择结算商品");
			return;
		}
		
		$.mobile.changePage(app.servicerURL + "/order/confirmOrder?carCheck=" + carChecks.join(","), {
			transition : "slide"
		});

	},
	addCartAjax : function(form) {
		$.ajax({
			type : "get",
			url : app.servicerURL + "/buy/addCart",
			data : $(form).serialize(),
			cache : false,
			async : true,
			dataType : 'jsonp',
			success : addCartAjaxCallBack,
			error : function(data, df, d) {
				app.message("网络请求失败，请检查您的网络设置")
			}
		});

		function addCartAjaxCallBack(data) {

			if (data.code == -100) {
				$.mobile.activePage.find(".popupBox").popup("close");
				// alert($.mobile.activePage.find("#loginPopup").html())

				// $.mobile.activePage.find("#loginPopup").popup('open');
				// $.mobile.changePage(app.servicerURL + "/loginPage", {
				// transition : "slideup"
				// });

				setTimeout(function() {
					$.mobile.activePage.find("#loginPopup").popup('open', {
						transition : "pop"
					});
					// $.mobile.activePage.find("#loginPopup").css({'width':content_width*0.8});
				}, 500);

				// $.mobile.changePage($.mobile.activePage.find("#loginPopup"),
				// {
				// transition : "slideup"
				// });
			} else if (data.code == 1) {

				var type = $.mobile.activePage.find("#buyTypeHid").val();
				if (type == 0) {
					// $.mobile.changePage(app.servicerURL + "/buy/cart", {
					// transition : "slide"
					// });
					app.message(data.msg);
				} else {
					$.mobile.changePage(app.servicerURL + "/order/confirmOrder", {
						transition : "slide"
					});
				}
				// 关闭弹出窗口
				$.mobile.activePage.find(".popupBox").popup("close");
			} else {
				app.message(data.msg);
			}
		}

	},
	editCartAjax : function(form, obj) {

		$.ajax({
			type : "get",
			url : app.servicerURL + "/buy/editCart",
			data : $(form).serialize(),
			cache : false,
			async : true,
			dataType : 'jsonp',
			success : editCartAjaxCallBack,
			error : function(data, df, d) {
				app.message("网络请求失败，请检查您的网络设置")
			}
		});

		function editCartAjaxCallBack(data) {
			if (data.code == 1) {

				var cid = $(obj).parents(".popup").find("input[name='cid']");
				var attr1Val = $(obj).parents(".popup").find(".attr1Val");
				var attr2Val = $(obj).parents(".popup").find(".attr2Val");

				// 显示选择的属性
				var attr1ValShow = $(obj).parents(".popup").find(".attr1ValShow").text();
				var attr2ValShow = $(obj).parents(".popup").find(".attr2ValShow").text();

				var str = attr1Val.attr("tit") + ":" + attr1ValShow + ";" + attr2Val.attr("tit") + ":" + attr2ValShow

				$.mobile.activePage.find("#attrShowBtn_" + $(obj).attr("sku")).html(str);
				// 显示选择的属性 END

				// 删除重复sku
				// alert(cid.val()+attr1Val.val()+attr2Val.val())
				var _comm = $("#commodity_" + cid.val() + attr1Val.val() + attr2Val.val());
				if (_comm.length > 0) {
					$(_comm).hide(500, function() {/* alert("演示完毕！"); */
					});
				}
				// 删除重复sku END

				$("#commodity_" + $(obj).attr("sku") + " .c_price .s_1").html();
				$("#commodity_" + $(obj).attr("sku") + " .c_price .s_2").html();
				$("#commodity_" + $(obj).attr("sku") + " .c_price .s_3").html();
				$("#commodity_" + $(obj).attr("sku") + " .c_price .s_4").html();

			} else {
				app.message(data.msg)
			}
		}

	},
	delCart : function() {// 删除购物车商品

		var skus = new Array();
		$.mobile.activePage.find(".commodity input[name='carCheck']").each(function(i, e) {
			if ($(e).prop("checked") == true) {
				skus[i] = $(e).val();
			}
		});

		$.ajax({
			type : "get",
			url : app.servicerURL + "/buy/delCart",
			data : {
				skus : skus.join(",")
			},
			cache : false,
			async : true,
			dataType : 'jsonp',
			success : addDelCartCallBack,
			error : function(data, df, d) {
				app.message("网络请求失败，请检查您的网络设置")
			}
		});

		function addDelCartCallBack(data) {
			if (data.code == 1) {
				for (var i = 0; i < skus.length; i++) {
					var _comm = $.mobile.activePage.find("#commodity_" + skus[i]);
					if (_comm.length > 0) {

						// alert(_comm.parents(".shopBox").find(".commodity").length);

						if (_comm.siblings(".commodity").length > 1) {
							$(_comm).hide(600, function() {
								$(this).remove();
							});
						} else {
							_comm.parents(".shopBox").hide(600, function() {
								_comm.parents(".shopBox").remove();
							});
						}
					}
				}
			}
		}
	},
	inventoryShow : function() {// 属性切换库存显示
		var attr1Val = $.mobile.activePage.find(".inventoryShowBox .attr1Val").val();
		var attr2Val = $.mobile.activePage.find(".inventoryShowBox .attr2Val").val();

		var inv = $.mobile.activePage.find("input[name='_inventory_" + attr1Val + "_" + attr2Val + "']");

		if (inv.length > 0) {
			$.mobile.activePage.find(".inventoryShowBox .inventoryNum").html(inv.val());
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
			$.mobile.activePage.find(".commodity input[name='carCheck']").each(function() {
				$(this).prop("checked", flag);
			});

			$.mobile.activePage.find(".check input[name='carShopCheck']").each(function() {
				$(this).prop("checked", flag);
			});
		}
	},
	checkInventoryNum : function(num) {// 商品库存前台检测

		if (!$.isNumeric(num) || num <= 0)
			return 1;

		var attr1Val = $.mobile.activePage.find(".inventoryShowBox .attr1Val").val();
		var attr2Val = $.mobile.activePage.find(".inventoryShowBox .attr2Val").val();

		var inv = $.mobile.activePage.find(".inventoryShowBox input[name='_inventory_" + attr1Val + "_" + attr2Val + "']");

		if (inv.length > 0) {
			if (num > parseInt(inv.val())) {
				return parseInt(inv.val());
			}
		}
		return num;
	},
	getCurrentInventory : function() {// 获取选择属性后库存数

	},
	cartEdit : function() {// 编辑购物车
		if ($.mobile.activePage.find("#cartEditBtn").text() == '编辑') {
			$.mobile.activePage.find(".cartEdit").attr("style", "display:inline-block");
			$.mobile.activePage.find(".cartNormal").attr("style", "display:none");
			$.mobile.activePage.find("#cartEditBtn").text("完成");

			$.mobile.activePage.find(".c_infobox").attr("style", "display:none");
			$.mobile.activePage.find(".c_infoboxEdit").attr("style", "display:inline-block");

		} else if ($.mobile.activePage.find("#cartEditBtn").text() == '完成') {
			$.mobile.activePage.find(".cartEdit").attr("style", "display:none");
			$.mobile.activePage.find(".cartNormal").attr("style", "display:inline-block");
			$.mobile.activePage.find("#cartEditBtn").text("编辑");

			$.mobile.activePage.find(".c_infobox").attr("style", "display:inline-block");
			$(".c_infoboxEdit").attr("style", "display:none");

			cart.cartEditComplete();
		}
	},
	cartEditComplete : function() {// 购物车编辑完成

	},
	confirmCommodityBtn : function() {// 编辑购物车选择商品属性

		var cid = $(this).parents(".popup").find("input[name='cid']");
		var attr1Val = $(this).parents(".popup").find(".attr1Val");
		if (attr1Val.val() == '') {
			app.message("请选择" + attr1Val.attr("tit"))
			return;
		}

		var attr2Val = $(this).parents(".popup").find(".attr2Val");
		if (attr2Val.val() == '') {
			app.message("请选择" + attr2Val.attr("tit"))
			return;
		}

		// 提交修改
		cart.editCartAjax($(this).parents(".cartForm"), this);

		$.mobile.activePage.find(".popupBox").popup("close");

	}
}
