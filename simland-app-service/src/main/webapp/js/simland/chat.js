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


		var sid = $.mobile.activePage.find("input[name='sid']").val();
		var uid = $.mobile.activePage.find("input[name='uid']").val();
		chat.sign ='_'+sid+'_'+uid;		
		
		//chat.connection = io.connect('ws://139.196.23.106:3000', { 'reconnect': true }); 
		//chat.connection = io.connect('ws://139.196.23.106:3000', { "force new connection":true });
		//chat.connection = io.connect('ws://139.196.23.106:3000',{'reconnect':true,'auto connect':true});
		chat.connection = io.connect('ws://139.196.23.106:3000',{"force new connection":true });
		chat.connection.on('connect', function (data) { 
			console.log("与服务器连接");
			chat.query(1);
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
			console.log("与服务其断开");
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

		var sid = $.mobile.activePage.find("input[name='sid']").val();
		var uid = $.mobile.activePage.find("input[name='uid']").val();
		
		var msgObj = {};
		msgObj.msg = msg;
		msgObj.sid = sid;
		msgObj.uid = uid;
		msgObj.sendType = 0;//用户发送
		
         //发送消息
		chat.connection.emit('private message',chat.sign,msgObj);

			
	},
	showResult : function(msgObj){
		
		var uid = $.mobile.activePage.find("input[name='uid']").val();
		var htmlStr = "";
		htmlStr += "<div class='"+(msgObj.sendType==0?('msg'):('msg_s'))+"'>";
		htmlStr +="<div class='u_msg'><div class='u_out'>"+msgObj.msg+"</div></div>";
		htmlStr +="<div class='u_img'><img alt='' src='"+contextPath+(msgObj.sendType==0?('/images/user/auction_1_r6_c6.jpg'):('/images/tmp/258926-15011416445038.gif'))+"'></div>";	
		htmlStr +="<div class='clear'></div>";
		htmlStr +="</div>";
		$.mobile.activePage.find(".msgBox").append(htmlStr);
		$.mobile.activePage.find("input[name='inputMsg']").val("");
		app.myScroll.refresh();
		app.myScroll.scrollToElement($.mobile.activePage.find(".msgBox .msg:last")[0],100);
	},
	pullDownAction : function(){//加载之前的聊天记录
		chat.query();
	},
	query : function(reload){
		
		var sid = $.mobile.activePage.find("input[name='sid']").val();
		var icurrentPage = $.mobile.activePage.find("input[name='icurrentPage']").val();
		
		var data = {};
		data.sid = sid;
		data.icurrentPage = icurrentPage;
		
		$.ajax({
			type : "get",
			url : app.servicerURL + "/buy/chatList",
			data : data,
			dataType:'html',
			cache : false,
			async : false,
			success : callBack,
			error : function(data, df, d) {
				app.message("网络请求失败，请检查您的网络设置")
			}
		});
		
		function callBack(data){
			if(reload==1){
				$.mobile.activePage.find(".msgBox").html(data);
			}else{
				$.mobile.activePage.find(".msgBox").prepend(data);
			}
			
			$.mobile.activePage.find("input[name='icurrentPage']").val(parseInt(icurrentPage)+1);
			try{
				app.myScroll.refresh();
				app.myScroll.scrollToElement($.mobile.activePage.find(".msgBox .msg:last")[0],100);
			}catch(e){
				
			}
			
		}
	}
	
}