//待定
var websocket;
$(function(){
	if ("WebSocket" in window){
		var name = $.cookie('SESSION_USERNAME');
		var pwd = $.cookie('SESSION_PASSWORD');
		websocket = new WebSocket("ws://localhost:8089/websocket/"+name+pwd);
		//连接发生错误的回调方法
	    websocket.onerror = function(){
	    	layer.msg("the websocket server is error!");
	    };
	    //连接成功建立的回调方法
	    websocket.onopen = function(event){
	    }
	    
	    //连接关闭的回调方法
	    websocket.onclose = function(){
	    }
	    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	    window.onbeforeunload = function(){
	        websocket.close();
	    }
	    //关闭连接
	    function closeWebSocket(){
	        websocket.close();
	    }
	}else{
		layer.msg("your browser is not support websocket!");
	}
})