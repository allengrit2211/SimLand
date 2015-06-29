var user = {
	loginFlag : true,
	initialize : function() {

		$("#loginBtn").click(user.login);

		// $(document).on("pageshow", "#userCenterPage", user.pageLoad);

		$(document).on("pagebeforeshow", "#userCenterCheckLoginPage", function(event) {
			setTimeout(user.isLogin(), 300);
			return;
		});

		//店铺收藏显示
		$(document).on("pagebeforeshow", "#myCollectShopPage", function() {
			user.collectShopShow();
		});
		
		//商品收藏显示
		$(document).on("pagebeforeshow", "#myCollectCommodityPage", function(event, data) {
			user.collectCommodityShow();
		});

	},
	pageLoad : function() {

	},
	login : function() {

		if (!user.loginFlag) {
			return;
		}

		user.loginFlag = false;

		setTimeout(function() {// 防止重复提交
			user.loginFlag = true;
		}, 3000);

		$.ajax({
			type : "get",
			url : app.servicerURL + "/user/login",
			data : $("#loginPage_form").serialize(),
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : loginCallBack,
			error : function(data, df, d) {
			}
		});

		function loginCallBack(data) {
			if (data.code == 2) {
				if (data.toUrl && data.toUrl != '') {
					$("#toUrl").val("");
					$.mobile.changePage("#" + data.toUrl, "slideup");
				} else {
					$.mobile.changePage("#userCenterPage", "slideup");
				}
			} else {
				app.message(data.msg)
			}
		}

		return false;
	},
	isLogin : function() {// 是否登录
		$.ajax({
			type : "get",
			url : app.servicerURL + "/user/isLogin",
			data : {},
			cache : false,
			async : true,
			dataType : 'jsonp',
			success : isLoginCallBack,
			error : function(data, df, d) {
			}
		});

		function isLoginCallBack(data) {
			if (data && data.code == 1) {
				$.mobile.changePage("#userCenterPage", "slideup");
			} else {
				$.mobile.changePage("#loginPage", "slideup");
			}

		}
	},
	collectShopShow : function() {// 显示收藏店铺
		$("#myCollectShopPage .collectList").html("");

		$.ajax({
			type : "get",
			url : app.servicerURL + "/user/collectShopShow",
			data : {},
			cache : false,
			async : true,
			dataType : 'jsonp',
			success : collectShopShowCB,
			error : function(data, df, d) {
			}
		});

		var htmlStr = "";

		function collectShopShowCB(data) {
			if (data && data.code == -1) {
				$.mobile.changePage("#loginPage", "slideup");
			} else {
				if (data.code == 1) {
					for (var i = 0; i < data.list.length; i++) {
						htmlStr += "<div class='collectBox'>";
						htmlStr += "<p class='p1'><a sid='" + data.list[i].sid + "' href='#shop1Page' class='toShop s1 ui-link'>" + data.list[i].cname + "</a>";
						htmlStr += "<span class='star star" + data.list[i].score + "'></span></p>";
						htmlStr += "<div class='clear'></div>";
						htmlStr += "<div class='line'></div>";
						htmlStr += "<div class='c_imgs'>";
						for (var y = 0; y < data.list[i].rclist.length; y++) {
							htmlStr += "<a href='#Product1Page' class='ui-link a1'><img alt='' src='" + app.servicerURL + data.list[i].rclist[y].img + "'></a>";
						}

						// htmlStr += "<a href='#Product1Page'
						// class='ui-link'><img alt=''
						// src='http://115.28.92.59:8089/simland-app-service/images/commodity/collect_r4_c6.jpg'></a>";
						// htmlStr += "<a href='#Product1Page'
						// class='ui-link'><img alt=''
						// src='http://115.28.92.59:8089/simland-app-service/images/commodity/collect_r4_c8.jpg'></a>";

						htmlStr += "</div>";
						htmlStr += "</div>";
					}
					$("#myCollectShopPage .collectList").html(htmlStr);
					shop.toShopEvent();
				} else {

				}
			}
		}
	},
	collectCommodityShow : function(){//商品收藏显示
		//$("#myCollectCommodityPage .commodity_list").html("");

		$.ajax({
			type : "get",
			url : app.servicerURL + "/user/collectCommodityShow",
			data : {},
			cache : false,
			async : true,
			dataType : 'jsonp',
			success : collectCommodityShowCB,
			error : function(data, df, d) {
			}
		});

		var htmlStr = "";
		
		function collectCommodityShowCB(data){
			if (data && data.code == -1) {
				$.mobile.changePage("#loginPage", "slideup");
			} else {
				if (data.code == 1) {
					for (var i = 0; i < data.list.length; i++) {
						htmlStr += "<div class='commodityBox'>";
						htmlStr += "<div class='img'>";
						htmlStr += "<a href='#Product1Page' class='ui-link a1'><img width='61' height='61' alt='' src='" + app.servicerURL + data.list[i].commodity.img + "'></a>";
						htmlStr += "</div>";
						htmlStr += "<div class='info'>";
						htmlStr += "<p class='p1'><a href='#Product1Page' class='ui-link'>"+data.list[i].commodity.name+"</a></p>";
						htmlStr += "<p class='p2'><span class='s1'>￥"+data.list[i].commodity.marketPrice+"</span><span>成交:</span><span class='s3'>562</span></p>";
						htmlStr += "</div>";
						htmlStr += "</div>";
					}
					$("#myCollectCommodityPage .commodity_list").html(htmlStr);
				} else {

				}
			}
		}
	},
	collectShopScroll : function(){
		
	}
	
	
	
}

user.initialize();