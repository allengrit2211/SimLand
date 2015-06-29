//Initialize function

var init = function() {

	$(function() {
		$(document).ajaxStart(function() {
			$("#loading").show();
			setTimeout(function(){
				$("#loading").hide();
			},30000);
		});
		$(document).ajaxSuccess(function() {
			$("#loading").hide();
		});
	});
	
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

};
$(document).ready(init);




