var sellerList = {
	totalPage : $("#totalPage").val(),// 总页数,
	myScroll : {},
	initialize : function() {
		// 商家星级排序
		$("#sellerListPage_score_btn").click(sellerList.scoreOrder);

		// 滚动翻页
		sellerList.scroll();

		// 页面初始数据加载
		$(document).on("pageinit", initPage2);

		function initPage2(event) {
			$(document).off('pageinit', initPage2);
			$("#sellerListPage_currentPage").val(1);
		}

		// 加载店铺收藏事件
		$("#sellerListPage .boxList .box .p2 .a2").click(function() {
			shop.collectShop($(this), $(this).attr("sid"));
		});

	},
	scroll : function() {// 商家列表页面，滚动分页
		/**
		 * 初始化iScroll控件
		 */
		function loaded() {
			sellerList.myScroll = new IScroll('#wrapper', {
				scrollbarClass : 'myScrollbar', /* 重要样式 */
				useTransition : false, /* 此属性不知用意，本人从true改为false */
				checkDOMChanges : true,
				mouseWheel : true,
				click : true,
				probeType : 3
			});

			sellerList.myScroll.on('scrollEnd', updatePosition);

			function updatePosition() {
				if (this.y - this.maxScrollY == 0) {
					var currentPage = parseInt($("#sellerListPage_currentPage").val()) + 1;

					if (currentPage > sellerList.totalPage) {
						return;
					}

					sellerList.selerListPageShow({
						currentPage : currentPage
					});
				}

			}

		}

		document.addEventListener('DOMContentLoaded', loaded, false);

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
			url : app.servicerURL + "shop/listAjax",
			data : $("#sellerListPage_form").serialize(),
			cache : false,
			async : false,
			success : selerListPageShowCallBack,
			error : function(data, df, d) {
				app.message("数据加载失败")
			}
		});

		// 回掉
		function selerListPageShowCallBack(data) {

			$("#sellerListPage .boxList").append(data);
			sellerList.myScroll.refresh();
			if (option.currentPage >= sellerList.totalPage) {
				$("#pullUp").html("已经到达最后一页...");
				return;
			}

		}

	},
	scoreOrder : function() {// 商家星级排序
		$("#sellerListPage_score").val("score");
		$("#sellerListPage_scoreType").val(1);
		$("#sellerListPage_form").submit();
	}
}

sellerList.initialize();
