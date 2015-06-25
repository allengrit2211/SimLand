var shop = {
	id : 0,// 店铺id
	initialize : function() {

		$(document).on("pagebeforeshow", "#shop1Page", function(event,data){
			shop.pageLoad(0)
		});
		$(document).on("pagebeforeshow", "#shop2Page", function(){
			shop.pageLoad(1)
		});
		$(document).on("pagebeforeshow", "#shop3Page", function(){
			shop.pageLoad(2)
		});
		$(document).on("pagebeforeshow", "#Vip1Page", function(){
			shop.pageLoad(3)
		});
		//店铺信息页面
		$(document).on("pagebeforeshow", "#shopInfoPage", function(){
			shop.showShop(shop.id);
		});
		
		//店铺信息页面
		$(document).on("pagebeforeshow", "#contactPage", function(){
			shop.showShop(shop.id);
			
			// 百度地图API功能
			var map1 = new BMap.Map("contactAllmap");
			map1.centerAndZoom(new BMap.Point(114.133149, 22.578115), 13);
			var json_data = [ [ 114.133149, 22.578115 ]];
			var pointArray = new Array();
			for (var i = 0; i < json_data.length; i++) {
				var marker = new BMap.Marker(new BMap.Point(json_data[i][0],
						json_data[i][1])); // 创建点
				map1.addOverlay(marker); //增加点
				pointArray[i] = new BMap.Point(json_data[i][0], json_data[i][1]);
				marker.addEventListener("click", attribute);
			}
			
			
		});
	},
	shopInfo : function(){
		if (shop.id <= 0)
			return;
		
	},
	pageLoad : function(type) {// 进入店铺
		
		$(".shop .commodityList").html("暂无商品");
		
		if (shop.id <= 0)
			return;

		shop.showShop(shop.id);

		$.ajax({
			type : "get",
			url : app.servicerURL + "shop/commodityList",
			data : {
				sid : shop.id,// 店铺ID
				type : type
			// 新品
			},
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : shop1LoadCallBack,
			error : function(data, df, d) {
				app.message("读取店铺信息失败...")
			}
		});

		function shop1LoadCallBack(data) {
			if (data.code == 1 && data.list.length > 0) {
				$(".shop .commodityList").html("");
				var htmlStr = "";
				for (var i = 0; i < data.list.length; i++) {
					htmlStr += "<div class='commodityBox'>";
					htmlStr += "	<a data-transition='none' href='#Product1Page'>";
					htmlStr += "	<span class='s3'>" + data.list[i].collectNum
							+ "</span>";
					htmlStr += "	<img width='126' alt='' src='"
							+ app.servicerURL + data.list[i].img + "'>";
					htmlStr += "	<span class='s4'>" + data.list[i].name
							+ "</span>";
					htmlStr += "	</a>";
					htmlStr += "	<div class='price'>";
					htmlStr += "		<span class='s1'>￥</span>";
					htmlStr += "		<span class='s2'>" + data.list[i].marketPrice
							+ "</span>";
					htmlStr += "	</div>";
					htmlStr += "</div>";
				}
				$(".shop .commodityList").html(htmlStr)
			}
		}
	},
	showShop : function(id) {
		$.ajax({
			type : "get",
			url : app.servicerURL + "shop/showShop",
			data : {
				id : id
			},
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : showShopCallBack,
			error : function(data, df, d) {
				app.message("读取店铺信息失败...")
			}
		});

		function showShopCallBack(data) {

			if (data) {
				$(".shop .shop_top .b_logo img").attr("src",
						app.servicerURL + data.clogo);
				$(".shop .shop_top .b_info h3").html(data.cname);
				var span = $(".shop .shop_top .b_info span");
				$(span).removeClass();
				$(span).addClass("star star" + data.score + "");
				
				$("#shopInfoPage .info .s1").html(data.cname);
				$("#shopInfoPage .info .s2").html(data.caddress);
				$("#shopInfoPage .info .s3").html(data.bmodel);
				$("#shopInfoPage .info .s4").html(data.brand);
				$("#shopInfoPage .info .s5").html(data.salesArea);
				$("#shopInfoPage .info .s6").html(data.clientrGroup);
				$("#shopInfoPage .info .s7").html(data.regAddress);
				$("#shopInfoPage .info .s8").html(data.corporate);
				$("#shopInfoPage .info .s9").html(data.people);
			
				$("#contactPage .contactBox .s1").html(data.contactPeople);
				$("#contactPage .contactBox .s2").html(data.contact);
				
			}

		}
	}

}

shop.initialize();