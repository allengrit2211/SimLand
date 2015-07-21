var user = {
	initialize : function() {
		// 用户地址完成按钮
		$("#addressBtn").unbind().click(function() {
			$("#addressForm").submit();
		});

		$("#loginAjaxBtn").unbind().click(user.loginAjax);

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
