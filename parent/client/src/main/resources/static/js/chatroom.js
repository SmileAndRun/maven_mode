var chatroom ={}
var w_width = $(window).width();
var w_height = $(window).height();
$(function() {
	//初始化页面
	chatroom.initPages();
	$(".sendButton").on("click","#qq",function(){
		$("#qqFace").css("display","block");
	});
	$("#qqFace").on("click","img",function(){
		var src = $(this).attr("src");
		
		$("#hwsMessage").append("<img src=" + src + "></img>");
		$("#qqFace").css("display","none");
	});
	$(".sendButton").on(
			"click",
			"#hwsSend",
			function() {
				var value = $("#hwsMessage").html();
				if (value != null) {
					var massage = getNowFormatDate()+"&nbsp;&nbsp;"+"<label style=\"border-radius: 5px;background-color:#00F5FF;\">游客</label>："+value
						+ "<br/>";	
					$("#MessageBoard").append(massage);
					$("#hwsMessage").html("");
				}

			});
	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var hour = date.getHours();
		var min = date.getMinutes();
		var currentdate = year + seperator1 + month + seperator1 + strDate +" "+hour+":"+min;
		return currentdate;
	}
	//websocket 初始化
	if ("WebSocket" in window){
		websocket = new WebSocket("ws://localhost:8089/websocket");
		//连接发生错误的回调方法
	    websocket.onerror = function(){
	    	layer.msg("the websocket server is error!");
	    };
	    //连接成功建立的回调方法
	    websocket.onopen = function(event){
	    }
	    websocket.onmessage = function(result){
	    	var json = eval("("+result.data+")");
	    	
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
}); 
chatroom.initPages = function(){
	$('.background').css({
		height: w_height
	});
	$(".announcement").css({
		marginLeft: (w_width - $(".announcement").width()-$("#MessageBoard").width())/2,
		marginTop: (w_height - $(".announcement").height() - $(".sendDiv").height())/2 -15
	});
	$(".sendDiv").css({
		marginLeft: (w_width - $(".name").width()- $("#hwsMessage").width()- $(".sendButton").width())/2,
	});
	
}
