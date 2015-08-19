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
	myScroll : {},
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
		smap.initialize();
	},
	loadIScroll : function() {// 加载局部滚动
		
		var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset;
		
		
		function pullDownAction() {
			setTimeout(function() {
				if (app.myScroll) {
					app.myScroll.refresh();
				}
			}, 300);
		}	
		
		
		function pullUpAction() {
			setTimeout(function() {
			
				if (location.href.indexOf("shop/list") > -1){
					sellerList.pullUpAction();
				}
				
				if (app.myScroll) {
					app.myScroll.refresh();
				}
			}, 300);
		}
		
		function loaded() {
			
			pullDownEl = $.mobile.activePage.find('#pullDown')[0];
			if (pullDownEl) {
				pullDownOffset = pullDownEl.offsetHeight;
			} else {
				pullDownOffset = 0;
			}
			pullUpEl = $.mobile.activePage.find('#pullUp')[0];
			if (pullUpEl) {
				pullUpOffset = pullUpEl.offsetHeight;
			} else {
				pullUpOffset = 0;
			}
			
			
			app.myScroll = new IScroll($.mobile.activePage
					.find(".wrapper")[0], {
				scrollbarClass : 'myScrollbar', /* 重要样式 */
				useTransition : true, /* 此属性不知用意，本人从true改为false */
				checkDOMChanges : true,
				mouseWheel : true,
				preventDefault : false,
				vScrollbar : false,
				scrollbars : false,
				fadeScrollbars: true,
				probeType : 1,
				startY : -pullDownOffset
			});		
			
			 //Event: scroll
			app.myScroll.on('scroll', function(){
					var length = 2;
		    	  	if (this.y >= length && pullDownEl && !pullDownEl.className.match('flip')) {
		    	  		pullDownEl.className = 'flip';
		    	  		pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手开始更新...';
		    	  		this.minScrollY = 0;
		    	  	} else if (this.y < length && pullDownEl && pullDownEl.className.match('flip')) {
		    	  		pullDownEl.className = '';
		    	  		pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
		    	  		this.minScrollY = -pullDownOffset;
		    	  	}else if (this.y <= (this.maxScrollY - length) && pullUpEl && !pullUpEl.className.match('flip')) {
		    	  		pullUpEl.className = 'flip';
		    	  		pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
		    	  		this.maxScrollY = this.maxScrollY;
		    	  	} else if (this.y > (this.maxScrollY +length) && pullUpEl && pullUpEl.className.match('flip')) {
		    	  		pullUpEl.className = '';
		    	  		pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载更多...';
		    	  		this.maxScrollY = pullUpOffset;
		    	  	}
		     });
			
		      //Event: scrollEnd
			app.myScroll.on("scrollEnd", function() {
		          if (pullDownEl && pullDownEl.className.match('flip')){
		        	  	pullDownEl.className = 'loading';
		        	  	pullDownEl.querySelector('.pullDownLabel').innerHTML = '努力加载中...';                
		        	  	// Execute custom function (ajax call?)
			        		  pullDownAction();    
		          }
		          else if (pullUpEl && pullUpEl.className.match('flip')) {
		        	  pullUpEl.className = 'loading';
		        	  pullUpEl.querySelector('.pullUpLabel').innerHTML = '努力加载中...';    
		        	  // Execute custom function (ajax call?)
		        		  pullUpAction();    
		          }
		      });

		      //Event: refresh
			app.myScroll.on("refresh", function() {
		           if (pullDownEl  && pullDownEl.className.match('loading')) {
		                  pullDownEl.className = '';
		                  pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
		                  //alert(this.options.startY);
		                  this.scrollTo(0,this.options.startY,0);
		           } else if (pullUpEl && pullUpEl.className.match('loading')) {
		                  pullUpEl.className = '';
		                  pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载更多...';
		                 this.scrollTo(0,this.maxScrollY,0);
		           }
		           
		      });
		      
			setTimeout(function () { $.mobile.activePage
				.find(".wrapper")[0].style.left = '0'; }, 500);
		}
		
		if ($.mobile.activePage.find(".wrapper").length > 0) {
			loaded();
		}
		
		
		//if (location.href.indexOf("shop/list") <= -1) {
			//if ($.mobile.activePage.find(".wrapper").length > 0) {
				//var myscroll = new IScroll($.mobile.activePage.find(".wrapper")[0], {
					//scrollbarClass : 'myScrollbar',
					//mouseWheel : true,
					//preventDefault : false
				// click : true
				//});
				
				// myscroll.refresh();
			//}
		//}
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
