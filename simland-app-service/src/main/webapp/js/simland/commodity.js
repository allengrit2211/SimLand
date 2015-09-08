var commodity = {
	initialize : function() {
		
		
		//店铺tags切换
		$.mobile.activePage.find(".commTags a").unbind().click(function() {
			$(this).addClass("on").siblings().removeClass('on');
			$.mobile.activePage.find("#page" + $(this).attr("i")).siblings(".c_infoBox_tag").removeClass("on").end().addClass('on');
			app.myScroll.refresh();
		});		
		
		
		$.mobile.activePage.find(".inventoryNum").text($.mobile.activePage.find("#inventoryNum").val());
		
		$.mobile.activePage.find(".c_listMenu").unbind().click(function() {
			
			var menu = $.mobile.activePage.find(".listMenu");
			if(menu.is(":hidden")){
				menu.show();
				setTimeout(function(){
					menu.hide();
				},1500);
			}else{
				menu.hide();
			}
		});		
		
	},
	collectEvent : function() {//收藏事件加载
		$.mobile.activePage.find(".shop .commodityList .s3").unbind().click(function(){
			commodity.collectCommodity($(this))
			return false;
		});
	},
	collectCommodity : function(obj) {//收藏商品
		$.ajax({
			type : "get",
			url : app.servicerURL + "/user/collectCommodity",
			data : {
				cid : $(obj).attr("cid")
			},
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : collectCommodityCallBack,
			error : function(data, df, d) {
				app.message("网络请求失败，请检查您的网络设置")
			}
		});

		function collectCommodityCallBack(data) {
			if (data && data.code == -1) {
				$.mobile.changePage("#loginPage", "slideup");
			} else {
				if (data.code == 1) {
					app.message(data.msg)
					
					
				} else {
					app.message(data.msg)
				}
			}
		}
	}
}

