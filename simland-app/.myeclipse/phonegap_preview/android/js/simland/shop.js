var shop = {
	currentPage : 1,// 当前页面计数
	totalPage : 1,// 总页数
	initialize : function() {
		// 店铺页面加载事件
		$(document).on("pageshow", "#sellerListPage", function() {
			// 商家列表
			shop.selerListPageShow(1, 0);
			shop.currentPage = 1;

		});
		// 店铺搜索按钮
		$("#searchShopBtn").click(shop.searchShop);
		// 主营类别按钮
		$("#searchCategoryBtn").click(shop.searchCategoryBtn);
		// 主营类别获得焦点
		$("#searchCategory").focus(shop.searchCategoryFocus);
		// 主营类别失去焦点
		$("#searchCategory").blur(shop.searchCategoryBlur);
		// 滚动翻页
		shop.scroll();

	},
	scroll : function() {// 商家列表页面，滚动分页

		var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset, generatedCount = 0;

		/**
		 * 下拉刷新 （自定义实现此方法） myScroll.refresh(); // 数据加载完成后，调用界面更新方法
		 */
		function pullDownAction() {
			setTimeout(function() { // <-- Simulate network congestion, remove
				// setTimeout from production!

				//shop.currentPage = 1;
				//shop.selerListPageShow(1, 0)

				myScroll.refresh(); // 数据加载完成后，调用界面更新方法 Remember to refresh when
				// contents are loaded (ie: on ajax
				// completion)
			}, 1000); // <-- Simulate network congestion, remove setTimeout
			// from production!
		}

		/**
		 * 滚动翻页 （自定义实现此方法） myScroll.refresh(); // 数据加载完成后，调用界面更新方法
		 */
		function pullUpAction() {
			setTimeout(function() { // <-- Simulate network congestion, remove
				// setTimeout from production!

				var currentPage = parseInt(shop.currentPage) + 1;
				
				
				if(currentPage>shop.totalPage){
					pullUpEl.className = '';
					pullUpEl.querySelector('.pullUpLabel').innerHTML = '没有更多数据加载...';
					myScroll.refresh(); // 数据加载完成后，调用界面更新方法 Remember to refresh when
					return;
				}
				
				shop.currentPage = currentPage;
				shop.selerListPageShow(currentPage, 1);

				myScroll.refresh(); // 数据加载完成后，调用界面更新方法 Remember to refresh when
				// contents are loaded (ie: on ajax
				// completion)
			}, 1000); // <-- Simulate network congestion, remove setTimeout
			// from production!
		}

		/**
		 * 初始化iScroll控件
		 */
		function loaded() {
			pullDownEl = document.getElementById('pullDown');
			pullDownOffset = pullDownEl.offsetHeight;
			pullUpEl = document.getElementById('pullUp');
			pullUpOffset = pullUpEl.offsetHeight;
			
			myScroll = new iScroll(
					'wrapper',
					{
						scrollbarClass : 'myScrollbar', /* 重要样式 */
						useTransition : false, /* 此属性不知用意，本人从true改为false */
						checkDOMChanges:true,
						topOffset : pullDownOffset,
						onRefresh : function() {
							if (pullDownEl.className.match('loading')) {
								pullDownEl.className = '';
								pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
							} else if (pullUpEl.className.match('loading')) {
								pullUpEl.className = '';
								pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
							}
						},
						onScrollMove : function() {
							if (this.y > 5
									&& !pullDownEl.className.match('flip')) {
								pullDownEl.className = 'flip';
								pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手开始更新...';
								this.minScrollY = 0;
							} else if (this.y < 5
									&& pullDownEl.className.match('flip')) {
								pullDownEl.className = '';
								pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
								this.minScrollY = -pullDownOffset;
							} else if (this.y < (this.maxScrollY - 5)
									&& !pullUpEl.className.match('flip')) {
								pullUpEl.className = 'flip';
								pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
								this.maxScrollY = this.maxScrollY;
							} else if (this.y > (this.maxScrollY + 5)
									&& pullUpEl.className.match('flip')) {
								pullUpEl.className = '';
								pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
								this.maxScrollY = pullUpOffset;
							}
						},
						onScrollEnd : function() {
							if (pullDownEl.className.match('flip')) {
								pullDownEl.className = 'loading';
								pullDownEl.querySelector('.pullDownLabel').innerHTML = '加载中...';
								pullDownAction(); // Execute custom function
								// (ajax call?)
							} else if (pullUpEl.className.match('flip')) {
								pullUpEl.className = 'loading';
								pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';
								pullUpAction(); // Execute custom function (ajax
								// call?)
							}
						}
					});

			setTimeout(function() {
				document.getElementById('wrapper').style.left = '0';
			}, 800);
		}

		// 初始化绑定iScroll控件
		//document.addEventListener('touchmove', function(e) {
			//e.preventDefault();
		//}, false);
		document.addEventListener('DOMContentLoaded', loaded, false);

	},
	searchCategoryFocus : function() {// 搜索主营类别获得焦点
		$(".historyList").show();
	},
	searchCategoryBlur : function() {// 搜索主营类别失去焦点
		setTimeout(function() {
			$(".historyList").hide();
		}, 100)
	},
	getShopList : function() {// 获取商家列表

	},
	searchShop : function() {// 搜索店铺跳转
		$.mobile.changePage("#sellerListPage", "slideup");
	},
	searchCategoryBtn : function() {// 主营类别搜索
		$.mobile.changePage("#sellerListPage", "slideup");
	},
	selerListPageShow : function(currentPage, reset) {// 商家列表显示 reset 0重新载入
														// 1追加
		var k = $("#searchShop").val();

		if (k == '')
			return;

		$.ajax({
			type : "get",
			url : app.servicerURL + "/shop/list",
			data : {
				"k" : k,
				"currentPage" : currentPage
			},
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : function(data) {
				if (data.totalPage)
					shop.totalPage = data.totalPage;
				
				var htmlStr = "";
				if (data.list && data.list.length != 0) {
					for (var i = 0; i < data.list.length; i++) {
						htmlStr += shop.selerListHtmlShow(data.list[i]);
					}
				}
				if (reset == 0) {
					$("#sellerListPage .boxList").html(htmlStr);
					shop.currentPage = 1;
				}
				if (reset == 1) {
					$("#sellerListPage .boxList").append(htmlStr);
				}

			},
			error : function(data, df, d) {
				alert("数据加载失败");
			}
		});
	},
	selerListHtmlShow : function(jsonData) {// jsonData 商家数据
		var html = "";
		html += "<div class='box'>";
		html += "<h3>";
		html += "<a href='#shop1Page' class='ui-link'>" + jsonData.cname
				+ "</a>";
		html += "<a href='#' class='a1 ui-link'>260M</a>";
		html += "</h3>";
		html += "<h6>" + jsonData.caddress + "</h6>";
		html += "<p class='p1'>";
		html += "<span>主营产品:</span><span>" + jsonData.engage + "</span>";
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
