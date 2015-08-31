var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
app.get('/', function(req, res){
	res.send('<h1>Welcome Realtime Server</h1>');
});



/*************Redis 模块 把数据写入队列**************************/
///var redis = require("redis"),//召唤redis  
/* 
    连接redis数据库，createClient(port,host,options); 
    如果REDIS在本机，端口又是默认，直接写createClient()即可 
    redis.createClient() = redis.createClient(6379, '127.0.0.1', {}) 
*/  
///client = redis.createClient(6379,'localhost',{});  
//如果需要验证，还要进行验证  
//client.auth(password, callback);  
  
// if you'd like to select database 3, instead of 0 (default), call  
// client.select(3, function() { /* ... */ });  
  
//错误监听？  
///client.on("error", function (err) {  
    ///console.log("Error " + err);  
///}); 



var mysql=require('mysql');
var db_config = {
    host: 'localhost',  
    user: 'root',  
    password: '123123',  
    database: 'sl_db',  
    port: '3306'
};


var pool = mysql.createPool(db_config);  

var insertSQL = 'insert into t_message(sid,uid,message,sendType) values(?,?,?,?)';

var insert = function(insertSQL,params){
    pool.getConnection(function(err,conn){  
    	if(err){  
    		console.log('pool.getConnection [INSERT ERROR] - ',err.message);
            return;
        }
    	
        conn.query(insertSQL,params,function(err,result){ 
        	if(err){
        		console.log('[INSERT ERROR] - ',err.message);
                return;
        	}             	
            //释放连接  
            conn.release();  
            //事件驱动回调  
            //callback(qerr,vals,fields);  
        });
    });
}



var users = {};

io.on('connection', function(socket){
	
	console.log('a user connected');
	
	socket.on('private message', function (sign,msgObj) {
		console.log('private message');
		
		//向redis 消息队列中发送消息,后台定时任务写入数据库
		///client.lpush('messageQueue',msgObj.sid+"_"+msgObj.uid+"_"+msgObj.msg);
		console.log('msgObj.sendType='+msgObj.sendType);
		if(msgObj.sid&&msgObj.uid&&msgObj.msg&&msgObj.sendType!=null&&msgObj.sendType!=undefined){
			insert(insertSQL,[msgObj.sid,msgObj.uid,msgObj.msg,msgObj.sendType]);
		}else{
			console.log('no insert [msgObj.sid]='+msgObj.sid+" [msgObj.uid]="+msgObj.uid+" [msgObj.msg]="+msgObj.msg+" [msgObj.sendType]="+msgObj.sendType);
		}
		
		io.sockets.in('group'+sign).emit('event_name', msgObj);
	});
	
	
	socket.on('new user',function(sign){
		console.log('group'+sign);
		socket.join('group'+sign);
	});	

});



http.listen(3000, function(){
	console.log('listening on *:3000');
});