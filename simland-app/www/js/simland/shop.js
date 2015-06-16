var shop = {
	initialize : function() {
		// 店铺页面加载事件
		$(document).on("pageshow", "#sellerListPage", shop.selerListPageShow);
		//店铺搜索按钮
		$("#searchShopBtn").click(shop.searchShop);
		//主营类别按钮
		$("#searchCategoryBtn").click(shop.searchCategoryBtn);
		//主营类别获得焦点
		$("#searchCategory").focus(shop.searchCategoryFocus);
		//主营类别失去焦点
		$("#searchCategory").blur(shop.searchCategoryBlur);
		
	},
	searchCategoryFocus : function() {
		$(".historyList").show();
	},
	searchCategoryBlur : function() {
		setTimeout(function() {
			$(".historyList").hide();
		}, 100)
	},	
	getShopList : function(){// 获取商家列表
		
	},
	searchShop : function(){// 搜索店铺跳转
		$.mobile.changePage("#sellerListPage", "slideup");
	},
	searchCategoryBtn : function() {//主营类别搜索
		$.mobile.changePage("#sellerListPage", "slideup");
	},
	selerListPageShow : function() {//商家列表显示
		var k = $("#searchShop").val();

		if (k == '')
			return;

		$.ajax({
			type : "get",
			url : AppServicerURL + "/shop/list",
			data : {
				"k" : k
			},
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : function(data) {
				var htmlStr = "";
				if(data!=null&&data.length!=0){
					for(var i=0;i<data.length;i++){
						htmlStr += shop.selerListHtmlShow(data[i]);
					}
				}
				$("#sellerListPage .boxList").html(htmlStr);
			},
			error : function(data, df, d) {

			}
		});
	},
	selerListHtmlShow : function(jsonData) {//jsonData 商家数据
		var html = "";
		html += "<div class='box'>";
		html += "<h3>";
		html += "<a href='#shop1Page' class='ui-link'>"+jsonData.cname+"</a>";
		html += "<a href='#' class='a1 ui-link'>260M</a>";
		html += "</h3>";
		html += "<h6>"+jsonData.caddress+"</h6>";
		html += "<p class='p1'>";
		html += "<span>主营产品:</span><span>"+jsonData.engage+"</span>";
		html += "</p>";
		html += "<div class='line'></div>";
		html += "<p class='p2'>";
		html += "<span class='s1'>965</span> <span>件产品</span> | <span class='s2'>&nbsp;</span> <span class='s3'>236</span> <span>次</span>";
		html += "<span class='s4 star2'>&nbsp;</span>";
		html += "</p>";
		html += "</div>";
		return html;
	}
}


shop.initialize();
