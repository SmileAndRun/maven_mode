$(function(){
	$(".sidebar").on("click","ul li",function(){
		$(".sidebar ul li").find(".active").removeClass("active");
		$(this).addClass("active");
	})
})