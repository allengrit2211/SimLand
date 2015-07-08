var commodity = {
	initialize : function() {
		
	},
	collectEvent : function() {//收藏事件加载
		$(".shop .commodityList .s3").click(function(){
			commodity.collectCommodity($(this))
			return false;
		});
	},
	collectCommodity : function(obj) {//收藏商品
		$.ajax({
			type : "get",
			url : app.servicerURL + "user/collectCommodity",
			data : {
				cid : $(obj).attr("cid")
			},
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : collectCommodityCallBack,
			error : function(data, df, d) {
				app.message("数据加载失败")
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

commodity.initialize();