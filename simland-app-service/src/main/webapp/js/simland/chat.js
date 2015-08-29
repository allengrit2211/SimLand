var chat = {
	stompClient: null,
	connection : null,
	initialize : function() {
		try{
			setTimeout(function(){
				location.href.indexOf("/buy/chatMessage")>-1?(chat.connect()):(chat.disconnect());
			},200);
			
			$.mobile.activePage.find("#sendMsg").unbind().click(chat.sendMsg);
		}catch(e){
			alert("error:"+e)
		}
	},
	connect : function(){//连接websocket
		/*
		var socket = new SockJS(contextPath+"/chat");
		chat.stompClient = Stomp.over(socket);
		chat.stompClient.connect({}, function(frame) {
			
			var sid = $.mobile.activePage.find("input[name='sid']").val();
			var uid = $.mobile.activePage.find("input[name='uid']").val();
			
	        console.log('Connected: ' + frame);
	        chat.stompClient.subscribe('/topic/showResult/'+sid+"/"+uid, function(result){
	            chat.showResult(JSON.parse(result.body).message);
	        });

			chat.stompClient.subscribe('/app/chat/init/' + sid+'/'+uid, function (initData) {
				console.log(initData);
				//var body = JSON.parse(initData.body);
				//var chat = body.chat;
				//chat.forEach(function(item) {
					//showChat(item);
				//});
			});  
	    },function(e){
	    	alert(e)
	    });	
		*/
		
		
		chat.connection = io.connect('ws://139.196.23.106:3000', { 'reconnect': false }); 
		chat.connection.on('connect', function (data) {  
			console.log("与服务器连接");
	    }); 
		//监听消息发送
		this.connection.on('message', chat.showResult);		
		
	},
	disconnect : function(){//断开链接
		/*
		if(chat.stompClient!=null){
			chat.stompClient.disconnect();
	        console.log("Disconnected");
		}*/
		if(chat.connection!=null){
			chat.connection.disconnect();
			console.log("与服务其断开");
		}
		

		
	},	
	sendMsg : function(){

		if(chat.connection==null){
			chat.connect();
		}
		
		var msg = $.mobile.activePage.find("input[name='inputMsg']").val();
		/*
		var sid = $.mobile.activePage.find("input[name='sid']").val();
		var uid = $.mobile.activePage.find("input[name='uid']").val();
		
		chat.stompClient.send("/app/chat/sendMsg", {}, JSON.stringify({ 'message': msg,'time':0,'sid':sid,"uid":uid}));
		*/
		//chat.connection.on('msg',function(data){
			chat.connection.emit('message', {message:msg}); //向服务器发送消息
		    //console.log(data);
		//});
	},
	showResult : function(re){
		var htmlStr = "";
		htmlStr += "<div class='msg'>";
		htmlStr +="<div class='u_msg'><div class='u_out'>"+re.message+"</div></div>";
		htmlStr +="<div class='u_img'><img alt='' src='"+contextPath+"/images/user/auction_1_r6_c6.jpg'></div>";	
		htmlStr +="<div class='clear'></div>";
		htmlStr +="</div>";
		$.mobile.activePage.find(".msgBox").append(htmlStr);
		$.mobile.activePage.find("input[name='inputMsg']").val("");
		app.myScroll.refresh();
		app.myScroll.scrollToElement($.mobile.activePage.find(".msgBox .msg:last")[0],100);
	}
	
}