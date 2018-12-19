var barr={}
var num = 0;
var s_width = $(window).width();
var s_height = $(window).height();
var name = "";
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
		name = $(this).attr("alt");
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
			//动画特效
			var y = 0;
			var tipWidth = 0;
			var num = 0;
			var timeDiv = setInterval(function () {
				$(".tipOfName").css({
					left: 34.5*Math.sqrt(y),
					top: y ,
					width: tipWidth,
					height: tipWidth/3*2,
					opacity: num,
					paddingTop: num*20
				});
			    y+= 20;
			    tipWidth +=12;
			    num += 0.04;
			    //兼容不同屏幕屏幕高度为635，移动300即可
			    if(y>s_height/2){
			    	clearInterval(timeDiv);
			    	$(".tipOfName").append("<input type='text' /><br></br><button class='nameConfirm'>"+$(".confirm-i18n").val()+"</button>");
			    	$(".tipOfName input").focus();
			    }
			}, 50);
		}
	});
	var tipFlag = false;
	$(".tipOfName").on("click",".nameConfirm",function(){
		tipFlag = false;
		var inputName = $(this).parent().find("input").val();
		$(".tipOfName").empty();
		$(".tipOfName").css({
			transform: "rotateY(360deg)",
			transition: "all 1s"
		});
		if(name == inputName){
			$(".tipOfName").append("<p>"+$(".response3-i18n").val()+"</p>");
		}else{
			tipFlag = true;
			$(".tipOfName").append("<p class='response1'>"+$(".response1-i18n").val()+"</p>")
		}
	});
	var pNum = 0;
	$(".container").on("click",".tipOfName",function(){
		if(tipFlag){
			
			$(".tipOfName p").css({
				transition: "all 1s",
				opacity: 0
			});
			if($(".response").attr("opacity")==0){
				$(".tipOfName").append("<p class='response'>"+$(".Iam-i18n").val()+"</p>");
			}else{
				$(".tipOfName").append("<p class='response'>"+$(".response-i18n").val()+"</p>");
			}
			
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
