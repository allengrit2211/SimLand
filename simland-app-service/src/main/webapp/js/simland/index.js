var index = {
	initialize : function() {
		
		// 店铺搜索按钮
		$.mobile.activePage.find("#searchShopBtn").unbind().click(index.searchShop);
		// 主营类别按钮
		$.mobile.activePage.find("#searchCategoryBtn").unbind().click(index.searchCategoryBtn);
		// 主营类别获得焦点
		$.mobile.activePage.find("#searchCategory").unbind().focus(index.searchCategoryFocus);
		// 主营类别失去焦点
		$.mobile.activePage.find("#searchCategory").unbind().blur(index.searchCategoryBlur);
		// 历史记载
		index.loadHistoryList(3);
		
		$.mobile.activePage.find("#searchShop").unbind().bind("blur", function(event, ui) {
			if($(this).val()!=''){
				index.searchShop();
			}
		});
		
		$.mobile.activePage.find("#searchCategory").unbind().bind("change", function(event, ui) {
			if($(this).val()!=''){
				index.searchCategoryBtn();
			}
		});		
		
	},
	loadHistoryList : function(n){
		
		$.mobile.activePage.find(".historyList ul").html("");
		
		for (var i = 1; i <= n; i++) {
			if((localStorage.getItem('k' + i))!=null&&(localStorage.getItem('k' + i))!='null')
				$.mobile.activePage.find(".historyList ul").append("<li><a class='a1' href='#sellerListPage'>"+(localStorage.getItem('k' + i))+"</a> <a index='"+i+"' class='a2' href='#'>X</a></li>");
		}
		
		$.mobile.activePage.find(".historyList .a1").unbind().click(index.historySeach);
		// 历史记录清除
		$.mobile.activePage.find(".historyList .a2").unbind().click(index.historyClear);
		
	},
	searchCategoryFocus : function() {// 搜索主营类别获得焦点
		$.mobile.activePage.find(".historyList").show();
	},
	searchCategoryBlur : function() {// 搜索主营类别失去焦点
		setTimeout(function() {
			$.mobile.activePage.find(".historyList").hide();
		}, 100)
	},
	searchShop : function() {// 搜索店铺跳转
		$.mobile.activePage.find("#sellerListPage_stype").val(0);
		$.mobile.activePage.find("#sellerListPage_currentPage").val(1);
		$.mobile.activePage.find("#sellerListPage_form").submit();
	},
	searchCategoryBtn : function() {// 主营类别搜索
		$.mobile.activePage.find("#sellerListPage_currentPage").val(1);
		$.mobile.activePage.find("#sellerListPage_stype").val(1);
		index.setHistoryList($.mobile.activePage.find("#searchCategory").val(), 0);
		$.mobile.activePage.find("#sellerListPage_form").submit();
	},
	setHistoryList : function(key, type) {// 搜索历史
	
		var n = 3;
		
		if (key == null || $.trim(key) == '')
			return;
		
		var flag = true;
		for (var i = 1; i <= n; i++) {
			if (localStorage.getItem("k" + i) == key) {
				flag = false;
			}
		}

		if (flag) {
			
			for (var i = n; i >= 1; i--) {
				if (i == 1) {
					localStorage.setItem(("k" + i), key);
				} else {
					localStorage.setItem(("k" + i), localStorage.getItem("k"
							+ (i - 1)));
				}
			}
			
			index.loadHistoryList(3);
		}
	},
	historySeach : function(){
		$.mobile.activePage.find("#sellerListPage_stype").val(1);
		$.mobile.activePage.find("#searchCategory").val($(this).text());
		$.mobile.activePage.find("#sellerListPage_form").submit();
	},
	historyClear :function(){
		localStorage.removeItem("k"+$(this).attr("index"))
		index.loadHistoryList(3);
	}
}

