//Initialize function

var AppServicerURL = "http://192.168.10.129:8080/simland-app-service";

var init = function() {

	$("#loginBtn").click(login);
	
	$("#searchShopBtn").click(searchShop);
	
	$("#searchCategory").focus(searchCategoryFocus);
	
	$("#searchCategory").blur(searchCategoryBlur);
	
	$("#searchCategoryBtn").click(searchCategoryBtn);
	
	//购物车商品全选按钮
	$("#carCheckAll").click(carCheckAll);
	
	document.addEventListener("backbutton", yourCallbackFunction, false);
};
$(document).ready(init);


function yourCallbackFunction(){
	alert(0);
}


function carCheckAll(){
	
	if($(this).is(":checked")){
		$("input[name='carCheck']").attr("checked", true);  
	}else{
	    $("input[name='carCheck']").attr("checked", false);  
	}
	

}

function searchCategoryFocus(){
	$(".historyList").show();
}

function searchCategoryBlur(){
	
	setTimeout(function(){
		$(".historyList").hide();
	},1000)
	
}

function searchCategoryBtn(){
	$.mobile.changePage("#sellerListPage", "slideup");
}




jQuery(function(){
    $('.slides2').slidesjs({
        navigation: true,
        start: 3,
        play: {
          auto: true
        }
	});
    
    $(".slides2").css("width",$(window).width());
    
});


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


function searchShop(){
	$.mobile.changePage("#sellerListPage", "slideup");
}