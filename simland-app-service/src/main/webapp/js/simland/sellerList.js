var sellerList = {
	totalPage : 0,// 总页数,
	initialize : function() {

		// 总页数
		sellerList.totalPage = $.mobile.activePage.find("#totalPage").val();

		// 商家星级排序
		$("#sellerListPage_score_btn").unbind().click(sellerList.scoreOrder);

		// 滚动翻页
		sellerList.scroll();

		// 页面初始数据加载
		// $(document).on("pageinit", initPage2);

		// function initPage2(event) {
		// $(document).off('pageinit', initPage2);
		$("#sellerListPage_currentPage").val(1);
		// }

		// 加载店铺收藏事件
		shop.collectShopEvent();

		$("#sellerListPage_search_k").unbind().bind("change",
				function(event, ui) {
					$("#sellerListPage_form").submit();
				});

		sellerList.textSeach();
	},
	pullUpAction : function(){
		
		var currentPage = parseInt($("#sellerListPage_currentPage").val()) + 1;

		if (currentPage > sellerList.totalPage) {
			return;
		}

		sellerList.selerListPageShow({
			currentPage : currentPage
		});		
	},
	scroll : function() {// 商家列表页面，滚动分页
		/*
		var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset;

		function pullDownAction() {
			setTimeout(function() { // <-- Simulate network congestion, remove
				// setTimeout from production!
				
				if (sellerList.myScroll) {
					sellerList.myScroll.refresh(); // Remember to refresh when contents are
					// loaded (ie: on ajax completion)
				}
			}, 300); // <-- Simulate network congestion, remove setTimeout
			// from production!
		}

		function pullUpAction() {
			setTimeout(function() { // <-- Simulate network congestion, remove
				// setTimeout from production!
				
				var currentPage = parseInt($("#sellerListPage_currentPage").val()) + 1;

				if (currentPage > sellerList.totalPage) {
					return;
				}

				sellerList.selerListPageShow({
					currentPage : currentPage
				});		
				
				if (sellerList.myScroll) {
					sellerList.myScroll.refresh(); // Remember to refresh when contents are
					// loaded (ie: on ajax completion)
				}
			}, 300); // <-- Simulate network congestion, remove setTimeout
			// from production!
		}

		setTimeout(loaded, 100);


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

			
			sellerList.myScroll = new IScroll($.mobile.activePage
					.find(".wrapper")[0], {
				// scrollbarClass : 'myScrollbar', 
				useTransition : false, 
				checkDOMChanges : true,
				mouseWheel : true,
				//preventDefault : false,
				scrollbars : true,
				fadeScrollbars: true,
				probeType : 1,
				startY : -pullDownOffset
			});

			var isScrolling = false;
			// Event: scrollStart
			sellerList.myScroll.on("scrollStart", function() {
				if (this.y == this.startY) {
					isScrolling = true;
				}
			});
			
			 //Event: scroll
			sellerList.myScroll.on('scroll', function(){
				
					var length = 2;
				
				
		    	  	if (this.y >= length && !pullDownEl.className.match('flip')) {
		    	  		pullDownEl.className = 'flip';
		    	  		pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Release to refresh';
		              //this.minScrollY = 0;
		    	  	} else if (this.y < length && pullDownEl.className.match('flip')) {
		    	  		pullDownEl.className = '';
		    	  		pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh';
		              //this.minScrollY = -pullDownOffset;
		    	  	}else if (this.y <= (this.maxScrollY - length) && !pullUpEl.className.match('flip')) {
		    	  		pullUpEl.className = 'flip';
		    	  		pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Release to refresh';
		    	  		//this.maxScrollY = this.maxScrollY;
		    	  		this.maxScrollY = this.maxScrollY;
		    	  	} else if (this.y > (this.maxScrollY +length) && pullUpEl.className.match('flip')) {
		    	  		pullUpEl.className = '';
		    	  		pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Pull up to load more';
		              //this.maxScrollY = pullUpOffset;
		    	  		this.maxScrollY = pullUpOffset;
		    	  	}
		     });
			
		      //Event: scrollEnd
			sellerList.myScroll.on("scrollEnd", function() {
		    	  if (pullDownEl && !pullDownEl.className.match('flip') && this.y > this.options.startY) {
		    		  this.scrollTo(0, this.options.startY,800);
		    	  }
		          else if (pullDownEl && pullDownEl.className.match('flip')){
		        	  	pullDownEl.className = 'loading';
		        	  	pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Loading...';                
		        	  	// Execute custom function (ajax call?)
		        	  		pullDownAction();
		          }
		          else if (pullUpEl && pullUpEl.className.match('flip')) {
		        	  pullUpEl.className = 'loading';
		        	  pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Loading...';    
		        	  // Execute custom function (ajax call?)
		        	  if (isScrolling) {            
		        		  pullUpAction();    
		        	  }
		          }
		          isScrolling = false;
		      });

		      //Event: refresh
			sellerList.myScroll.on("refresh", function() {
		           if (pullDownEl  && pullDownEl.className.match('loading')) {
		                  pullDownEl.className = '';
		                  pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh';
		                  this.scrollTo(0,this.options.startY,0);
		           } else if (pullUpEl && pullUpEl.className.match('loading')) {
		                  pullUpEl.className = '';
		                  pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Pull up to load more';
		                  this.scrollTo(0,this.maxScrollY,0);
		           }
		           
		      });
		      
		      setTimeout(function () { $.mobile.activePage
					.find(".wrapper")[0].style.left = '0'; }, 500);
		      
		      
		      //sellerList.myScroll.on('scrollEnd', updatePosition);


			function updatePosition() {
				if (this.y - this.maxScrollY == 0) {
					var currentPage = parseInt($("#sellerListPage_currentPage")
							.val()) + 1;

					if (currentPage > sellerList.totalPage) {
						return;
					}

					sellerList.selerListPageShow({
						currentPage : currentPage
					});
				}
			}


		}
		*/
	},
	/***************************************************************************
	 * 
	 * @param option {
	 *            k 关键字 currentPage 当前页数 reset 加载类型 0 重新加载列表 1累计加载列表 sort 排序字段
	 *            sortType 排序类型 0 正序 1 倒序 }
	 */
	selerListPageShow : function(option) {// 商家列表显示

		$("#sellerListPage_currentPage").val(option.currentPage);

		$.ajax({
			type : "get",
			url : app.servicerURL + "/shop/listAjax",
			data : $("#sellerListPage_form").serialize(),
			cache : false,
			async : false,
			success : selerListPageShowCallBack,
			error : function(data, df, d) {
				app.message("网络请求失败，请检查您的网络设置")
			}
		});

		// 回掉
		function selerListPageShowCallBack(data) {

			$("#sellerListPage .boxList").append(data);
			sellerList.textSeach();

			// 加载店铺收藏事件
			shop.collectShopEvent();

			if (option.currentPage >= sellerList.totalPage) {
				$("#pullUp").html("已经到达最后一页...");
				return;
			}

		}

	},
	textSeach : function() {
		if ($("#sellerListPage_stype").val() == 1) {
			$("#sellerListPage .boxList .box .p1").textSearch(
					$("#sellerListPage_search_k").val(), {
						markColor : "#44BBAB"
					});
		} else {
			$("#sellerListPage .boxList .box .a0").textSearch(
					$("#sellerListPage_search_k").val(), {
						markColor : "#44BBAB"
					});
		}
	},
	scoreOrder : function() {// 商家星级排序
		$("#sellerListPage_score").val("score");
		$("#sellerListPage_scoreType").val(1);
		$("#sellerListPage_form").submit();
	}
}
