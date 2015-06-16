//Initialize function

//var AppServicerURL = "http://192.168.10.129:8080/simland-app-service";

var AppServicerURL = "http://localhost:8080/simland-app-service";

var init = function() {

	// 购物车商品全选按钮
	$("#carCheckAll").click(carCheckAll);

};
$(document).ready(init);

function carCheckAll() {

	if ($(this).is(":checked")) {
		$("input[name='carCheck']").attr("checked", true);
	} else {
		$("input[name='carCheck']").attr("checked", false);
	}

}

jQuery(function() {
	$('.slides2').slidesjs({
		navigation : true,
		start : 3,
		play : {
			auto : true
		}
	});

	$(".slides2").css("width", $(window).width());

});
