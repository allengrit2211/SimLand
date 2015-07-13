//Initialize function

// jQuery.mobile.ajaxEnabled = false;
// jQuery.mobile.allowCrossDomainPages = true;

document.addEventListener('touchmove', function(e) {
	e.preventDefault();
}, false);

$(document).on("pageinit", function(event) {
	
	setTimeout(loadIScroll, 500);
	
});


var init = function() {

	index.initialize();
	
	if($.mobile.activePage.is("#sellerListPage")){
		sellerList.initialize();
	}
	if($.mobile.activePage.is("#shop1Page")){
		shop.initialize();
	}
	if($.mobile.activePage.is("#userCenterPage")){
		user.initialize();
	}
	if($.mobile.activePage.is("#cartPage")){
		cart.initialize();
	}
	
};
$(document).ready(init);

jQuery(function() {
	if ($("#message").length <= 0) {
		$("body").append("<div id=\"message\" style=\"display: none;\"></div>");
	}
});

function loadIScroll(){//加载局部滚动
	if (location.href.indexOf("shop/list") <= -1) {
		if ($.mobile.activePage.find(".wrapper").length > 0) {
			var myscroll = new IScroll($.mobile.activePage.find(".wrapper")[0], {
				scrollbarClass : 'myScrollbar',
				mouseWheel : true,
				preventDefault:false
				// click : true
			});
			// myscroll.refresh();
		}	
	}
}



/*
$('a').bind('tap', function() {
	$.mobile.loading("show")
	setTimeout(function() {
		$.mobile.loading("hide");
	}, 300)
});	
*/




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


/*
 * $(document).on("pagebeforecreate", function(event) { $.mobile.loading("show")
 * setTimeout(function() { $.mobile.loading("hide"); }, 200) });
 */