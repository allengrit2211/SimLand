var sellerList = {
	totalPage : 1,// 总页数
	initialize : function() {
		// 店铺列表页面加载事件
		$(document).on("pageshow", "#sellerListPage", sellerList.pageLoad);
		// 商家星级排序
		$("#sellerListPage_score_btn").click(sellerList.scoreOrder);

		// $( "#sellerListPage_search_k" ).bind( "change", function(event, ui) {
		// alert(0);
		// });

		// 滚动翻页
		sellerList.scroll();
	},
	serchParam : function(option) {
		if (option.k != undefined && option.k != null)
			$("#sellerListPage_search_k").val(option.k)
		if (option.currentPage != undefined && option.currentPage != null)
			$("#sellerListPage_currentPage").val(option.currentPage)

		if (option.sort != undefined && option.sort != null)
			$("#sellerListPage_score").val(option.sort)

		if (option.sortType != undefined && option.sortType != null)
			$("#sellerListPage_scoreType").val(option.sortType)

	},
	pageLoad : function(event, ui) {
		// 设置参数查询 商家列表
		sellerList.selerListPageShow({
			k : $("#sellerListPage_search_k").val(),
			currentPage : 1,
			reset : 0,
			sort : "",
			sortType : ""
		});
	},
	scroll : function() {// 商家列表页面，滚动分页

		var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset, generatedCount = 0;

		/**
		 * 下拉刷新 （自定义实现此方法） myScroll.refresh(); // 数据加载完成后，调用界面更新方法
		 */
		function pullDownAction() {
			setTimeout(function() { // <-- Simulate network congestion, remove
				// setTimeout from production!

				// sellerList.selerListPageShow(1, 0)

				myScroll.refresh(); // 数据加载完成后，调用界面更新方法 Remember to refresh when
				// contents are loaded (ie: on ajax
				// completion)
			}, 300); // <-- Simulate network congestion, remove setTimeout
			// from production!
		}

		/**
		 * 滚动翻页 （自定义实现此方法） myScroll.refresh(); // 数据加载完成后，调用界面更新方法
		 */
		function pullUpAction() {
			setTimeout(
					function() { // <-- Simulate network congestion, remove
						// setTimeout from production!

						var currentPage = parseInt($(
								"#sellerListPage_currentPage").val()) + 1;

						if (currentPage > sellerList.totalPage) {
							pullUpEl.className = '';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '已经到达最后一页...';
							myScroll.refresh(); // 数据加载完成后，调用界面更新方法 Remember to
							// refresh when
							return;
						}

						sellerList.selerListPageShow({
							currentPage : currentPage,
							reset : 1
						});

						myScroll.refresh(); // 数据加载完成后，调用界面更新方法 Remember to
						// refresh when
						// contents are loaded (ie: on ajax
						// completion)
					}, 300); // <-- Simulate network congestion, remove
			// setTimeout
			// from production!
		}

		/**
		 * 初始化iScroll控件
		 */
		function loaded() {
			pullDownEl = document.getElementById('sellerListPage_pullDown');
			pullDownOffset = pullDownEl.offsetHeight;
			pullUpEl = document.getElementById('sellerListPage_pullUp');
			pullUpOffset = pullUpEl.offsetHeight;

			myScroll = new iScroll(
					'sellerListPage_wrapper',
					{
						scrollbarClass : 'myScrollbar', /* 重要样式 */
						useTransition : false, /* 此属性不知用意，本人从true改为false */
						checkDOMChanges : true,
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

			setTimeout(
					function() {
						document.getElementById('sellerListPage_wrapper').style.left = '0';
					}, 300);
		}

		// 初始化绑定iScroll控件
		// document.addEventListener('touchmove', function(e) {
		// e.preventDefault();
		// }, false);
		document.addEventListener('DOMContentLoaded', loaded, false);

	},
	/***************************************************************************
	 * 
	 * @param option {
	 *            k 关键字 currentPage 当前页数 reset 加载类型 0 重新加载列表 1累计加载列表 sort 排序字段
	 *            sortType 排序类型 0 正序 1 倒序 }
	 */
	selerListPageShow : function(option) {// 商家列表显示
		// 设置搜索参数
		sellerList.serchParam(option);
		
		$.ajax({
			type : "get",
			url : app.servicerURL + "shop/list",
			data : $("#sellerListPage_form").serialize(),
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : selerListPageShowCallBack,
			error : function(data, df, d) {
				app.message("数据加载失败")
			}
		});

		// 回掉
		function selerListPageShowCallBack(data) {
			if (data.totalPage)
				sellerList.totalPage = data.totalPage;

			if(data.list&&data.list.length==0){
				$("#sellerListPage .boxList").html("没有搜索到任何数据...");
				return;
			}
			
			
			var htmlStr = "";
			if (data.list && data.list.length != 0) {
				for (var i = 0; i < data.list.length; i++) {
					htmlStr += sellerList.selerListHtmlShow(data.list[i]);
				}
			}
			// 重新加载或者累积加载
			if (option.reset == 0) {
				$("#sellerListPage .boxList").html(htmlStr);
				$('.pullUpLabel').html("上拉加载更多...");
			}
			if (option.reset == 1) {
				$("#sellerListPage .boxList").append(htmlStr);
			}
			
			
			if($("#sellerListPage_stype").val()==1){
				$("#sellerListPage .boxList .box .p1").textSearch($("#sellerListPage_search_k").val(),{markColor: "#44BBAB"});
			}else	
				$("#sellerListPage .boxList .box .a0").textSearch($("#sellerListPage_search_k").val(),{markColor: "#44BBAB"});
			
		}

	},
	selerListHtmlShow : function(jsonData) {// jsonData 商家数据
		var html = "";
		html += "<div class='box'>";
		html += "<p>";
		html += "<a onclick='shop.id="+jsonData.id+"' href='?id="+jsonData.id+"#shop1Page' class='a0 ui-link'>" + jsonData.cname
				+ "</a>";
		html += "<a href='#' class='a1 ui-link'>260M</a>";
		html += "</p>";
		html += "<p>" + jsonData.caddress + "</p>";
		html += "<p class='p1'>";
		html += "<span>主营产品:</span><span>" + jsonData.engage + "</span>";
		html += "</p>";
		html += "<div class='line'></div>";
		html += "<p class='p2'>";
		html += "<span class='s1'>965</span> <span>件产品</span> | <span class='s2'>&nbsp;</span> <span class='s3'>236</span> <span>次</span>";
		html += "<span class='s4 star star" + jsonData.score
				+ "'>&nbsp;</span>";
		html += "</p>";
		html += "</div>";
		return html;
	},
	scoreOrder : function() {// 商家星级排序
		$("#sellerListPage_score").val("score");
		$("#sellerListPage_scoreType").val(1);
		sellerList.selerListPageShow({
			currentPage : 1,
			reset : 0,
			sort : "score",
			sortType : 1
		});
	},
	sellerListPageForm : function(){
		try{
			sellerList.selerListPageShow({
				k:$("#sellerListPage_search_k").val(),
				currentPage : 1,
				reset : 0
			});
		}catch(e){
			return false;
		}
		return false;
	}
}

sellerList.initialize();
