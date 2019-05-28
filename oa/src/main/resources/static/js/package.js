$(function(){
	var loadIndex;
	//websocket 初始化
	if ("WebSocket" in window){
		var jsessionId = $(".jsessionId").val();
		console.log(jsessionId);
		websocket = new WebSocket("ws://"+$(".websocketIp").val()+":"+$(".serverPort").val()+"/websocket/"+jsessionId);
		//连接发生错误的回调方法
	    websocket.onerror = function(){
	    	layer.msg("the websocket server is error!");
	    };
	    //连接成功建立的回调方法
	    websocket.onopen = function(event){
	    }
	    websocket.onmessage = function(event){
	    	
	    	var json = eval("("+event.data+")");
	    	if(json.type=="update"){
	    		$(".update_value").append(event.data);
	    	}else{
	    		$(".package_value").append(event.data);
	    	}
	    	if(json.isFinished){
	    		
	    		layer.close(loadIndex); 
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
	
	
	$(".center_div").on("click",".update",function(){
		var data = {
				num: 0,
				jessionId:$(".jsessionId").val()
			};
		var url = "/update";
		var s_function = function(data){
			if(data.updateFlag){
				
			}else{
				if(!data.hasDiff){
					layer.close(loadIndex); 
					layer.msg("No code update");
				}else{
					layer.msg($(".updateFailure-i18n").val());
				}
			}
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		loadIndex = layer.load(2);
		$.commonAjax(url,data,s_function,e_function);
	})
});