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
	function enterKey(event){
		
	}
	
	$(".panel").keydown(function(event) {
		
		 //处理回车光标后面的数据换行效果
		 var divContent = $(this).find(".inputBg").html();//获取div全部内容
		 var selection = getSelection();
		 var range = selection.getRangeAt(0); 
		 // 获取光标位置
		 var rangeStartOffset = range.startOffset;
		 var firstContent = divContent.substring(0,rangeStartOffset);
		 var lastContent = divContent.substring(rangeStartOffset,divContent.length);
		 
         if (event.keyCode == "13") {//keyCode=13是回车键
				
			  event.preventDefault();//阻止默认事件样式发生
			  var count = $(".num li").length;
			  count++;
              $(".num").append("<li>"+count+"</li>");
			  $(this).append("<div  contenteditable=\"true\"></div>");
			  var li = $(".num").find(".libg").next();
			  var input = $(this).find(".inputBg").next();
			  
			  
			  $(".inputBg").html(firstContent);
			  
			  $(".libg").removeClass();
			  $(".inputBg").removeClass();
			  li.addClass("libg");
			  input.addClass("inputBg");
			  $(".inputBg").focus(); //获取焦点
			  $(".inputBg").html(lastContent);
			 
			  
			  
			  
         }
		 
		 if(event.keyCode == "8"){ //退格键
			 if(rangeStartOffset == 0){ //当input框中没有数据的时候使用退格键则返回上一个input
				 if($(".num li").length != 1){
					  event.preventDefault();//阻止默认事件样式发生
					 
					  var prevLi = $(".num").find(".libg").prev();
					  var prevInput = $(this).find(".inputBg").prev();
					  var nLi = $(".num").find(".libg").next();
					  var nInput = $(this).find(".inputBg").next();
					  $(".libg").removeClass();
					  //获取删除行的内容
					  var delContent = $(".inputBg").html();
					  $(".inputBg").remove();
					  $(".num li:last-child").remove();
					  prevLi.addClass("libg");
					  prevInput.addClass("inputBg");
					  if(!$(".num li").hasClass("libg")){
						  nLi.addClass("libg");
						  nInput.addClass("inputBg");
					  }
					  po_Last_Div($(".inputBg")[0]);
					  $(".inputBg").after(delContent);
				  }
				  
			 }
		 }
		 if(event.keyCode == "38"){ //上箭头
			 if($(".libg").text() != 1){
				 var prevLi = $(".num").find(".libg").prev();
				 var prevInput = $(this).find(".inputBg").prev();
				 $(".libg").removeClass();
				 $(".inputBg").removeClass();
				 prevInput.focus();
				 prevLi.addClass("libg");
				 prevInput.addClass("inputBg");
			 }
			 
		 }
		 
		 if(event.keyCode == "40"){ //下箭头
			 if($(".num li").length != $(".libg").text()){
				 var nLi = $(".num").find(".libg").next();
				  var nInput = $(this).find(".inputBg").next();
				  $(".libg").removeClass();
				  $(".inputBg").removeClass();
				  nLi.addClass("libg");
				  nInput.addClass("inputBg");
				  $(".inputBg").focus();
			 }
		 }
		 
    });
	//解决光标不能显示在最后面的问题
	function po_Last_Div(obj) {
        if (window.getSelection) {//ie11 10 9 ff safari
            obj.focus(); //解决ff不获取焦点无法定位问题
            var range = window.getSelection();//创建range
            range.selectAllChildren(obj);//range 选择obj下所有子内容
            range.collapseToEnd();//光标移至最后
        }
        else if (document.selection) {//ie10 9 8 7 6 5
            var range = document.selection.createRange();//创建选择对象
            range.moveToElementText(obj);//range定位到obj
            range.collapse(false);//光标移至最后
            range.select();
        }
    }
	
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
	
	// 禁止粘贴
	document.onpaste = function(){ 
		return false; 
	};
	
});

