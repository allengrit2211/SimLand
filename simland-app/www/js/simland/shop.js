

var shop = {
	id : 0,//店铺id
	initialize : function() {
		$(document).on("pageshow", "#shop1Page", shop.pageLoad);
		$(document).on("pageshow", "#shop2Page", shop.pageLoad);
		$(document).on("pageshow", "#shop3Page", shop.pageLoad);
		$(document).on("pageshow", "#Vip1Page", shop.pageLoad);
	},
	pageLoad : function(event, ui){//进入店铺
		if(shop.id<=0)
			return;
		
		shop.showShop(shop.id);
	},
	showShop : function(id){
		$.ajax({
			type : "get",
			url : app.servicerURL + "shop/showShop",
			data : {id:id},
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : showShopCallBack,
			error : function(data, df, d) {
				app.message("读取店铺信息失败...")
			}
		});
		
		function showShopCallBack(data){
			
			if(data){
				$(".shop .shop_top .b_logo img").attr("src",app.servicerURL + data.clogo);
				$(".shop .shop_top .b_info h3").html(data.cname);
				var span = $(".shop .shop_top .b_info span");
				$(span).removeClass();
				$(span).addClass("star star"+data.score+"");
			}
			
		}
	}
	
}

shop.initialize();