$(function(){
	$(".titleButton").on("click",".createButton",function(){
		var data = "";
		data = "<table><tr><td><lable for='taskNameVal'>"+$(".taskName").val()+"</lable></td><td><input type='text' class='taskNameVal'/></td></tr>" +
				"<tr><td><lable for='jobTypeVal'>"+$(".jobType").val()+"</lable></td><td><input type='text' class='jobTypeVal'/></td></tr>" +
				"<tr><td><lable for='createTimeVal'>"+$(".createTime").val()+"</lable></td><td><input type='text' class='createTimeVal'/></td></tr>" +
				"<tr><td><lable for='finishedTimeVal'>"+$(".finishedTime").val()+"</lable></td><td><input type='text' class='finishedTimeVal'/></td></tr>" +
				"<tr><td><lable for='executionFrequencyVal'>"+$(".executionFrequency").val()+"</lable></td><td><input type='text' class='executionFrequencyVal'/></td></tr>" +
				"</table>"
		layer.open({
			  title: $(".add").val()
			  ,content: data
			  ,area: ['500px', '300px']
			});   
		$(".layui-layer").css("border-radius", "8px");
	});
})