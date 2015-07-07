var shop = {
	initialize : function() {
		//店铺tags切换
		$(".shop .shopTag a").click(function() {
			$("#loading").show();
			$(this).addClass("on").siblings().removeClass('on');
			$("#page" + $(this).attr("i")).siblings(".tags").addClass("none").end().removeClass('none');
			$("#loading").hide();
		});
		
		
		// 加载店铺收藏事件
		$("#sellerListPage .boxList .box .p2 .a2").click(function() {
			
			shop.collectShop($(this), $(this).attr("sid"));
		});
		
	},
	collectShop : function(obj, sid) {
		
		if(sid==0||!sid)
			return
		
		$.ajax({
			type : "get",
			url : app.servicerURL + "user/collectShop",
			data : {
				sid : sid
			},
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : collectShopCallBack,
			error : function(data, df, d) {
				app.message("数据加载失败")
			}
		});

		function collectShopCallBack(data) {
			if (data && data.code == -1) {
				$.mobile.changePage("#loginPage", "slideup");
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

shop.initialize();

$(document).ready(function() {

});