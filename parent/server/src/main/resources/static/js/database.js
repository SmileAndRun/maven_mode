$(function(){
	var height = $(window).height();
	$(".left").css("height",height);
	
	$(".resource ul").on("click",".bli,.ali",function(){
		var thisName = $(this).attr("class");
		if(thisName == "bli"){
			$(".ali").find(".rarrows").attr("class", "fa fa-caret-right rarrows");
			$(".ali").attr("class", "bli");
			$(this). removeClass();
			$(this).addClass("ali");
		}
		var arrowsName = $(this).find(".rarrows").attr("class");
		if(arrowsName == "fa fa-caret-down rarrows"){
			$(this).find(".rarrows").attr("class", "fa fa-caret-right rarrows");
		}else{
			$(this).find(".rarrows").attr("class", "fa fa-caret-down rarrows");
		}
	});
	
});