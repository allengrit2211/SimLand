var shop = {
	myScroll:{},
	initialize : function() {
		//店铺tags切换
		$.mobile.activePage.find(".shopTag a").unbind().click(function() {
			$("#loading").show();
			$(this).addClass("on").siblings().removeClass('on');
			$("#page" + $(this).attr("i")).siblings(".tags").addClass("none").end().removeClass('none');
			$("#loading").hide();
			app.myScroll.refresh();
		});
		
		
		// 加载店铺收藏事件
		shop.collectShopEvent();
		
		shop.loadSlide();

		shop.loadMoreBtn();//店铺下拉菜单
		
	},
	loadMoreBtn : function(){
		$.mobile.activePage.find(".moreImg").unbind().click(function() {
			
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
	loadSlide : function(){
		
		var wrapper1 = $.mobile.activePage.find(".wrapperRow")[0];
		
		if($(wrapper1).length>0){
			function loaded () {
				shop.myScroll = new IScroll(wrapper1, {
					scrollX: true,
					scrollY: false,
					momentum: false,
					snap: true,
					snapSpeed: 400,
					keyBindings: true
				});
			}
			setTimeout(function(){
				$(wrapper1).css("width",$(window).width()+"px");
				$.mobile.activePage.find(".scrollerRow .slide").css("width",$(window).width()+"px");
				$.mobile.activePage.find(".scrollerRow").css("width",($.mobile.activePage.find(".scrollerRow .slide").length*$.mobile.activePage.find(".scrollerRow .slide:eq(0)").width())+"px")
				loaded();
			},200);
		}

	},
	collectShopEvent : function(){// 加载店铺收藏事件
		$.mobile.activePage.find(".listMenu li:first a").unbind().click(function() {
			shop.collectShop($(this), $(this).attr("sid"));
		});	
		
		//$("#sellerListPage .boxList .box .p2 .a2").unbind()
	},
	collectShop : function(obj, sid) {
		
		$.mobile.activePage.find(".listMenu").hide();
		
		if(sid==0||!sid)
			return
		
		$.ajax({
			type : "get",
			url : app.servicerURL + "/user/collectShop",
			data : {sid : sid,_stype:"ajax"},
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : collectShopCallBack,
			error : function(data, df, d) {
				app.message("网络请求失败，请检查您的网络设置")
			}
		});

		function collectShopCallBack(data) {
			if (data && data.code == -100) {
				$.mobile.changePage(app.servicerURL +"/loginPage", "slideup");
			} else {
				if (data.code == 1) {
					app.message(data.msg)
					if($(obj).find(".s3")){
						$(obj).find(".s3").text(parseInt($(obj).find(".s3").text()) + 1);
					}
					
				} else {
					app.message(data.msg)
				}
			}
		}
	}
}


