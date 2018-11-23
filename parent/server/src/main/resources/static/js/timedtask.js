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
	function initDateTimePicker(id){
		$(id).datetimepicker({
	        format: "yyyy-mm-dd  HH:mm:ss",
	        showMeridian: true,
	        autoclose: true,
	        todayBtn: true,
	        pickerPosition: "bottom-left"
	    });
	}
	
	
})