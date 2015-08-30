var chat = {
	connection : null,
	sign:null,
	initialize : function() {
		try{
			($.mobile.activePage.find(".msgBox").length>0)?chat.connect():chat.disconnect();
			
			
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
	
		
		var sid = $.mobile.activePage.find("input[name='sid']").val();
		var uid = $.mobile.activePage.find("input[name='uid']").val();
		chat.sign ='_'+sid+'_'+uid;		
		
		//chat.connection = io.connect('ws://139.196.23.106:3000', { 'reconnect': true }); 
		//chat.connection = io.connect('ws://139.196.23.106:3000', { "force new connection":true });
		chat.connection = io.connect('ws://139.196.23.106:3000',{'reconnect':true,'auto connect':true});
		chat.connection.on('connect', function (data) { 
			//console.log("与服务器连接");
	    });
		
		chat.connection.emit('new user',chat.sign);
		
		//监听消息发送
		//this.connection.on('message_'+sid+'_'+uid, chat.showResult);
        //监听 接收消息
		chat.connection.on('event_name', chat.showResult);
		
		
	},
	disconnect : function(){//断开链接
		
		/*
		if(chat.stompClient!=null){
			chat.stompClient.disconnect();
	        console.log("Disconnected");
		}*/
		if(chat.connection){
			chat.connection.emit('disconnect remove',chat.sign);
			//chat.connection.disconnect();
			//console.log("与服务其断开");
			chat.connection.disconnect();
		}
		

		
	},	
	sendMsg : function(){

		if(chat.connection==null){
			chat.connect();
		}
		
		var msg = $.mobile.activePage.find("input[name='inputMsg']").val();
		/*
		chat.stompClient.send("/app/chat/sendMsg", {}, JSON.stringify({ 'message': msg,'time':0,'sid':sid,"uid":uid}));
		*/
		//chat.connection.on('msg',function(data){
			//chat.connection.emit('message_'+sid+'_'+uid, {message:msg}); //向服务器发送消息
		    //console.log(data);
		//});
		//console.log("chat.connection.id="+chat.connection.id);
		//console.log("msg="+msg);

         //发送消息
		chat.connection.emit('private message',chat.sign,msg);

			
	},
	showResult : function(msg){
		var htmlStr = "";
		htmlStr += "<div class='msg'>";
		htmlStr +="<div class='u_msg'><div class='u_out'>"+msg+"</div></div>";
		htmlStr +="<div class='u_img'><img alt='' src='"+contextPath+"/images/user/auction_1_r6_c6.jpg'></div>";	
		htmlStr +="<div class='clear'></div>";
		htmlStr +="</div>";
		$.mobile.activePage.find(".msgBox").append(htmlStr);
		$.mobile.activePage.find("input[name='inputMsg']").val("");
		app.myScroll.refresh();
		app.myScroll.scrollToElement($.mobile.activePage.find(".msgBox .msg:last")[0],100);
	}
	
}