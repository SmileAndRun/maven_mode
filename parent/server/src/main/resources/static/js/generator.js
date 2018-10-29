$(function(){
	$(".addTable").on("click",".addButton",function(){
		var data ="<div class='data'>";
		var button = "<div><button class='add'><i class='fa fa-plus-square fa-2x' aria-hidden='true'></i>"+$(".add").val()+"</button><button class='del'><i class='fa fa-minus-square fa-2x' aria-hidden='true'></i>"+$(".delete").val()+"</button></div>";
		var table = "<table class='table table-bordered tableData' style='overflow: auto;'><thead><tr><td>"+$(".clumnName").val()+"</td><td>"+$(".type").val()+"</td></tr></thead></table>";
		data += button + table + "</div>";
		 layer.open({
			  type: 1 //Page层类型
			  , area: ['420px', '240px']//宽高
			  ,title: $(".createTable").val()
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: data
			  ,btn: [$(".confirm").val(), $(".cancel").val()] //按钮组
		 	  ,yes: function(index, layero){
		 		  if($(".tableData").find("input")[0] == null || $(".tableData").find("input")[0] == undefined){
		 			  layer.msg("table is null,please add clumn!")
		 		  }else{
		 			  if($(".rowSelected").val() == ""){
		 				 layer.msg("please add completed!")
		 			  }else{
		 				 layer.prompt(
		 						 {title: $(".pitn").val()}	 
		 					,function(value, index, elem){
		 						var url = "/server/generator/code";
		 						var columnArray = [];
		 						var typeArray = [];
		 						for(var i=1;i<=$(".tableData").find("tr").length;i++){
		 							var columnName = $(".tableData").find("tr").eq(i).find("input").val();
		 							var columnType = $(".tableData").find("tr").eq(i).find("select").val();
		 							columnArray.push(columnName);
		 							typeArray.push(columnType);
		 						}
				 				var data = {
				 						tableName : value,
				 						columnArray : columnArray,
				 						typeArray : typeArray
				 				};
				 				var rs_function = function(data){
				 					$(".addTable").css("display","none");
				 					$(".treeData").css("display","block");
				 					var result = data.result;
				 					var mapper = [];
				 					var model = [];
				 					var service = [];
				 					var serviceImpl = [];
				 					var mapperXml = [];
				 					if(null != result){
				 						console.log(result.service);
				 						for(var i = 0;i<result.mapper.length;i++){
				 							var jsonMapper = {};
				 							jsonMapper["text"] = "<i class='fa fa-file-code-o treeIcon' aria-hidden='true'></i>"+result.mapper[i] ;
				 							mapper.push(jsonMapper);
				 						}
				 						for(var i = 0;i<result.model.length;i++){
				 							var jsonModel = {};
				 							jsonModel["text"] = "<i class='fa fa-file-code-o treeIcon' aria-hidden='true'></i>"+result.model[i] ;
				 							model.push(jsonModel);
				 						}
				 						for(var i=0;i<result.serviceImpl.length;i++){
				 							var jsonServiceImpl = {};
				 							jsonServiceImpl["text"] = "<i class='fa fa-file-code-o treeIcon' aria-hidden='true'></i>"+result.serviceImpl[i];
				 							serviceImpl.push(jsonServiceImpl);
				 						}
				 						for(var i=0;i<result.service.length;i++){
				 							var jsonService = {};
				 							if(result.service[i] != "impl"){
				 								jsonService["text"] = "<i class='fa fa-file-code-o treeIcon' aria-hidden='true'></i>"+result.service[i];
				 								service.push(jsonService);
				 							}else{
				 								jsonService["text"] = "<i class='fa fa-th-large treeIcon' aria-hidden='true'></i>"+result.service[i];
				 								jsonService["nodes"] = serviceImpl;
				 								service.push(jsonService);
				 							}
				 						}
				 						for(var i=0;i<result.mapperXml.length;i++){
				 							var jsonMapperXml = {};
				 							jsonMapperXml["text"] = "<i class='fa fa-file-excel-o treeIcon' aria-hidden='true'></i>"+result.mapperXml[i];
				 							mapperXml.push(jsonMapperXml);
				 						}
				 						
				 					}else{
				 						return ;
				 					}
				 					
				 					var tree = [{
				 						text: "model1",
				 						nodes:  [{
				 							text: "<i class='fa fa-folder treeIcon' aria-hidden='true'></i>src",
				 							nodes: [{
				 								text: "<i class='fa fa-folder treeIcon' aria-hidden='true'></i>main",
				 								nodes: [{
				 									text: "<i class='fa fa-folder treeIcon' aria-hidden='true'></i>java",
				 									nodes: [{
				 										text: "<i class='fa fa-th-large treeIcon' aria-hidden='true'></i>com",
				 										nodes: [{
				 											text: "<i class='fa fa-th-large treeIcon' aria-hidden='true'></i>example",
				 											nodes: [{
				 												text: "<i class='fa fa-th-large treeIcon' aria-hidden='true'></i>demo",
				 												nodes: [
				 												{
				 													text: "<i class='fa fa-th-large treeIcon' aria-hidden='true'></i>controller"
				 												},
				 												{
				 													text: "<i class='fa fa-th-large treeIcon' aria-hidden='true'></i>mapper",
				 													nodes: mapper
				 												},
				 												{
				 													text: "<i class='fa fa-th-large treeIcon' aria-hidden='true'></i>model",
				 													nodes: model
				 												},
				 												{
				 													text: "<i class='fa fa-th-large treeIcon' aria-hidden='true'></i>service",
				 													nodes: service
				 												},
				 												{
				 													text: "DemoApplication.java",
				 												}
				 												],
				 											}],
				 										}],
				 									}],
				 								},
				 								{
				 									text: "<i class='fa fa-folder treeIcon' aria-hidden='true'></i>resources",
				 									nodes: [{
				 										text: "<i class='fa fa-folder treeIcon' aria-hidden='true'></i>mapper",
				 										nodes: mapperXml
				 									},
				 									{
				 										text: "<i class='fa fa-file-powerpoint-o treeIcon' aria-hidden='true'></i>application.properties"
				 									}
				 									],
				 								}
				 								
				 								],
				 							},
				 							{
				 								text: "<i class='fa fa-file-excel-o treeIcon' aria-hidden='true'></i>pom.xml",
				 							}
				 							],
				 						}],
				 					}];
				 					
				 					//初始化数据源tree结构
				 					$('#tree').treeview({
				 						  data: tree,         // data is not optional
				 						  levels: 1,
				 						  collapseIcon: "glyphicon glyphicon-triangle-bottom",
				 						  expandIcon: "glyphicon glyphicon-triangle-right",
				 					});
				 				}
				 				 var re_function = function(result){
				 					layer.msg("The server is error!!!")
				 				}
				 				$.arrayAjax(url,data,rs_function,re_function);
				 				layer.closeAll();
		 					});
		 				 
		 			  }
		 		  }
			  }
			});
	});
	$(this).on("click",".add",function(){
		var input = $(".tableData").find("tr:last").find("td input");
		if(input != undefined  && input != null){//当前字段填写后才能添加下一个
			if(input.val() == ""){
				return ;
			}
		}
		var select = "<select>" +
				"<option value='tinyint'>tinyint</option><option value='smallint'>smallint</option>" +
				"<option value='mediumint'>mediumint</option><option value='int'>int</option>" +
				"<option value='integer'>integer</option><option value='bigint'>bigint</option>" +
				"<option value='float'>float</option><option value='double'>double</option>" +
				"<option value='decimal'>decimal</option><option value='date'>date</option>" +
				"<option value='time'>time</option><option value='year'>year</option>" +
				"<option value='datetime'>datetime</option><option value='timestamp'>timestamp</option>" +
				"<option value='char'>char</option><option value='varchar'>varchar</option>" +
				"<option value='tinyblob'>tinyblob</option><option value='tinytext'>tinytext</option>" +
				"<option value='blob'>blob</option><option value='text'>text</option>" +
				"<option value='mediumblob'>mediumblob</option><option value='mediumtext'>mediumtext</option>" +
				"<option value='longblob'>longblob</option><option value='longtext'>longtext</option>" 
		+"</select>";
		var row = "<tr><td><input type=\"text\"/></td><td>"+select+"</td></tr>";
		$(".tableData").append(row);
	});
	$(this).on("click",".tableData input",function(){
		$(".rowSelected").removeClass();
		$(this).addClass("rowSelected");
	});
	
	$(this).on("click",".del",function(){
		
	 $(".rowSelected").parent("td").parent("tr").remove();
		
	});
})