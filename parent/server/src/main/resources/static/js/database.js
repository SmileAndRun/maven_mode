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
    
	//replace( /<[^>]*>/g, "" );清除粘贴样式
	
	$(".panel").keydown(function(event) {
		var obj = $(".inputBg");
		if($(".database-content div").hasClass("inputBg")){
			if(obj.text().indexOf(" ")== -1){
				console.log(isKeyWord(obj.text()));
			}else{
				
			}
		}
		
		
         if (event.keyCode == "13") {//keyCode=13是回车键
			  //event.preventDefault();//阻止默认事件样式发生
			  var count = $(".num li").length;
			  count++;
              $(".num").append("<li>"+count+"</li>");
			  var li = $(".num").find(".libg").next();
			  $(".libg").removeClass();
			  li.addClass("libg");
			  var t =  setInterval(function(){//database-content=true比较特殊，因此需要事件完成后才执行
				  var inputBg = $(".inputBg:eq(1)");
				  $(".inputBg").removeClass();
				  inputBg.addClass("inputBg");
				  clearInterval(t);
							
				},10);
			  
         }
		 
		 if(event.keyCode == "8"){ //退格键
			$(".inputBg").prev().addClass("inputBg");
			if($(".num li").length == 1){
				if($(".database-content").find("div").text() == ""){
					 event.preventDefault();//阻止默认事件样式发生
				 }
			}
			var t =  setInterval(function(){//database-content=true比较特殊，因此需要在退格键事件完成后才执行
				if($(".num li").length != 1){
					  var divLength = $(".database-content").find("div").length;
					  if(divLength < $(".num li").length){
			 			var prevLi = $(".num").find(".libg").prev();
			 			$(".libg").removeClass();
			 			$(".num li:last-child").remove();
			 			prevLi.addClass("libg");
			 		  }
				 }
				 clearInterval(t);
			},10);
		 }
		 if(event.keyCode == "46"){//del键
		   var t =  setInterval(function(){//database-content=true比较特殊，因此需要事件完成后才执行
			  var divLength = $(".database-content").find("div").length;
			  if(divLength < $(".num li").length){
	 			$(".num li:last-child").remove();
	 		  }
				clearInterval(t);
						
			},10);
		 }
		 if(event.keyCode == "38"){ //上箭头
			 if($(".libg").text() != 1){
				 var prevLi = $(".num").find(".libg").prev();
				 $(".libg").removeClass();
				 prevLi.addClass("libg");
				 var preInputBg = $(".inputBg").prev();
				 $(".inputBg").removeClass();
				 preInputBg.addClass("inputBg");
			 }
		 }
		 if(event.keyCode == "40"){ //下箭头
			 if($(".num li").length != $(".libg").text()){
				 var nLi = $(".num").find(".libg").next();
				  $(".libg").removeClass();
				  nLi.addClass("libg");
				  var nInputBg = $(".inputBg").next();
				  $(".inputBg").removeClass();
				  nInputBg.addClass("inputBg");
			 }
		 }
		 
		 /*if(event.keyCode == "32"){//空格
			 event.preventDefault();//阻止默认事件样式发生
			 //$(".inputBg").html($(".inputBg").html()+"&nbsp;");
		 }*/
		 
    });
	//解决光标不能显示在最后面的问题 (nouse)
	function po_Last_Div(obj) {
        if (window.getSelection) {//ie11 10 9 ff safari
            obj.focus(); //解决ff不获取焦点无法定位问题
            var range = window.getSelection();//创建range
            range.selectAllChildren(obj);//range 选择obj下所有子内容
            range.setStart(obj,3);//光标移至最后
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
	//获取焦点
	$(".panel").on("click","div",function(){
		
		var height = getSelectionCoords(window).y;
		var num = (height - 105)/20;
		if(num < 0){
			num = 0;
		}
		$(".libg").removeClass();
		$(".num li:eq("+ num +")").addClass("libg");
		$(".inputBg").removeClass();
		$(".database-content div:eq("+ num +")").addClass("inputBg");
		
	});

	
	// 禁止粘贴
	document.onpaste = function(){ 
		return false; 
	};
	//获取光标在input框中的位置(nouse)
	$.fn.getCursorPosition = function () {
        var el = $(this).get(0);
        var pos = 0;
        if ('selectionStart' in el) {
            pos = el.selectionStart;
        } else if ('selection' in document) {
            el.focus();
            var Sel = document.selection.createRange();
            var SelLength = document.selection.createRange().text.length;
            Sel.moveStart('character', -el.value.length);
            pos = Sel.text.length - SelLength;
        }
        return pos;
    }
	//获取光标在可编辑div中的x,y坐标
	function getSelectionCoords(win) {
	    win = win || window;
	    var doc = win.document;
	    var sel = doc.selection, range, rects, rect;
	    var x = 0, y = 0;
	    if (sel) {
	        if (sel.type != "Control") {
	            range = sel.createRange();
	            range.collapse(true);
	            x = range.boundingLeft;
	            y = range.boundingTop;
	        }
	    } else if (win.getSelection) {
	        sel = win.getSelection();
	        if (sel.rangeCount) {
	            range = sel.getRangeAt(0).cloneRange();
	            if (range.getClientRects) {
	                range.collapse(true);
	                rects = range.getClientRects();
	                if (rects.length > 0) {
	                    rect = rects[0];
	                }
	                // 光标在行首时，rect为undefined
	                if(rect){
	                    x = rect.left;
	                    y = rect.top;
	                }
	            }
	            // Fall back to inserting a temporary element
	            if ((x == 0 && y == 0) || rect === undefined) {
	                var span = doc.createElement("span");
	                if (span.getClientRects) {
	                    // Ensure span has dimensions and position by
	                    // adding a zero-width space character
	                    span.appendChild( doc.createTextNode("\u200b") );
	                    range.insertNode(span);
	                    rect = span.getClientRects()[0];
	                    x = rect.left;
	                    y = rect.top;
	                    var spanParent = span.parentNode;
	                    spanParent.removeChild(span);

	                    // Glue any broken text nodes back together
	                    spanParent.normalize();
	                }
	            }
	        }
	    }
	    return { x: x, y: y };
	}
	//关键字变色
	var keyword = "add all alter analyze and as asc asensitive " +
			"before between bigint binary blob both by call cascade case " +
			"change char character check collate column condition connection " +
			"constraint continue convert create cross current_date current_time " +
			"current_timestamp current_user cursor database databases day_hour " +
			"day_microsecond day_minute day_second dec decimal declare default " +
			"delayed delete desc describe deterministic distinct distinctrow div " +
			"double drop dual each else elseif enclosed escaped exists exit explain " +
			"false fetch float float4 float8 for force foreign from fulltext goto " +
			"grant group having high_priority hour_microsecond hour_minute hour_second " +
			"if ignore in index infile inner int int1 int2 int3 int4 int8 integer interval " +
			"into is iterate join key keys kill label leading leave left like limit linear " +
			"lines load localtime localtimestamp lock long longblob longtext loop " +
			"low_priority match mediumblob mediumint mediumtext middleint minute_microsecond " +
			"minute_second mod modifies natural not no_write_to_binlog null numeric on " +
			"optimize option optionally or order out outer outfile precision primary procedure " +
			"purge raido range read reads real references regexp release rename repeat replace " +
			"require restrict return revoke right rlike schema schemas second_microsecond select " +
			"sensitive separator set show smallint spatial specific sql sqlexception sqlstate " +
			"sqlwarning sql_big_result sql_calc_found_rows sql_small_result ssl starting straight_join " +
			"table terminated then tinyblob tinyint tinytext to trailing trigger true undo union unique " +
			"unlock unsigned update usage use using utc_date utc_time utc_timestamp values varbinary varchar " +
			"varcharacer varying when where while with write x509 xor year_month zerofill"
	function isKeyWord(value){
		if (keyword.indexOf(value) == -1){
			return false;
		}
		return true;
	}
	

});

