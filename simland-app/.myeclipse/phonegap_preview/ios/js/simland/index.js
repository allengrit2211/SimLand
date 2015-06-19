var index = {
	initialize : function() {
		
		// 店铺搜索按钮
		$("#searchShopBtn").click(index.searchShop);
		// 主营类别按钮
		$("#searchCategoryBtn").click(index.searchCategoryBtn);
		// 主营类别获得焦点
		$("#searchCategory").focus(index.searchCategoryFocus);
		// 主营类别失去焦点
		$("#searchCategory").blur(index.searchCategoryBlur);
		
	},
	searchCategoryFocus : function() {// 搜索主营类别获得焦点
		$(".historyList").show();
	},
	searchCategoryBlur : function() {// 搜索主营类别失去焦点
		setTimeout(function() {
			$(".historyList").hide();
		}, 100)
	},
	searchShop : function() {// 搜索店铺跳转
		$.mobile.changePage("#sellerListPage", "slideup");
	},
	searchCategoryBtn : function() {// 主营类别搜索
		$.mobile.changePage("#sellerListPage", "slideup");
	}
}


index.initialize();