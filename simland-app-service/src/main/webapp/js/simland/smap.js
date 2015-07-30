var smap = {
	previewMap : null,// 预览对象
	initialize : function() {
		smap.preview();
	},
	preview : function() {// 预览图

		if ($.mobile.activePage.find("#map1PageMap").length <= 0)
			return;
		
		setTimeout(loadMap,500);
		
		function loadMap(){//加载地图
			// 百度地图API功能
			smap.previewMap = new BMap.Map("map1PageMap"); // 创建Map实例

			var point = new BMap.Point(114.139417, 22.567018);
			smap.previewMap.centerAndZoom(point, 15); // 初始化地图,设置中心点坐标和地图级别。
			// var marker = new 114.137548, 22.566284
			// //previewMap.addOverlay(marker);
			smap.previewMap.setCurrentCity("深圳");
			// previewMap.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
			// previewMap.addControl(new BMap.ScaleControl()); // 添加比例尺控件
			// previewMap.addControl(new BMap.OverviewMapControl()); //添加缩略地图控件
			smap.previewMap.enableScrollWheelZoom(); // 启用滚轮放大缩小

			smap.previewMap.addEventListener("tilesloaded", function() {
				
			});

			// 加载地图坐标数据
			var secRingArray = new Array();
			var ringlist = $.mobile.activePage.find("#ringlist");
			if (ringlist.length > 0) {
				var ringlistJson = eval($(ringlist).text());
				$(ringlistJson).each(function(i, ring) {
					if ($(ring.ringDetailss).length > 0) {
						var secRing = new Array();

						$(ring.ringDetailss).each(function(ii, ringDetail) {
							if (ringDetail.point)
								secRing.push(new BMap.Point(ringDetail.point.split(",")[0], ringDetail.point.split(",")[1]));
						});
						if (secRing.length > 0)
							secRingArray.push(secRing);

					}
				});
			}

			// 设置坐标
			$(secRingArray).each(function(i, e) {
				var color = [ "Blue", "Green", "Red", "Indigo", "orange", "DarkSlateGray" ];
				var secRingPolygon1 = new BMap.Polygon(secRingArray[i], {
					strokeColor : "#41AC98",
					fillColor : '',
					strokeWeight : 5,
					strokeOpacity : 0.8
				});// 创建多边形
				smap.previewMap.addOverlay(secRingPolygon1);// 添加多边形到地图上
			});

			window.setTimeout(function() {
				smap.previewMap.panTo(point);
			}, 1000);
		}
		
	}
}
