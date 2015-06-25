var app = {
	//servicerURL : "http://192.168.253.3/simland-app-service/",
	//servicerURL : "http://220.228.131.85:9001/simland-app-service/",
	//servicerURL: "http://192.168.1.38/simland-app-service/",
	servicerURL: "http://115.28.92.59:8089/simland-app-service/",
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
	},
	loading : function() {// 加载信息

	}
}

app.initialize();


