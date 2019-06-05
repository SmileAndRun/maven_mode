var websocket ;
$(function(){
	
	
	//初始化页面
	dateFormateTime();
	function dateFormateTime(){
		$(".data tbody tr").each(function(){
			var startTime = $(this).find("td").eq(2).text();
			var endTime = $(this).find("td").eq(3).text();
			$(this).find("td").eq(2).text(getSmpFormatDateByLong(parseInt(startTime),true));
			$(this).find("td").eq(3).text(getSmpFormatDateByLong(parseInt(endTime),true));
		});
	}
	
	$(".titleButton").on("click",".createButton",function(){
		$(".shadeDiv").css("display","block");
		$(".addDiv").css("display","block");
	});
	$(".addJobTable").on("click",".cancel",function(){
		removeAddPanel();
	});
	
	$(".addJobTable").on("click",".jobTypeVal",function(){
		if($(this).val()=="com.hws.oa.core.quartz.model.PackageJob"){
			$(".mvnCommandTr").css({
				display:"block",
			})
			$(".addDiv").css({
				height: "400px",
			});
		}else{
			$(".mvnCommandTr").css({
				display:"none",
			})
			$(".addDiv").css({
				height: "350px",
			});
		}
	});
	
	initDateTimePicker(".startTimeDiv");
	initDateTimePicker(".endTimeDiv");
	function removeAddPanel(){
		$(".shadeDiv").css("display","none");
		$(".addDiv").css("display","none");
		$(".taskNameVal").val("");
		$(".finishedTimeVal").val("");
		$(".createTimeVal").val("");
		$(".executionFrequencyVal").val("");
	}
	//bootstrap时间控件方法
	function initDateTimePicker(id){
		$(id).datetimepicker({
	        format: "yyyy-mm-dd  hh:ii:ss",
	        showMeridian: true,
	        autoclose: true,
	        todayBtn: true,
	        pickerPosition: "bottom-left"
	    });
	}
	$(".executionSelect").on("click","input",function(){
		var flag = $(".executionTitle input:checked").val();
		if(flag == 1){
			$(".unactiveBut").removeClass();
			//$("#activeBut-1").removeAttr("id");
			$(this).prop('id',"activeBut-1");
			
		}
	});
	$(".executionTitle input:first").prop("checked","checked");
	$(".executionTitle").on("click","input",function(){
		var flag = $(this).val();
		if(flag != 1){
			$("#activeBut-1").removeAttr("id");
			$(".executionSelect input").addClass("unactiveBut");
		}else{
			$(".executionSelect input").removeClass("unactiveBut");
		}
	});
	function dealWithData(){
		var select_type = $(".executionTitle input:checked").val();
		var value="";
		if(select_type == 1){
			
			$("[id=activeBut-1]").each(function(){
				value += $(this).val()+",";
			});
			if(value.length <= 0){
				value= "*";
			}else{
				value = value.substr(0, value.length-1);
			}
			
		}else if(select_type == 2){
			value = $(".executionTitle select:first").val() + "/" + $(".executionTitle select:last").val();
		}else{
			value = "*";
		}
		return value;
	}
	
	//执行频率的类型选择
	var cronExpression = {};
	$(".executionType").on("click","button",function(){
		//数据处理
		var type = $("#activeBtn").val();
		cronExpression[type]=dealWithData();
		
		//界面样式处理
		$("#activeBtn").removeAttr("id");
		$(this).prop('id',"activeBtn");
		
		
		
		var flag = $(this).val();
		$(".executionTitle span:nth-last-child(2)").text($(this).text());
		$(".executionTitle span:nth-last-child(4)").text("");
		if(flag == 1 || flag == 0){
			var selectLength = $(".executionSelect input").length;
			if(selectLength != 60){
				$(".executionSelect").empty();
				$(".executionTitle select:first").empty();
				$(".executionTitle select:last").empty();
				for(var i = 0;i<60;i++){
					$(".executionSelect").append("<input  value="+i+" readonly/>");
					$(".executionTitle select:first").append("<option value="+i+">"+i+"</option>");
					var temp = i + 1;
					if(temp < 60){
						$(".executionTitle select:last").append("<option value="+(i+1)+">"+(i+1)+"</option>");
					}
				}
			}
				
		}else if(flag == 2){
			$(".executionSelect").empty();
			$(".executionTitle select:first").empty();
			$(".executionTitle select:last").empty();
			for(var i = 0;i<24;i++){
				$(".executionSelect").append("<input  value="+i+" readonly/>");
				$(".executionTitle select:first").append("<option value="+i+">"+i+"</option>");
				var temp = i + 1;
				if(temp < 24){
					$(".executionTitle select:last").append("<option value="+(i+1)+">"+(i+1)+"</option>");
				}
			}
		}else if(flag == 3){
			$(".executionSelect").empty();
			$(".executionTitle select:first").empty();
			$(".executionTitle select:last").empty();
			for(var i = 1;i<32;i++){
				$(".executionSelect").append("<input  value="+i+" readonly/>");
				$(".executionTitle select:first").append("<option value="+i+">"+i+"</option>");
				$(".executionTitle select:last").append("<option value="+i+">"+i+"</option>");
			}
		}else if(flag == 4){
			$(".executionSelect").empty();
			$(".executionTitle select:first").empty();
			$(".executionTitle select:last").empty();
			for(var i = 1;i<13;i++){
				$(".executionSelect").append("<input  value="+i+" readonly/>");
				$(".executionTitle select:first").append("<option value="+i+">"+i+"</option>");
				$(".executionTitle select:last").append("<option value="+i+">"+i+"</option>");
			}
		}else if(flag == 5){
			$(".executionSelect").empty();
			$(".executionTitle select:first").empty();
			$(".executionTitle select:last").empty();
			$(".executionTitle span:nth-last-child(4)").text($(this).text());
			$(".executionTitle span:nth-last-child(2)").text("");
			for(var i = 1;i<8;i++){
				$(".executionSelect").append("<input  value="+i+" readonly/>");
				$(".executionTitle select:first").append("<option value="+i+">"+i+"</option>");
				$(".executionTitle select:last").append("<option value="+i+">"+i+"</option>");
			}
		}
		//清除上一次选择的内容
		//表明上次点击的按钮和当前点击的按钮不是同一个即可以开始清除数据
		if(type != $(this).val()){
			$(".executionTitle input:first").prop("checked","checked");
			$("[id=activeBut-1]").removeAttr("id");
			if(cronExpression[$(this).val()] != undefined){
				if(cronExpression[$(this).val()] == "*"){
					$(".executionTitle input:first").prop("checked","checked");
				}else if (cronExpression[$(this).val()].indexOf("/") != -1){
					$(".executionTitle input:last").prop("checked","checked");
					var cycleArr = cronExpression[$(this).val()].split("/");
					$(".executionTitle select:first option[selected='selected']").removeAttr("selected");
					$(".executionTitle select:first option[value='"+cycleArr[0]+"']").prop("selected",true);
					$(".executionTitle select:last option[selected='selected']").removeAttr("selected");
					$(".executionTitle select:last option[value='"+cycleArr[1]+"']").prop("selected",true);
				}else{
					$(".executionTitle input[value='1']").prop("checked","checked");
					if(cronExpression[$(this).val()].indexOf(",") == -1){
						$(".executionSelect input[value='"+cronExpression[$(this).val()]+"']").prop('id',"activeBut-1");
					}else{
						var pointArr = cronExpression[$(this).val()].split(",");
						for(var i=0;i<pointArr.length;i++){
							$(".executionSelect input[value='"+pointArr[i]+"']").prop('id',"activeBut-1");
						}
					}
				}
			}
		}
	});
	$(this).on("click",".executionFrequencyVal",function(){
		$(".executionFrequencyDiv").css("display","block");
	});
	$(".executionFrequencyDiv").on("click",".cancel-common-btn",function(){
		$(".executionFrequencyDiv").css("display","none");
	});
	//确认按钮
	$(".executionFrequencyDiv").on("click",".confirm-common-btn",function(){
		var resultExpression="";
		//记录当前信息
		var type = $("#activeBtn").val();
		cronExpression[type]=dealWithData();
		for(var i=0;i<6;i++){
			if(cronExpression[i.toString()] == undefined){
				resultExpression += "* ";
			}else{
				resultExpression += cronExpression[i.toString()] + " ";
			}
		}
		var temp = resultExpression.split(" ");
		if(temp[3]==temp[5]){
			temp[5] = "?";
		}else{
			if(temp[3]!="*"){
				temp[5] = "?";
			}else{
				temp[3] = "?";
			}
		}
		resultExpression = "";
		for(var i=0;i<temp.length;i++){
			resultExpression += temp[i] + " ";
		}
		resultExpression = resultExpression.substr(0,resultExpression.length-1);
		$(".executionFrequencyVal").val(resultExpression);
		$(".executionFrequencyDiv").css("display","none");
		$("[id=activeBut-1]").removeAttr("id");
		$("#activeBut").removeAttr("id");
		$(".executionType button[value='0']").prop("id","activeBut");
		$(".executionTitle input:first").prop("checked","checked");
		$(".executionTitle select:first option[selected='selected']").removeAttr("selected");
		$(".executionTitle select:first").find("option[value='0']").attr("selected",true);
		$(".executionTitle select:last option[selected='selected']").removeAttr("selected");
		$(".executionTitle select:last").find("option[value='1']").attr("selected",true);
		
	});
	
	
	var confNum;
	var numArr = [];
	var addressArr = [];
	var command;
	$(".addJobTable").on("click",".confirm",function(){
		if($(".taskNameVal").val()==""||$(".jobTypeVal").val()==""||$(".createTimeVal").val()==""||
				$(".finishedTimeVal").val()==""||$(".executionFrequencyVal").val()==""){
			layer.msg($(".tip1-il8n").val());
			return;
		}
		if($(".jobTypeVal").val()=="com.hws.oa.core.quartz.model.UpdateCodeJob"){
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
				  			confNum = num-1;
				  			addTask();
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

			
		}else if($(".jobTypeVal").val()=="com.hws.oa.core.quartz.model.DeleteJob"){
			addTask();
		}else if($(".jobTypeVal").val()=="com.hws.oa.core.quartz.model.PackageJob"){
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
				  			confNum = num-1;
				  			
				  			var data = {
				  					num :confNum,
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
				  				  			numArr = [];
				  				  			addressArr = [];
				  							$("table input:checkbox").each(function(){
				  								if($(this).prop("checked")){
				  									numArr.push($(this).parent().prev().prev().text());
				  									addressArr.push($(this).parent().prev().text());
				  								}
				  							});
				  				  			addTask();
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
		
		}
		
		
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
	
	function addTask(){
		var startTime =  Date.parse(new Date($(".createTimeVal").val()));
		var endTime =  Date.parse(new Date($(".finishedTimeVal").val()));
		var data = {
				JOB_NAME:$(".taskNameVal").val(),
				JOB_CLASS_NAME:$(".jobTypeVal").val(),
				START_TIME:startTime,
				END_TIME:endTime,
				CRON_EXPRESSION:$(".executionFrequencyVal").val(),
				num:confNum,
				numArr:numArr,
				addressArr:addressArr,
				command:$(".mvnCommand").val()
			};
			var url = "/timedtask/addNewTask";
			var s_function = function(data){
				if(data.flag){
					$(".nodata").css("display","none");
					
					$(".dataTable tbody").append("<tr> " +
							"<td style='display:none;'>"+data.result.job_CLASS_NAME+"</td>" +
							"<td>"+data.result.job_NAME+"</td>" +
							"<td>"+getSmpFormatDateByLong(data.result.start_TIME,true)+"</td>" +
							"<td>"+getSmpFormatDateByLong(data.result.end_TIME,true)+"</td>" +
							"<td>"+$("."+data.result.trigger_STATE+"-il8n").val()+"</td>" +
							"<td class='edit'>"+$(".edit-il8n").val()+"</td></tr>");
				}else{
					if(null != data.message){
						layer.msg($("."+data.message+"-il8n").val());
					}else{
						layer.msg("The server is error!!!");
					}
				}
			}
			var e_function = function(){
				layer.msg("The server is error!!!");
			}
			$.arrayAjax(url,data,s_function,e_function);
			removeAddPanel();
	}
	
	
	
	
	
	
	
	//点击菜单外菜单消失
	var jobName;
	$(this).click(function(e){
	 if(!$(e.target).is('.editMenu')&&!$(e.target).is('.edit')){
		 $(".editMenu").remove();
	    }
	});
	//edit function
	
	var jobClass;
	var jobState;
	$(".dataTable").on("click",".edit",function(e){
		$(".editMenu").remove();
		var data = "<li value='1'>"+$(".seeDetails-il8n").val()+"</li>" +
				"<li value='2'>"+$(".delete-il8n").val()+"</li>";
		var menuItem = $(this).prev().text();
		if(menuItem == $(".PAUSED-il8n").val()){
			data+="<li value='4'>"+$(".open-il8n").val()+"</li>";
		}else if(menuItem == $(".ACQUIRED-il8n").val()||menuItem==$(".SCHEDULING-il8n").val()){
			data+="<li value='3'>"+$(".PAUSED-il8n").val()+"</li>";
		}
		$(".container").append("<ul class='editMenu'>"+data+"</ul>");
		$(".editMenu").css({
			   top: e.pageY,
			   left: e.pageX - 260,
			  });
		jobName = $(this).prev().prev().prev().prev().text();
		jobClass = $(this).prev().prev().prev().prev().prev().text();
		jobState = $(this).prev().text();
	});
	//menu操作
	//var time = null;
	$(this).on("click",".editMenu li",function(){
		var operate = $(this).text();
		var names = [jobName];
		var type = $(this).val();
		var data = {
				names: names,
				type: type,
				jobClass:jobClass
			};
		//var openFlag = 0;
		var url = "/timedtask/menuOperate";
		var s_function = function(data){
			if(data.flag){
				var obj = $(".dataTable tr td:contains('"+jobName+"')");
				if(type=="2"){
					obj.parent().remove();
					if($(".edit")){
						$(".nodata").css("display","block");
					}
				}else if(type == "3"){
					obj.next().next().next().text(operate);
				}else if(type == "1"){
					if(data.dataJson == null){
						alert($(".nodata-il8n").val());
						return;
					}
					var json  = eval("("+data.dataJson.jobdata+")");
					var dataValue="<div style='margin-left:20px;' class='jobDataVal'>";
					if(json.type=="delete"){
						dataValue += "<div>"+json.deleteInfo+"</div>"+
						"</div>";
					}else if(json.type=="update"){
						dataValue += "<div>"+json.updateInfo+"</div>"+
						"</div>";
					}else if(json.type=="package"){
						dataValue += "<div>"+json.updateInfo+"</div>"+
						"<div>"+json.packageInfo+"</div>"+
						"</div>";
					}
					 
						layer.open({
							  title: $(".dataDisplay-il8n").val(),
							  content: dataValue,
							  area: ['700px', '400px'],
							  type: 1,
							  maxmin: true,            //最大化按钮
						  	  anim:3,                    //动画
						  	  shade: [0.8, '#393D49'],//遮罩层
						  	  cancel: function(index, layero){ 
						  	    layer.close(index);
						  	  }
							});
				} 
			}else{
				layer.msg(operate+$(".failure-il8n").val());
			}
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		$.arrayAjax(url,data,s_function,e_function);
	});
	//datatable 样式
	$(".dataTable tbody").on("click","tr",function(){
		if($(this).attr("class")!="active-tr"){
			$(this).addClass("active-tr");
		}else{
			$(this).removeClass();
		}
	});
	$(".titleButton").on("click",".deleteButton",function(){
		var temp = [];
		$(".active-tr").each(function(){
			temp.push($(this).find("td:nth-child(2)").text());
		});
		if(temp.length == 0){
			layer.msg($(".pSelectTask-il8n").val());
			return;
		}
		var data = {
				names: temp,
				type: "2",
			};
			
		var url = "/timedtask/menuOperate";
		var s_function = function(data){
			if(data.flag){
				$(".active-tr").remove();
				if($(".edit")){
					$(".nodata").css("display","block");
				}
			}else{
				layer.msg($(".deleteFailure-il8n").val());
			}
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		$.arrayAjax(url,data,s_function,e_function);
	});
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
	    	if(null != json.name){
	    		$(".dataTable tr td:contains('"+json.name+"')").next().next().next().text($("."+json.state+"-il8n").val());
	    	}
	    	/*if(json.name==jobName&&json.state=="COMPLETE"){
	    		if(time != null){
	    			clearInterval(time);
	    		}
	    	}
	    	if(time != null&&jobName==null){
	    		clearInterval(time);
	    	}*/
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