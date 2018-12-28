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
					//alert(massage);
					$("#MessageBoard").append(massage);
					//$("#hwsData").append(massage);
					
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
		var currentdate = year + seperator1 + month + seperator1 + strDate +"&nbsp;&nbsp;&nbsp;&nbsp;"+hour+":"+min;
		return currentdate;
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
		marginLeft: (w_width - $(".announcement").width()-$("#MessageBoard").width())/2+$(".announcement").width(),
	});
	
}
