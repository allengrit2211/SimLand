var app = {
	servicerURL : "http://192.168.10.129:8080/simland-app-service/",
	initialize : function() {
	},
	message : function(msg) {// 信息提示
		$("#message").text(msg);
		$("#message").show();
		setTimeout(function(){
			$('#message').fadeOut(500, function() {
				$("#message").html("");
				$('#message').hide();
			});
		},2000);
	}
}

app.initialize();


