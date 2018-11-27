$(function(){
	$(".titleButton").on("click",".createButton",function(){
		$(".shadeDiv").css("display","block");
		$(".addDiv").css("display","block");
	});
	$(".addJobTable").on("click",".cancel",function(){
		removeAddPanel();
	})
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
	$(".addJobTable").on("click",".confirm",function(){
		var data = {
			jobName:$(".taskNameVal").val(),
			undetermined:$(".jobTypeVal").val(),
			startDate:$(".createTimeVal").val(),
			endDate:$(".finishedTimeVal").val(),
			expression:$(".executionFrequencyVal").val(),
		};
		
		var url = "/timedtask/addNewTask";
		var s_function = function(data){
			if(data.flag){
				$(".nodata").remove();
				$(".dataTable tbody").append("<tr> " +
						"<td>"+data.result.JOB_NAME+"</td>" +
						"<td>"+data.result.qrtzTriggers.START_TIME+"</td>" +
						"<td>"+data.result.qrtzTriggers.END_TIME+"</td>" +
						"<td>"+data.result.qrtzTriggers.TRIGGER_STATE+"</td>" +
						"<td class='edit'>"+$(".edit-il8n").val()+"</td></tr>");
			}else{
				layer.msg("The server is error!!!");
			}
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		$.commonAjax(url,data,s_function,e_function);
		removeAddPanel();
	});
})