var user = {
	initialize : function() {
		// 用户地址完成按钮
		$("#addressBtn").unbind().click(function() {
			$("#addressForm").submit();
		});

		$("#loginAjaxBtn").unbind().click(user.loginAjax);

		// 设置默认地址
		$(".defualtAddressBtn").unbind().click(user.defualtAddressBtn);
		
		//修改地址
		$(".editAddressBtn").unbind().click(user.editAddressShow);
		

	},
	editAddressShow : function(){
		var id = $(this).attr("aid");
		$.mobile.changePage(app.servicerURL + "/user/addAddressShow?id="+id, {
			transition : "slideup"
		});
	},
	defualtAddressBtn : function() {// 设置默认地址

		var tmp = this;
		
		var id = $(this).attr("aid");
		$.ajax({
			type : "get",
			url : app.servicerURL + "/user/defualtAddress",
			data : {
				id : id
			},
			cache : false,
			async : true,
			dataType : 'jsonp',
			success : defualtAddressCallBack,
			error : function(data, df, d) {
				app.message("网络请求失败，请检查您的网络设置")
			}
		});

		function defualtAddressCallBack(data) {
			if (data.code == -100) {
				$.mobile.changePage(app.servicerURL + "/loginPage", {
					transition : "slideup"
				});
			} else if (data.code == 1) {
				$(".defualtAddressBtn").each(function(i,e){
					$(e).removeClass("check")
				});
				$(tmp).addClass("check");
			} else {
				app.message(data.msg);
			}
		}
	},
	loginAjax : function() {
		$.ajax({
			type : "get",
			url : app.servicerURL + "/loginAjax",
			data : $.mobile.activePage.find("#loginPage_form").serialize(),
			cache : false,
			async : true,
			dataType : 'jsonp',
			success : loginAjaxCallBack,
			error : function(data, df, d) {
				app.message("网络请求失败，请检查您的网络设置")
			}
		});

		function loginAjaxCallBack(data) {
			if (data.code == 1) {
				app.message(data.msg)
				$.mobile.activePage.find("#loginPopup").popup('close');
			} else {
				app.message(data.msg)
			}
		}
	}

}
