//Initialize function

// jQuery.mobile.ajaxEnabled = false;
// jQuery.mobile.allowCrossDomainPages = true;

document.addEventListener('touchmove', function(e) {
	e.preventDefault();
}, false);

jQuery(function() {
	if ($("#message").length <= 0) {
		$("body").append("<div id=\"message\" style=\"display: none;\"></div>");
	}
});

var app = {
	servicerURL : contextPath,
	initialize : function() {
		
		$.mobile.allowCrossDomainPages = true;
		
		app.loadScript();
	},
	loadScript : function() {// 加载事件脚本
		// $.mobile.activePage.is("#indexPage")

		index.initialize();
		sellerList.initialize();
		commodity.initialize();
		cart.initialize();
		shop.initialize();
		user.initialize();
		order.initialize();
	},
	loadIScroll : function() {// 加载局部滚动
		if (location.href.indexOf("shop/list") <= -1) {
			if ($.mobile.activePage.find(".wrapper").length > 0) {
				var myscroll = new IScroll($.mobile.activePage.find(".wrapper")[0], {
					scrollbarClass : 'myScrollbar',
					mouseWheel : true,
					preventDefault : false
				// click : true
				});
				// myscroll.refresh();
			}
		}
	},
	message : function(msg) {// 信息提示
		$("#message").text(msg);
		$("#message").show();
		setTimeout(function() {
			$('#message').fadeOut(500, function() {
				$("#message").html("");
				$('#message').hide();
			});
		}, 1500);
	}
}

$(document).ready(app.initialize);

$(document).on("pageinit", function(event) {
	setTimeout(app.loadIScroll, 500);
});

$(document).on("pagebeforehide", function(event) {
	app.loadScript();
});
