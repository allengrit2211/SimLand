//Initialize function

var AppServicerURL = "http://192.168.10.129:8080/simland-app-service";

var init = function() {

	$("#loginBtn").click(login);

};
$(document).ready(init);

function login() {
	$.mobile.changePage("#userCenterPage", "slideup");
	$.ajax({
		type : "get",
		url : AppServicerURL + "/appservice/login",
		data : {
			"uname" : $("#uname").val(),
			"upw" : $("#upw").val()
		},
		cache : false,
		async : false,
		dataType : 'jsonp',
		success : function(data) {
			if (data.code == 1) {
				$.mobile.changePage("#userCenterPage", "slideup");
			} else {
				$('<div>').simpledialog2({
					mode : 'button',
					headerText : '登录提示',
					headerClose : true,
					themeDialog : "c",
					themeHeader : "d",
					buttonPrompt : '用户名或密码错误',
					buttons : {
						'OK' : {
							click : function() {
								$('#buttonoutput').text('OK');
							},
							// icon: "ok",
							theme : "c"
						}
					}
				})
			}
		},
		error : function(data, df, d) {
			// $.mobile.changePage("#userCenterPage","slideup");
		}
	});
	return false;
}
