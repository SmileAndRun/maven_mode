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
		$(".container").css("marginTop","0px");
		var r = 0
		var timeDiv = setInterval(function () {
			$(".tipOfName").css({left: Math.cos(r)*r, top: r});
		    r+= 10;
		    if($(".tipOfName").height()>300){
		    	clearInteval(timeDiv);
		    }
		    
		}, 50);
	});
	//设置图片大小
	$(".top img").css({
		width: s_width-46,
		height:(s_width-46)/2.243
	});
	//一下数据是在图片大小为1320,588.484375收集的,兼容不同大小图片
	var colletcionX = [726,730,732,734,704,720,730,750,759,768,748,750,755,740];
	var collectionY = [383,385,386,408,420,430,474,475,424,418,410,385,383,379];
	var coordinateText = "";
	for(var i = 0;i<colletcionX.length;i++){
		colletcionX[i] = colletcionX[i] * ($(".top img").width()/1320);
		collectionY[i] = collectionY[i] * ($(".top img").height()/588.484375);
		coordinateText += colletcionX[i]+","+collectionY[i]+",";
	}
	console.log(coordinateText);
	$(".personMap").append("<area shape='poly' coords='"+coordinateText+"'  alt='"+$(".hewensheng-i18n").val()+"' >");
	//$(".personMap").append("<area shape='poly' coords='726,383,730,385,732,386,734,408,704,420,720,430,730,474,750,475,759,424,768,418" +
	//		",748,410,750,385,755,383,740,379,'  alt='"+$(".hewensheng-i18n").val()+"' >");
	
	//点击图片放大
	//公式：偏移量=点击点与原图中心的距离 + 放大后需要偏移的距离
	//放大后需要偏移的距离=点击点在原图中的比例X放大倍数
	//思路：先将点击点移动到中心位置然后放大图片，然后根据偏移比例进行移动
	var num = 1;
	var flag = true;
	$('.personMap').on("click","area",function(e){
		if(flag){
			flag = false;
			var difWidth = s_width/2 - e.pageX;
			var difHeight = s_height/2 - e.pageY;
			$(".top img").css({
				marginLeft: difWidth -(e.pageX-(s_width-$(".top img").width())/2),
				marginTop : difHeight - (e.pageY-20),
				width: $(".top img").width()*2,
				height: $(".top img").height()*2
			});
		}
	});
	$('.top').on("click","img",function(){
		if(!flag){
			flag = true;
			$(this).css({
				marginLeft: 0,
				marginTop : 0,
				width: s_width-46,
				height: (s_width-46)/2.243
			});
		 }
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
	$(".scroll").css("marginTop","0px");
}
