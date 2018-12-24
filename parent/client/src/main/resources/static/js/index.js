var barr={}
var num = 0;
var s_width = $(window).width();
var s_height = $(window).height();
var name = "";
$(function(){
	//初始化
	barr.initHomePage();
	//关闭弹幕
	$(this).click(function(){
		$('.scroll').css("display","none");
		$(".container").css("marginTop","0px");
	});
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
			    //兼容不同屏幕屏幕，高度为635，移动300即可
			    if(y>s_height/2){
			    	clearInterval(timeDiv);
			    	$(".tipOfName").append("<input type='text' /><br></br><button class='nameConfirm'>"+$(".confirm-i18n").val()+"</button>");
			    	$(".tipOfName input").focus();
			    }
			}, 50);
		}
	});
	var tipFlag = false;
	var pNum = 0;
	var disFlag = false;
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
			disFlag = true;
		}else{
			tipFlag = true;
			pNum = 0;
			$(".tipOfName").append("<p class='response1'>"+$(".response1-i18n").val()+"</p>")
		}
	});
	
	$("body").on("click",".tipOfName",function(){
		if(tipFlag){
			pNum++;
			$(".tipOfName p").css({
				transition: "all 1s",
				opacity: 0
			});
			if(pNum==1){
				$(".tipOfName").append("<p class='response'>"+$(".response-i18n").val()+"</p>");
				
			}else if(pNum ==2){
				$(".tipOfName").append("<p class='response'>"+$(".Iam-i18n").val()+name+"</p>");
			}else if(pNum == 3){
				$(".tipOfName").empty();
				$(".tipOfName").append("<p>"+$(".Guess1-i18n").val()+"</p>");
				$(".tipOfName").css({
					transition: "all 1s",
					opacity: 0,
					width: "0px",
					height: "0px",
					left: "0px",
					top: "0px"
				});
			}
		}else{
			if(disFlag){
				disFlag = false;
				$(".tipOfName").empty();
				$(".tipOfName").append("<p>"+$(".Guess1-i18n").val()+"</p>")
				$(".tipOfName").css({
					transition: "all 1s",
					opacity: 0,
					width: "0px",
					height: "0px",
					left: "0px",
					top: "0px"
				});
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
			$(".tipOfName").css({
				transition: "all 1s",
				opacity: 0,
				width: "0px",
				height: "0px",
				left: "0px",
				top: "0px"
			});
		 }
	});
	//遮罩事件
	//on可以解决 append节点无法触发事件
	$(".images").on("mouseover",".shadeBar",function (){  
		$(this).css({
			opacity: 1
		});  
		var barLength=$('.shadeBar li').length;
		var barNum = 0;
		//记录当前对象
		var obj = $(this);
		var url = "/user/getImagesBar";
		var data = {
				imagesId: obj.find("input").val(),
			};
		var rs_function = function(result){
			if(result.imgBarFlag){
				var content = [];
				for(var i=0;i<result.imgBarList.length;i++){
					content.push(result.imgBarList[i].content);
				}
				$.init3dRotate(obj.find("canvas")[0],content);
			}
		}
		var re_function = function(result){
			layer.msg($(".tip3").text());
		}
		$.commonAjax(url,data,rs_function,re_function);
    });
	$(".images").on("mouseout",".shadeBar",function (){ 
		$(this).css({
    		opacity: 0
    	});
    	jQuery.stopLoop();
	});
	//待定
	$(".barFlag").on("click","i",function(){
		if($(this).attr("class")=="fa fa-toggle-on fa-2x"){
			$(this).remove();
			$(".barFlag").append("<i class='fa fa-toggle-off fa-2x'  aria-hidden='true'></i>");
		}else{
			$(this).remove();
			$(".barFlag").append("<i class='fa fa-toggle-on fa-2x'  aria-hidden='true'></i>");
		}
	});
	$(".images").on("click",".shadeBar",function(){
		$(".addBarDiv").css({display:"block"});
		$(".globalBackground").css({display:"block"});
		$(".container").css({display:"none"});
		var imgSrc = $(this).prev().find("img").attr("src");
		$(".currentBar").append("<div></div><img src='"+imgSrc+"'/><div></div>"); 
		var img_width = $(".currentBar").find("img").width();
		var img_height = $(".currentBar").find("img").height();
		//margin: 20,输入框 高34
		if(s_height > (img_height + 20 + 34)){
			$(".addBarDiv").css({
				top: (s_height - img_height - 20 - 34)/2,
			});
		}else{
			$(".addBarDiv").css({
				top: "20px",
				position: "absolute",
			});
		}
		//设置图片大小
		if(img_width>=s_width){
			$(".currentBar").find("img").css({
				width: s_width -40,
				height: img_height * (s_width-40) / img_width
			});
			$(".addBarDiv").css({
				left: "50%",
				marginLeft: -(s_width -40)/2,
			});
		}else{
			$(".currentBar").find("img").css({
				width: img_width -40,
				height: img_height * (img_width-40) / img_width
			});
			$(".addBarDiv").css({
				left: "50%",
				marginLeft: -$(".currentBar").find("img").width()/2,
			});
		}
		//设置弹幕遮罩大小
		$(".currentBar").find("div").css({
			width: (s_width-$(".currentBar").find("img").width())/2,
			height: $(".currentBar").find("img").height()
		});
		//设置背景大小
		$(".globalBackground").css({height:$(".addBarDiv").height()+20});
		
	});
	var totalPageNum = parseInt($(".pages").val());
	$(this).on("click",".pagination li:not(.active,.turndiv)",function(){
		var num = 1;
		var pointNum  = $(".active").text();
		var pageNums = $(this).text();
		if(pageNums == "首页"||pageNums == "home page"){
			num = 1;
			if(totalPageNum>=10){
				getPageNum(1,10,1);
			}else{
				getPageNum(1,totalPageNum,1);
			}
			
		}else if(pageNums == "上一页"||pageNums=="previous page"){
			var preNum = pointNum-1;
			num = preNum;
			if(preNum%10==0){//说明为一排数字的第一个数即要更换页码
				getPageNum(preNum-9,preNum,preNum);
			}else{
				var integer = Math.floor(pointNum/10);
				var integer1 = Math.floor(preNum/10);
				var integer2 = Math.floor(totalPageNum/10);
				if(integer1 == integer2){//表示上一页仍然和最后一页在同一行
					getPageNum(integer*10+1,totalPageNum,preNum);
				}else{
					getPageNum(integer*10+1,integer*10+10,preNum);
				}
			}
			
		}else if(pageNums == "下一页"||pageNums=="next page"){
			
			var nextNum = pointNum - -1;
			num = nextNum;
			if(pointNum%10==0){//说明为一排数字的最后一个数即要更换页码
				var value1 = Math.floor(num/10);
			    var value2 = Math.floor(totalPageNum/10);
				if(value1==value2){
					getPageNum(num,totalPageNum,num);
				}else{
					getPageNum(num,num+9,num);
				}
				
			}else{
				var integer1 = Math.floor(pointNum/10);
				var integer2 = Math.floor(num/10);
				var integer3 = Math.floor(totalPageNum/10);
				if(integer2==integer3){//表示上一页仍然和最后一页在同一行
					getPageNum(integer1*10+1,totalPageNum,num);
				}else{
					getPageNum(integer1*10+1,integer1*10+10,num);
				}
			}
			
		}else if(pageNums == "末页"||pageNums=="last page"){
			num = totalPageNum;
			if(num%10==0){
				getPageNum(num-9,num,num)
			}else{
				getPageNum(Math.floor(num/10)*10+1,num,num);
			}
			
		}else{
			num = pageNums;
			$(".active").removeClass("active");
			$(this).addClass("active");
		}
		//样式处理
		validate(num);
		//页码请求
		ajaxOfPagination(num);
		});
	function ajaxOfPagination(num){
		var url = "/user/pagination";
		var data = {
			pageNum: num
			};
		var rs_function = function(result){
			$(".images").empty();
			for(var i=0;i<result.pageInfo.list.length;i++){
				$(".images").append("<dl><dd><img src='"+result.pageInfo.list[i].imageAddress+"'/></dd>" +
						"<dd class='shadeBar'>" +
						"<input type='hidden' value='"+result.pageInfo.list[i].imageId+"'/>" +
						"<canvas style='width:250px;height:200px;'></canvas>" +
						"</dd><dt>"+result.pageInfo.list[i].imageAlias+"</dt></dl>");
			}
			$(".images").append("<button class='fa fa-plus fa-5x addImages' aria-hidden='true'></button>");
			
		}
		var re_function = function(result){
			layer.msg($(".tip3-i18n").text());
		}
		$.commonAjax(url,data,rs_function,re_function);
	}
	//验证首页上一页下一页尾页的显示状况
	function validate(pageNums){
		if(pageNums != 1){
			if(pageNums == totalPageNum){
				$(".homePage a").css("display","block");
				$(".previousPage a").css("display","block");
				$(".nextPage a").css("display","none");
				$(".endPage a").css("display","none");
			}else{
				$(".homePage a").css("display","block");
				$(".previousPage a").css("display","block");
				$(".nextPage a").css("display","block");
				$(".endPage a").css("display","block");
			}
			
		}else{
			$(".homePage a").css("display","none");
			$(".previousPage a").css("display","none");
			$(".nextPage a").css("display","block");
			$(".endPage a").css("display","block");
		} 
	}
	//获取页码
	function getPageNum(startNum,endNum,currentNum){
		$(".pagination").empty();
		$(".pagination").append("<li class=\"homePage\"><a>"+$(".homepage-i18n").val()+"</a></li>"
				+"<li class=\"previousPage\"><a>"+$(".prevpage-i18n").val()+"</a></li>");
		for(var i = startNum;i<= endNum;i++){
			if(i == currentNum){
				$(".pagination").append("<li  class=\"active\"><a>"+ i +"</a></li>");
			}else{
				$(".pagination").append("<li ><a>"+ i +"</a></li>");
			}
			
		}
		$(".pagination").append("<li class=\"nextPage\"><a>"+$(".nextpage-i18n").val()+"</a></li>"
			    + "<li class=\"endPage\"><a>"+$(".endpage-i18n").val()+"</a></li>"); 
	}
	//页码跳转
	$(".turndiv").on("click",".turn",function(){
		var pageNums = $(".turnNum").val();
		if(pageNums>totalPageNum){
			pageNums = totalPageNum;
		}
		if(pageNums<=1){
			pageNums = 1;
			if(totalPageNum>=10){
				getPageNum(1,10,1);
			}else{
				getPageNum(1,totalPageNum,1);
			}
		}else if(pageNums>=totalPageNum){
			pageNums = totalPageNum;
			if(pageNums%10==0){
				getPageNum(pageNums-9,pageNums,pageNums)
			}else{
				getPageNum(Math.floor(pageNums/10)*10+1,pageNums,pageNums);
			}
			
		}else{
			if(pageNums%10==0){
				getPageNum(pageNums-9,pageNums,pageNums);
			}else{
				if(pageNums/10==0){
					getPageNum(1,10,pageNums);
				}else{
					var val1 = Math.floor(pageNums/10);
					var val2 = Math.floor(totalPageNum/10);
					var temp = Math.floor(pageNums/10)*10+1;
					if(val1 == val2){
						getPageNum(temp,totalPageNum,pageNums);
					}else{
						getPageNum(temp,temp+9,pageNums);
					}
				}
			}
		}
		validate(pageNums);
		ajaxOfPagination(pageNums);
	});
	//上传图片
	$(".images").on("click",".addImages",function(){
		$(".uploadDiv").css({
			display: "block",
			top: (s_height - $(".uploadContent").height())/2
		});
		$(".uploadDiv").on("click","#picker",function(){
			$.EexternalInterface();
		});
	});
});
barr.initBarr = function (){
	barr.scroll(num);
	num++;
}
//总结整合滚动方法
//direction为字符串
barr.commonScroll = function(barHeight,obj,direction,){
	var arrColor = [ '#5dd9ff', '#fbe091', '#ff0', '#b5d8f4', '#0f0', '#0ff',
		     			'#83dd57', '#fff', '#b4f4ff', '#ccc', '#fff' ];
	
	
	obj.eq(num).css('color',
			arrColor[parseInt(10 * Math.random())]);
	
	obj.eq(num).css('top',parseInt(barHeight * Math.random()) );
	obj.eq(num).animate({
		direction : -300
	}, 30000, function() {
		obj.eq(num).css(direction, '100%');
	});
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
barr.initHomePage=function(){
	//弹幕
	var length = $('.scroll li').length;
	var job = setInterval(function() {
		barr.initBarr();
		if(num >= length){
			clearInterval(job);
		}
	}, 500);
	
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
	$(".personMap").append("<area shape='poly' coords='"+coordinateText+"'  alt='"+$(".hewensheng-i18n").val()+"' >");
	//$(".personMap").append("<area shape='poly' coords='726,383,730,385,732,386,734,408,704,420,720,430,730,474,750,475,759,424,768,418" +
	//		",748,410,750,385,755,383,740,379,'  alt='"+$(".hewensheng-i18n").val()+"' >");
	//设置小图片的位置
	$(".images").css({
		paddingLeft: (s_width - 250*4-100)/2-20
	});
	//初始化页码
	if($(".pages").val()==0){
		$(".pagination").css("display","none");
	}else{
		$(".previousPage").next().addClass("active");
		$(".homePage a").css("display","none");
		$(".previousPage a").css("display","none");
		if($(".active").text()==$(".pages").val()){
			$(".endPage a").css("display","none");
			$(".nextPage a").css("display","none");
		}
	}
	
}
