var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
app.get('/', function(req, res){
	res.send('<h1>Welcome Realtime Server</h1>');
});



var users = {};

io.on('connection', function(socket){
	
	console.log('a user connected');
	
	socket.on('private message', function (sign,msg) {
		console.log('private message');
		io.sockets.in('group'+sign).emit('event_name', msg);
	});
	
	
	socket.on('new user',function(sign){
		console.log('group'+sign);
		socket.join('group'+sign);
	});	

});


http.listen(3000, function(){
	console.log('listening on *:3000');
});