var menu = {};
//滚动条事件
menu.menuScroll = function(){
	if($('html,body').scrollTop()!=0){
		$(".rebackToTop").css({
			display: "block",
		});
	}else{
		$(".rebackToTop").css({
			display: "none",
		});
	}
}
//中英文国际化
menu.changeLanguage = function (lan){
	$.Ajax({
		type:"post",
		url:"/i18n/selectLanguage",
		contentType:"application/json;charset=utf-8",
		dataType:"json",
		data:{"language":lan},
		success:function(data){
			location.reload();
		}
	});
}
//menu事件
menu.menuButtonClick = function (){
	if($(".menuButton").find("i").attr("class")!="fa fa-times"){
		$(".menuButton").find("i").removeClass();
		$(".menuButton").find("i").addClass("fa fa-times");
		$('.menuContent').animate({left: $(window).width()-200},'slow');
	}else{
		$(".menuButton").find("i").removeClass();
		$(".menuButton").find("i").addClass("fa fa-bars");
		$('.menuContent').animate({left: "100%"},'slow');
	}
}
menu.menuMouseOver = function (){
	if($(".menuButton").find("i").attr("class")!="fa fa-times"){
		$(".menuButton").find("i").removeClass();
		$(".menuButton").find("i").addClass("fa fa-arrow-left");
	}
}
menu.menuMouseOut = function (){
	if($(".menuButton").find("i").attr("class")!="fa fa-times"){
		$(".menuButton").find("i").removeClass();
		$(".menuButton").find("i").addClass("fa fa-bars");
	}
}
menu.rebackToTop = function(){
	$('html,body').animate({scrollTop:0},'slow');
};
menu.menuClick = function(type){
	if(type=="1"){
		
	}
}
//待定
menu.initContent = function(){
	
}