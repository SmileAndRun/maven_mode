$(function(){
	$(".menu").on("click","ul li",function(){
		$(".menu ul li").find(".active").removeClass("active");
		$(this).addClass("active");
	})
})