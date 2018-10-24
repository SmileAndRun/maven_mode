$(function(){
	var height = $(window).height();
	$(".left").css("height",height);
	//后台直接跳转页面携带了数据，将它储存在html，js进行处理。此时需要转换为json数据
	var databaseData = eval("("+$(".databaseData").val()+")");
	var tree = [];
	$.each(databaseData.databaseName,function(index,value){
		var parent = {};
		parent["text"] = "<i class='fa fa-database database' style='margin-right:10px;margin-left:-10px;'/>" + value;
		parent["color"] = "#636363";
		var temp = [];
		$.each(databaseData.tables,function(i,v){
			
			var jsonTemp = {};
			jsonTemp["text"] = v;
			jsonTemp["icon"] = "glyphicon glyphicon-th";
			jsonTemp["color"] = "#636363";
			var temp1 = [];
			$.each(databaseData.colums[v],function(key,value){
				var jsonTemp1 = {};
				jsonTemp1["text"] = value;
				jsonTemp1["icon"] = "glyphicon glyphicon-menu-hamburger";
				jsonTemp1["color"] = "#636363";
				temp1.push(jsonTemp1);
			});
			jsonTemp["nodes"] = temp1;
			temp.push(jsonTemp);
			
		});
		parent["nodes"] = temp;
		tree.push(parent);
	});
	//初始化数据源tree结构
	$('#tree').treeview({
		  data: tree,         // data is not optional
		  levels: databaseData.databaseName.length,
		  collapseIcon: "glyphicon glyphicon-triangle-bottom",
		  expandIcon: "glyphicon glyphicon-triangle-right",
	});
	//使用bootstrap-treeview代替
	/*$(".resource ul").on("click",".bli,.ali",function(){
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
	});*/
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
         if (event.keyCode == "13") {//keyCode=13是回车键
			  //event.preventDefault();//阻止默认事件样式发生
			  var count = $(".num li").length;
			  count++;
              $(".num").append("<li>"+count+"</li>");
			  var li = $(".num").find(".libg").next();
			  $(".libg").removeClass();
			  li.addClass("libg");
			  var t =  setInterval(function(){//database-content=true比较特殊，因此需要事件完成后才执行
				 $(".suggestion").empty();
				 $(".suggestion").css("display","none");
				  
				  var inputBg = $(".inputBg:eq(1)");
				  $(".inputBg").removeClass();
				  inputBg.addClass("inputBg");
				  clearInterval(t);
							
				},10);
         }else if(event.keyCode == "8"){ //退格键
			//$(".inputBg").prev().addClass("inputBg");
			if($(".num li").length == 1){
				if($(".database-content").find("div").text().replace(/\s+/g,"") == ""){
					 event.preventDefault();//阻止默认事件样式发生
				 }
			}
			var t1 =  setInterval(function(){//database-content=true比较特殊，因此需要在退格键事件完成后才执行
				if($(".inputBg").text().replace(/\s+/g,"") == ""){
					 $(".suggestion").empty();
					 $(".suggestion").css("display","none");
				 }
				
				if($(".num li").length != 1){
					  var divLength = $(".database-content").find("div").length;
					  if(divLength < $(".num li").length){
			 			var prevLi = $(".num").find(".libg").prev();
			 			$(".libg").removeClass();
			 			$(".num li:last-child").remove();
			 			prevLi.addClass("libg");
			 			//console.log($(".libg").text());
			 			$(".database-content div:eq("+parseInt($(".libg").text()-1)+")").addClass("inputBg");
					  }
				 }
				 clearInterval(t1);
			},10);
			
			
		 }else if(event.keyCode == "46"){//del键
		   var t2 =  setInterval(function(){//database-content=true比较特殊，因此需要事件完成后才执行
			  var divLength = $(".database-content").find("div").length;
			  if(divLength < $(".num li").length){
	 			$(".num li:last-child").remove();
	 		  }
				clearInterval(t2);
						
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
			 
			 //$(".suggestion").empty();
			 //$(".suggestion").css("display","none");
		 }else if(event.keyCode == "40"){ //下箭头
			 if($(".num li").length != $(".libg").text()){
				 var nLi = $(".num").find(".libg").next();
				  $(".libg").removeClass();
				  nLi.addClass("libg");
				  var nInputBg = $(".inputBg").next();
				  $(".inputBg").removeClass();
				  nInputBg.addClass("inputBg");
			 }
			 //$(".suggestion").empty();
			 //$(".suggestion").css("display","none");
		 }else{
			 
			 var t3 =  setInterval(function(){//database-content=true比较特殊，因此需要事件完成后才执行
				 
				 var url = "/mysql/suggestion";
				 var index = $(".inputBg").text().lastIndexOf(" ");
				 var data = {
						 context: $(".inputBg").text().substring(index + 1),
				 };
				 var rs_function = function(result){
					 if(null != result && result.length >0){
						 $(".suggestion").empty();
						 $(".suggestion").css("display","inline-block");
						 //$(".suggestion").css("position","absolute");0-getSelectionCoords(window).x
						 var p_width = $(".panel").width()-1;
						 $(".suggestion").css("marginLeft",0-p_width);
						 var e_temp = $(".inputBg").index()*22 + 45;
						 $(".suggestion").css("marginTop",e_temp);
						 //$(".suggestion").css("marginLeft",event.x);
						 //$(".suggestion").css("marginTop",event.y);
						 $.each(result,function(index,value){
							 $(".suggestion").append("<li>"+value+"</li>");
						 });
					 }else{
						 $(".suggestion").empty();
						 $(".suggestion").css("display","none");
					 }
						
				}
				var re_function = function(result){
					layer.msg("The Server is error!");
				}
				if($(".inputBg").text().replace(/\s+/g,"") == ""){
				}else{
					$.commonAjax(url,data,rs_function,re_function);
				}
				clearInterval(t3);
				 			
			},10);
			 
		 }
		 
    });
	//提示框li的点击事件
	$(".suggestion").on("click","li",function(){
		 var index = $(".inputBg").text().lastIndexOf(" ");
		 var temp = $(".inputBg").text().substring(index + 1);
		 $(".inputBg").append($(this).text().split(temp));
		 $(".suggestion").empty();
		 $(".suggestion").css("display","none");
	});
	//解决光标不能显示在最后面的问题 (no use)
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
	//获取焦点
	$(".panel").on("click",".database-content div",function(){
		var num = $(this).index();
		$(".libg").removeClass();
		$(".num li:eq("+ num +")").addClass("libg");
		$(".inputBg").removeClass();
		$(".database-content div:eq("+ num +")").addClass("inputBg");
		
	});
	// 禁止粘贴 未解决粘贴bug
	$('.database-content').bind('paste',function(e){
		e.preventDefault();//阻止默认事件样式发生
        /*var pastedText = undefined;
        if (window.clipboardData && window.clipboardData.getData) { // IE
            pastedText = window.clipboardData.getData('Text');
          } else {
            pastedText = e.originalEvent.clipboardData.getData('Text');//e.clipboardData.getData('text/plain');
            document.execCommand('insertText', false, pastedText.replace(/<[^>]+>/g,""));
          }*/
    });
	//获取光标在input框中的位置(no use)
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
	//关键字变色 （待定）
	/*var keyword = ["add", "all", "alter", "analyze", "and", "as", "asc", "asensitive",
			"before", "between", "bigint", "binary", "blob", "both", "by", "call", "cascade", 
			"case",	"change", "char", "character", "check", "collate", "column", "condition",
			"connection","constraint", "continue", "convert", "create", "cross", "current_date",
			"current_time","current_timestamp", "current_user", "cursor", "database", "databases",
			"day_hour","day_microsecond", "day_minute", "day_second", "dec", "decimal", "declare", "default",
			"delayed", "delete", "desc", "describe", "deterministic", "distinct", "distinctrow",
			"div","double", "drop", "dual", "each" ,"else" ,"elseif", "enclosed", "escaped", "exists",
			"exit" ,"explain","false", "fetch", "float", "float4", "float8" ,"for", "force" ,"foreign" ,
			"from", "fulltext", "goto", "grant" ,"group", "having", "high_priority", "hour_microsecond",
			"hour_minute", "hour_second", "if", "ignore" ,"in" ,"index" ,"infile" ,"inner", "int","int1" ,
			"int2" ,"int3", "int4", "int8", "integer", "interval", "into", "is", "iterate", "join", "key",
			"keys" ,"kill" ,"label" ,"leading" ,"leave", "left" ,"like", "limit" ,"linear", "lines" ,"load",
			"localtime", "localtimestamp" ,"lock", "long" ,"longblob", "longtext", "loop", "low_priority",
			"match", "mediumblob" ,"mediumint" ,"mediumtext", "middleint", "minute_microsecond",
			"minute_second", "mod" ,"modifies" ,"natural", "not" ,"no_write_to_binlog" ,"null" ,"numeric", "on",
			"optimize" ,"option" ,"optionally", "or" ,"order", "out" ,"outer" ,"outfile" ,"precision" ,"primary",
			"procedure"	,"purge", "raido", "range", "read", "reads", "real" ,"references", "regexp", "release", 
			"rename" ,"repeat" ,"replace"	,"require" ,"restrict" ,"return", "revoke" ,"right" ,"rlike" ,"schema",
			"schemas", "second_microsecond" ,"select",	"sensitive", "separator", "set", "show", "smallint", "spatial",
			"specific" ,"sql" ,"sqlexception", "sqlstate" ,"sqlwarning" ,"sql_big_result" ,"sql_calc_found_rows",
			"sql_small_result" ,"ssl", "starting", "straight_join"	,"table" ,"terminated", "then" ,"tinyblob", 
			"tinyint" ,"tinytext", "to" ,"trailing", "trigger" ,"true", "undo", "union", "unique" ,"unlock",
			"unsigned" ,"update" ,"usage", "use" ,"using", "utc_date" ,"utc_time", "utc_timestamp", "values" ,
			"varbinary", "varchar" ,"varcharacer" ,"varying" ,"when", "where", "while", "with", "write", "x509", "xor",
			"year_month", "zerofill"];
	function isKeyWord(value){
		if (keyword.indexOf(value) == -1){
			return false;
		}
		return true;
	}*/
	//右键事件 
	//context.init({ preventDoubleContext: false });//初始化
    //context.settings({ compress: true });
	$(this).click(function(){
		$(".rightHand").css("display","none");
	});
	$(".rightHand").on("click","li",function(e){
		var iconClass = $(this).find("i").attr("class");
		var tableName = $(this).find("input").val();
		
		if(iconClass == undefined){
		}else if(iconClass.indexOf("search") != -1){
			var sql = "select * from "+tableName;
			var url = "/mysql/getMysqlReturnData";
			 var data = {
					 sql: sql,
			 };
			 var rs_function = function(result){
				 if(result.model != null){
					 if(null != result.model.title){
						 var title = "<thead><tr>";
						 for(var i=0;i<result.model.title.length;i++){
							 title += "<td>" + result.model.title[i] +"</td>";
						 }
						 title += "</tr></thead>";
						 var content = "<tbody>";
						 for(var j=0;j<result.model.content.length;j++){
							 content += "<tr>";
							 for(var k=0;k<result.model.content[j].length;k++){
								 content +=  "<td>"+result.model.content[j][k]+"</td>";
							 }
							 content += "</tr>";
						 }
						 content += "</tbody>"
					 }
					 var data = "<table class='table table-bordered' style='overflow: auto;'>" + title + content +"</table>"; 
					 layer.open({
						  type: 1 //Page层类型
						  //,area: ['500px', '300px']
						  ,title: tableName
						  ,shade: 0.6 //遮罩透明度
						  ,maxmin: true //允许全屏最小化
						  ,anim: 1 //0-6的动画形式，-1不开启
						  ,content: data
						});
				 }
			}
			var re_function = function(result){
				layer.msg("The Server is error!");
			}
			globalLoading();
			$.commonAjax(url,data,rs_function,re_function);
			layer.close(loadingIndex);//执行完关闭loading
			
		}else if(iconClass.indexOf("fa-pencil-square-o") != -1){
			var tableName = tableName;
			var url = "/mysql/getTableStructureData";
			 var data = {
					 tableName: tableName,
			 };
			 var rs_function = function(result){
				if(result.error ==null){
					var primaryKey = [];
					for(var i = 0;i<result.primaryKey.length;i++){
						primaryKey.push(result.primaryKey[i]);
					}
					var title = "<thead><tr><td>"+$(".clumnName").val()+"</td><td>"+$(".type").val()+"</td><td>"+$(".length").val()+"</td><td>"+$(".isAllowNull").val()+"</td><td>"+$(".isPrimaryKey").val()+"</td><thead>"
					var content = "<tbody><tr>";
					for(var i = 0;i<result.list.length;i++){
						for(var j=0;j<result.list[i].length;j++){
							content += "<td>" + result.list[i][j] +"</td>";
						}
						if(primaryKey.indexOf(result.list[i][0])!= -1){
							content += "<td><i class='fa fa-key' aria-hidden='true'></i></td>";
						}
						content += "</tr>";
					}
					content += "</tbody>";
					var data = "<table class='table table-bordered' style='overflow: auto;'>" + title + content +"</table>"; 
					 layer.open({
						  type: 1 //Page层类型
						  //,area: ['500px', '300px']
						  ,title: tableName
						  ,shade: 0.6 //遮罩透明度
						  ,maxmin: true //允许全屏最小化
						  ,anim: 1 //0-6的动画形式，-1不开启
						  ,content: data
						});
					
				}else{
					layer.msg(result.error);
				}
			}
			var re_function = function(result){
				layer.msg("The Server is error!");
			}
			globalLoading();
			$.commonAjax(url,data,rs_function,re_function);
			layer.close(loadingIndex);//执行完关闭loading
		}else if(iconClass.indexOf("fa-plus") != -1){
			
		}
	});
	$("#tree").on("contextmenu","li",function(e){
		//阻止默认事件发生
		e.preventDefault();
		//0表示当前右键的是数据库
		//1表示表
		//2表示字段
		var i_length = $(this).find(".indent").length;
		var tableName = $(this).text();
		if(i_length == 0){ // 待定
			/*$(".rightHand").empty();
			$(".rightHand").append("<li></i>"+$(".menu").val()+"</li>");
			$(".rightHand").append("<li><input type='hidden' value='"+ tableName+ "'/><i class='fa fa-plus' aria-hidden='true'>&nbsp;&nbsp;"+$(".createTable").val()+"</li>");
			$(".rightHand").css({
				   top: e.pageY,
				   left: e.pageX,
				   display: "block"
				  });*/
		}else if(i_length == 1){
			$(".rightHand").empty();
			$(".rightHand").append("<li></i>"+$(".menu").val()+"</li>");
			$(".rightHand").append("<li><input type='hidden' value='"+ tableName+ "'/><i class='fa fa-search' aria-hidden='true'></i>&nbsp;&nbsp;"+$(".checkData").val()+"</li>")
			$(".rightHand").append("<li><input type='hidden' value='"+ tableName+ "'/><i class='fa fa-pencil-square-o' aria-hidden='true'></i>&nbsp;&nbsp;"+$(".checkDesign").val()+"</li>")
			$(".rightHand").css({
				   top: e.pageY,
				   left: e.pageX,
				   display: "block"
				  });
			
		}else if(i_length == 2){
			
		}
	    
	});
	
    
	//runButton
	$(".right .top").on("click",".runButton",function(){
		$(".loading").css("display","block");
		var sql = $(".database-content").text();
		sql = sql.replace(/\s+/g, ' ');
		var url = "/mysql/getMysqlReturnData";
		 var data = {
				 sql: sql,
		 };
		 var rs_function = function(result){
			 if(result.model != null){
				 if(result.model.error != null){
					 var info = result.model.error;
					 $("#info").html("[SQL]:"+sql+"<br/>[ERROR]:"+info);
				 }else{
					 $("#info").html("[SQL]:"+sql+"<br/>[Affected rows]:"+result.model.affectRow);
					 if(null != result.model.title){
						 var title = "<tr>";
						 for(var i=0;i<result.model.title.length;i++){
							 title += "<td>" + result.model.title[i] +"</td>"
						 }
						 title += "</tr>";
						 var content = "";
						 for(var j=0;j<result.model.content.length;j++){
							 content += "<tr>";
							 for(var k=0;k<result.model.content[j].length;k++){
								 content +=  "<td>"+result.model.content[j][k]+"</td>";
							 }
							 content += "</tr>";
						 }
						 $("#result table").html(title+content);
					 }
				 }
				 if(result.message != null){
					 layer.msg(result.message);
				 }
			 }
				
		}
		var re_function = function(result){
			layer.msg("The Server is error!");
		}
		//globalLoading();
		$.commonAjax(url,data,rs_function,re_function);
		$(".loading").css("display","none");
		//layer.close(loadingIndex);//执行完关闭loading
	
	});
	$(".right .top").on("click",".stopButton",function(){
		var url = "/mysql/stopMysql";
		 var data = {};
		 var rs_function = function(result){
			 layer.msg("The process is stoped!");
		}
		var re_function = function(result){
			layer.msg("The Server is error!");
		}
		$.commonAjax(url,data,rs_function,re_function);
	});
	// no use
	var loadingIndex = -1;
	function globalLoading(num){
		if( loadingIndex == -1 ){
			//支持0-2,0和2还可以
			loadingIndex = layer.load(num);
		}
	}

	
	
});

