var chatroom ={}
var w_width = $(window).width();
var w_height = $(window).height();
$(function() {
	
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
					var time = chatroom.getNowFormatDate(null);
					var date = time.substring(0,19);    
					date = date.replace(/-/g,'/'); 
					var timestamp = new Date(date).getTime();
					var nickName = $(".name input:last-child").val().trim();
					if(nickName == ""){
						nickName = $(".visitor-i18n").val();
					}
					var url = "/html/addMessage";
					var data = {
							w_content: value,
							time: timestamp,
							w_nickName: nickName,
						};
					var rs_function = function(result){
						if(result.addChatFlag){
							var	massage = time+"&nbsp;&nbsp;"+"<label style=\"border-radius: 5px;background-color:#00F5FF;\">"+nickName+"</label>："+value+"<br/>"; 
							$("#MessageBoard").append(massage);
							$("#hwsMessage").html("");
						}else{
							layer.msg($(".serverError-i18n").val());
						}
						
					}
					var re_function = function(result){
						layer.msg($(".serverError-i18n").val());
					}
					$.commonAjax(url,data,rs_function,re_function);
					
				}

			});
	
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
	    	console.log(json);
	    	if(null != json.webSocketSessionId){
	    		$(".name input:nth-child(1)").val(json.webSocketSessionId);
		    	$(".name input:nth-child(3)").val(json.webSocketSessionId);
		    	$(".content").append("<p>"+$(".welcomeToYou-i18n").val()+","+$(".joinChatroom-i18n").val()+"</p>");
	    	}
	    	if(null != json.type){
	    		if(json.type == "1"){
	    			if(json.content!=$(".name input:nth-child(1)").val()){
						$(".content").append("<p>"+$(".welcome-i18n").val()+json.content+","+$(".joinChatroom-i18n").val()+"</p>");
					}
	    		}else if(json.type == "2"){
	    			$(".content").append("<p>"+json.content+","+$(".exitChatroom-i18n").val()+"</p>");
	    		}
	    		
	    	}
	    	
	    	
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
	//初始化页面
	chatroom.initPages();
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
	var url = "/html/initChatroom";
	var data = {
		};
	var rs_function = function(result){
			for(var i =0;i<result.WeChatList.length;i++){
				var	massage = chatroom.getNowFormatDate(result.WeChatList[i].w_time)+"&nbsp;&nbsp;"+"<label style=\"border-radius: 5px;background-color:#00F5FF;\">"+result.WeChatList[i].w_nickName+"</label>："+result.WeChatList[i].w_content+"<br/>"; 
				$("#MessageBoard").append(massage);
			}
	}
	var re_function = function(result){
		layer.msg($(".serverError-i18n").val());
	}
	$.commonAjax(url,data,rs_function,re_function);
	
}
chatroom.getNowFormatDate = function(time) {
	if(null == time){
		var date = new Date();
	}else{
		var date = new Date(time);
	}
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
