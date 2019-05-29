$(function(){
	var loadIndex;
	var packageFlag = false;
	var settingNum = 0;
	//websocket 初始化
	if ("WebSocket" in window){
		var jsessionId = $(".jsessionId").val();
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
	    		$(".update_value").append(json.value+"<br/>");
	    	}else{
	    		$(".package_value").append(json.value+"<br/>");
	    	}
	    	if(json.isFinished){
	    		layer.close(loadIndex); 
	    		layer.msg($(".operateSuccess-i18n").val());
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
			};
		var url = "/getSettings";
		var s_function = function(data){
			if(data.flag){
				var dataValue = "<div><table class='table table-bordered table-hover definewidth m10'><tr><td>"+$(".serialNumber-i18n").val()
				+"</td><td>"+$(".localAddress-i18n").val()+"</td><td>"+$(".remoteAddress-i18n").val()+"</td><td></td></tr>";
				for(var i=0;i<data.Settings.length;i++){
					dataValue += "<tr><td>"+(i+1)+"</td><td>"+data.Settings[i].localRepo+"</td><td>"
					+data.Settings[i].remoteRepo+"</td><td><input type='radio'  name='settings' value='"+(i+1)+"' ></td></tr>"
				}
				dataValue += "</table></div>";
				layer.open({
				  title: $(".sysSet-i18n").val(),
				  content: dataValue,
				  area: ['700px', '400px'],
				  type: 1,
				  maxmin: true,            //最大化按钮
			  	  anim:3,                    //动画
			  	  shade: [0.8, '#393D49'],//遮罩层
			  	  btn: [$(".confirm-i18n").val()],
			  	  yes: function(index, layero){
			  		var num = $('input[name="settings"]:checked').val();
			  		if(num != undefined){
			  			layer.close(index);
			  			updateCode(num-1);
			  			packageFlag = true;
			  			settingNum = num-1;
			  		}else{
			  			layer.msg($(".pleaseSelect-i18n").val());
			  		}
			  		
				  },
			  	  cancel: function(index, layero){ 
			  	    layer.close(index);
			  	  }
				});
			}else{
				
			}
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		$.commonAjax(url,data,s_function,e_function);
		
	});
	function updateCode(num){
		var data = {
				num: num,
				jessionId:$(".jsessionId").val(),
			};
		var url = "/update";
		var s_function = function(data){
			if(data.updateFlag){
				$(".code_version").val(data.code_version);
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
	}
	$(".step").on("click",".btn",function(){
		var className = $(this).prop("class");
		if(className.indexOf("btn-info")!= -1){
			if(packageFlag){
				$(".prev_step").addClass("btn-info");
				$(".next_step").addClass("btn-info");
				$(".finnish").addClass("btn-info");
				$(this).removeClass("btn-info");
			}
			var thisValue = $(this).val();
			if(thisValue == 1){
				$(".steptab2").removeClass("btn-info");
				$(".steptab1").addClass("btn-info");
				$(".package_value").css({
					display:"none",
				});
				$(".update_value").css({
					display:"block",
				});
				$(".package_title").css({
					display:"none",
				});
				$(".update_title").css({
					display:"block",
				});
			}else if(thisValue == 2){
				if(packageFlag){
					$(".steptab1").removeClass("btn-info");
					$(".steptab2").addClass("btn-info");
					$(".update_value").css({
						display:"none",
					});
					$(".package_value").css({
						display:"block",
					});
					$(".package_title").css({
						display:"block",
					});
					$(".update_title").css({
						display:"none",
					});
				}else{
					layer.msg($(".updateFirst-i18n").val());
				}
				
			}else{
				
			}
		}
	});

	$(".center_div").on("click",".package",function(){
		
		var data = {
				num :settingNum,
			};
		var url = "/getPoms";
		var s_function = function(data){
			if(data.flag){
				var dataValue = "<div><table class='table table-bordered table-hover definewidth m10'><tr><td>"+$(".serialNumber-i18n").val()
				+"</td><td>POM</td><td></td></tr>";
				for(var i=0;i<data.pomList.length;i++){
					dataValue += "<tr><td></td><td>"+data.pomList[i]+"</td>"
					+"<td><input type='checkbox'  name='pom'></td></tr>"
				}
				dataValue += "</table></div>";
				layer.open({
				  title: "POM",
				  content: dataValue,
				  area: ['700px', '400px'],
				  type: 1,
				  maxmin: true,            //最大化按钮
			  	  anim:3,                    //动画
			  	  shade: [0.8, '#393D49'],//遮罩层
			  	  btn: [$(".confirm-i18n").val()],
			  	  yes: function(index, layero){
			  		if(pomFlag==0){
			  			layer.msg($(".pleaseSelect-i18n").val());
			  		}else{
			  			mvnPackage();
			  			layer.close(index);
			  		}
			  		
				  },
			  	  cancel: function(index, layero){ 
			  		pomNum = 0;
			  	    layer.close(index);
			  	  }
				});
			}else{
				layer.msg("NO POM File");
			}
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		$.commonAjax(url,data,s_function,e_function);
		
	});
	var pomNum=0;
	var pomFlag = 0;
	$(this).on("click",":checkbox",function(){
		if($(this).prop("checked")){
			pomNum++;
			pomFlag++;
			$(this).parent().prev().prev().text(pomNum);
		}else{
			$(this).parent().prev().prev().text("");
			pomFlag--;
		}
	});
	
	function mvnPackage(){
		var numArr = [];
		var addressArr = [];
		$("table input:checkbox").each(function(){
				if($(this).prop("checked")){
					numArr.push($(this).parent().prev().prev().text());
					addressArr.push($(this).parent().prev().text());
				}
			});
		var data = {
				numArr:numArr,
				addressArr:addressArr,
				command:$(".mvn_command").val(),
			};
		var url = "/package";
		var s_function = function(data){
			if(!data.flag){
				layer.msg($(".operateFailure-i18n").val());
			}
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		loadIndex = layer.load(2);
		$.arrayAjax(url,data,s_function,e_function);
	}
});

