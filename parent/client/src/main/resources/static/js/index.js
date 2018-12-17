var barr={}
var num = 0;
var s_width = $(window).width();
var s_height = $(window).height();
$(function(){
	
	//弹幕
	var length = $('.scroll li').length;
	var job = setInterval(function() {
		barr.initBarr();
	}, 500);
	if(num >= length){
		clearInterval(job);
	}
	//关闭弹幕
	$(this).click(function(){
		$('.scroll').css("display","none");
	});
	//设置图片大小
	$(".top img").css({
		width: s_width-47,
		height:(s_width-47)/2.243
	});
	//点击图片放大
	//公式：偏移量=点击点与原图中心的距离 + 放大后需要偏移的距离
	//放大后需要偏移的距离=点击点在原图中的比例X放大倍数
	//思路：先将点击点移动到中心位置然后放大图片，然后根据偏移比例进行移动
	$('.top').on("click","img",function(e){
		var difWidth = s_width/2 - e.pageX;
		var difHeight = s_height/2 - e.pageY;
		$(this).css({
			marginLeft: difWidth -(e.pageX-(s_width-$(this).width())/2),
			marginTop : difHeight - (e.pageY-20),
			width: $(this).width()*2,
			height: $(this).height()*2
		});
	});
	
});
barr.initBarr = function (){
	barr.scroll(num);
	num++;
}
barr.scroll = function(num){
	var arrColor = [ '#5dd9ff', '#fbe091', '#ff0', '#b5d8f4', '#0f0', '#0ff',
		     			'#83dd57', '#fff', '#b4f4ff', '#ccc', '#fff' ];
	var height = $(window).height()- 30;
	$('.scroll li').eq(num).css('color',
			arrColor[parseInt(10 * Math.random())]);
	
	$('.scroll li').eq(num).css('top',parseInt(height * Math.random()) );
	$('.scroll li').eq(num).animate({
		'left' : -300
	}, 30000, function() {
		$('.scroll li').eq(num).css('left', '100%');
	});
}
