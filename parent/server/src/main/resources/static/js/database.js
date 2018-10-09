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
	
	//拖拽 html 元素浮动了所以移动不了
	/*var doc = $(document), dl = $("div.middle"), dc = $("div.bottom");
    var sum = dl.height() + dc.height() + 
    $("div.handler").mousedown (function (e) {
    	e.preventDefault();
        var me = $(this);
        var deltaY = e.clientY 
                - 
                (parseFloat(me.css("top")) || parseFloat(me.prop("clientTop")));
        doc.mousemove(function (e){
        	
        	
        	
            var lt = e.clientY - deltaY; 
            lt = lt < 0 ? 0 : lt;
            lt = lt > sum - me.height() ? sum - me.height() : lt;
            me.css ("top", lt + "px");
            dl.height(lt);
            dc.height(sum-lt-me.width());
        });
    }).height();

    doc.mouseup (function(){
        doc.unbind("mousemove");
    });*/
    /*doc[0].ondragstart = doc[0].onselectstart = function () 
    {
        return false;
    };*/
    
    
    //回车移动指针
	$(".panel").keydown(function(event) {
		
         if (event.keyCode == "13") {//keyCode=13是回车键
			  var count = $(".num li").length;
			  count++;
              $(".num").append("<li>"+count+"</li>");
			  $(this).append("<div  contenteditable=\"true\"></div>");
			  var li = $(".num").find(".libg").next();
			  var input = $(this).find(".inputBg").next();
			  $(".libg").removeClass();
			  $(".inputBg").removeClass();
			  li.addClass("libg");
			  input.addClass("inputBg");
			  $(".inputBg").empty();
			  $(".inputBg").focus(); //获取焦点
         }
		 if(event.keyCode == "8"){ //退格键
			 if($(".inputBg").val() == ""){ //当input框中没有数据的时候使用退格键则返回上一个input
				  if($(".num li").length != 1){
					  var prevLi = $(".num").find(".libg").prev();
					  var prevInput = $(this).find(".inputBg").prev();
					  var nLi = $(".num").find(".libg").next();
					  var nInput = $(this).find(".inputBg").next();
					  $(".libg").removeClass();
					  $(".inputBg").remove();
					  $(".num li:last-child").remove();
					  prevLi.addClass("libg");
					  prevInput.addClass("inputBg");
					  if(!$(".num li").hasClass("libg")){
						  nLi.addClass("libg");
						  nInput.addClass("inputBg");
					  }
					  $(".inputBg").focus(); //获取焦点
				  }
				  
			 }
		 }
    });

	// 纵向滚动条
	$(".panel").scroll(function(){
		$(".num").scrollTop($(this).scrollTop()); 
	});
	//鼠标移动指针
	$(".panel").on("focus","div",function(){
		$(".inputBg").removeClass();
		$(this).addClass("inputBg");
		var currentIndex = $(this).index();
		$(".libg").removeClass();
		$(".num").find("li").each(function(){
			if($(this).index() == currentIndex){
				$(this).addClass("libg");
			}
		});
	});
	
});

