$(function(){
	$(".titleButton").on("click",".createButton",function(){
		$(".shadeDiv").css("display","block");
		$(".addDiv").css("display","block");
	});
	$(".addJobTable").on("click",".cancel",function(){
		$(".shadeDiv").css("display","none");
		$(".addDiv").css("display","none");
		$(".taskNameVal").val("");
		$(".finishedTimeVal").val("");
		$(".createTimeVal").val("");
		$(".executionFrequencyVal").val("");
	})
	initDateTimePicker(".startTimeDiv");
	initDateTimePicker(".endTimeDiv");
	//bootstrap时间控件方法
	function initDateTimePicker(id){
		$(id).datetimepicker({
	        format: "yyyy-mm-dd  HH:mm:ss",
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
	//执行频率的类型选择
	var cronExpression = {};
	$(".executionType").on("click","button",function(){
		//数据处理
		var type = $("#activeBtn").val();
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
		cronExpression[type]=value;
		
		//cronExpression[type]=
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
	});
	$(this).on("click",".executionFrequencyVal",function(){
		$(".executionFrequencyDiv").css("display","block");
	});
	$(".executionFrequencyDiv").on("click",".cancel-common-btn",function(){
		$(".executionFrequencyDiv").css("display","none");
	});
	$(".executionFrequencyDiv").on("click",".confirm-common-btn",function(){
		var resultExpression="";
		if($.isEmptyObject(cronExpression)){
			if($(".executionTitle input:checked").val() == 1){
				$("[id=activeBut-1]").each(function(){
					resultExpression += $(this).val()+",";
				});
				if(resultExpression.length>0){
					resultExpression = resultExpression.substr(0,resultExpression.length-1);
					resultExpression += " * * * * *"
				}else{
					resultExpression = "* * * * * *";
				}
			}else if($(".executionTitle input:checked").val() == 2){
				resultExpression = $(".executionTitle select:first").val() + "/" + $(".executionTitle select:last").val()
				+" * * * *　*";
			}else{
				resultExpression = "* * * * *　*";
			}
			$(".executionFrequencyVal").val(resultExpression);
			return ;
		}
		for(var i=0;i<6;i++){
			if(cronExpression[i.toString()] == undefined){
				resultExpression += "* ";
			}
			resultExpression += cronExpression[i.toString()] + " ";
		}
		resultExpression = resultExpression.substr(0,resultExpression.length-1);
		$(".executionFrequencyVal").val(resultExpression);
	});
})