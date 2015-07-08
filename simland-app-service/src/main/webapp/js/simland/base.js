//Initialize function

$(function() {
	$('a').bind('tap', function() {
		$.mobile.loading("show")
		setTimeout(function() {
			$.mobile.loading("hide");
		}, 300)
	});
	

});

$(document).on("pagebeforecreate", function(event) {
	$.mobile.loading("show")
	setTimeout(function() {
		$.mobile.loading("hide");
	}, 200)
});

var init = function() {
	jQuery(function() {
		if ($("#message").length <= 0) {
			$("body").append("<div id=\"message\" style=\"display: none;\"></div>");
		}

		if ($("#loading").length <= 0) {
			var str = "<div id=\"loading\" style=\"display: none;\"><img src=\"" + app.servicerURL + "/images/bg/loading.gif\"></div>";
			$("body").append(str);
		}

	});

	// $(function() {
	// $(document).ajaxStart(function() {
	// $("#loading").show();
	// setTimeout(function(){
	// $("#loading").hide();
	// },30000);
	// });
	// $(document).ajaxSuccess(function() {
	// $("#loading").hide();
	// });
	// });

	// jQuery(function() {
	// $('.slides2').slidesjs({
	// navigation : true,
	// start : 3,
	// play : {
	// auto : true
	// }
	// });
	//
	// $(".slides2").css("width", $(window).width());
	// });

	jQuery.mobile.ajaxEnabled = false;
	// $.mobile.loadingMessageTextVisible = true;
	// $.mobile.page.prototype.options.domCache = true;

	// 初始化绑定iScroll控件
	document.addEventListener('touchmove', function(e) {
		e.preventDefault();
	}, false);

	if (location.href.indexOf("shop/list") > -1) {

	} else {
		if ($("#wrapper").length > 0) {
			var myscroll = new IScroll("#wrapper", {
				scrollbarClass : 'myScrollbar',
				mouseWheel : true,
				click:true
			});
		}
	}

};
$(document).ready(init);
