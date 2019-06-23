var menu = {};
$(function(){
	$(".menu").on("click","ul li",function(){
		$(".menu ul li").find(".active").removeClass("active");
		$(this).addClass("active");
	});
	
})
//中英文国际化
menu.changeLanguage = function (lan){
	var url = "/i18n/selectLanguage";
	var data = {
			language: lan
	}
	var s_function = function(){
		location.reload();
	}
	var e_function = function(){
		
	}
	jQuery.commonAjax(url,data,s_function,e_function);
	
}