var user = {
	loginFlag : true,
	initialize : function() {
		
		$("#loginBtn").click(user.login);
		
		$(document).on("pageshow", "#userCenterPage", user.pageLoad);
		
	},
	pageLoad : function(){
		//alert(0);
	},
	login : function() {

		if (!user.loginFlag) {
			return;
		}

		user.loginFlag = false;

		setTimeout(function() {// 防止重复提交
			user.loginFlag = true;
		}, 3000);

		$.ajax({
			type : "get",
			url : app.servicerURL + "/user/login",
			data : $("#loginPage_form").serialize(),
			cache : false,
			async : false,
			dataType : 'jsonp',
			success : loginCallBack,
			error : function(data, df, d) {
			}
		});

		function loginCallBack(data) {
			if (data.code == 2) {
				$.mobile.changePage("#userCenterPage", "slideup");
			} else {
				app.message(data.msg)
			}
		}

		return false;
	}
}

user.initialize();