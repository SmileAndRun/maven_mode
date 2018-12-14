//用来统计
var preList = [];
var preListO = [];
$(function(){
		
	$(".title").on("click",".search",function(){
		var content = $(".title input").val();
		var type = $(".type").val();
		if(content != ""){
			var url = "/server/user/searchRoleInfo";
			var data = {
					content: content,
					type: type
			};
			var s_function = function(result){
				if(result.users != null){
					$(".dataTable tbody").empty();
					for(var i=0;i<result.users.length;i++){
						var role = "";
						var permission = "";
						for(var j = 0;j<result.users[i].roleList.length;j++){
							role += "<label>"+result.users[i].roleList[j].role+"</label>";
							for(var k = 0;k<result.users[i].roleList[j].permissionList.length;k++){
								permission += "<label>"+result.users[i].roleList[j].permissionList[k].permission+"</label>";
							}
						}
						var id;
						if(type == "1"){
							id = result.users[i].uId;
						}else{
							id = result.users[i].userId;
						}
						$(".dataTable tbody").append("<tr>" +
								"<td>"+id+"</td>" +
								"<td>"+result.users[i].userName+"</td>" +
								"<td>"+role+"</td>" +
								"<td>"+permission+"</td>" +
								"<td class='edit'>"+$(".edit").val()+"</td>" +
								"</tr>");
					}
				}
			}
			var e_function = function(){
				layer.msg("The server is error!!!");
			}
			$.commonAjax(url,data,s_function,e_function);
		}
	});
	
	$(".editTable").on("click",".return",function(){
		$(".showTab").css("display","block");
		$(".showEdit").css("display","none");
		$(".preCollection").css("display","none");
	});
	
	
	$(".dataTable").on("click",".edit",function(){
		$(".showTab").css("display","none");
		$(".showADD").css("display","none");
		$(".preList").empty();
		preList = [];
		preListO = [];
		var preUl = $(this).prev().find("ul li");
		var name = $(this).prev().prev().text();
		var num = $(this).prev().prev().prev().text();
		
		preUl.each(function(){
			$(".preList").append("<li class='fa fa-window-close' value='"+$(this).val()+"'>"+$(this).text()+"</li>");
			preList.push($(this).text());
			preListO.push($(this).text());
		});
		$(".editTable").find("tr").eq(0).find("input").val(num);
		$(".editTable").find("tr").eq(1).find("input").val(name);
		$(".preList").css({
			width: $(".editTable tr:first").find("td:last").attr("width"),
			height: $(".editTable tr:first").find("td:last").attr("height")
		});
		$(".showEdit").css("display","block");
	});
	$(this).on("click",".preTd",function(e){
		$(".preCollection").css({
			   top: e.pageY,
			   left: e.pageX,
			   display:"block"
			  });
	});
	$(".preList").on("click","li",function(){
		$(this).remove();
		preList.splice(preList.indexOf($(this).text()), 1);
	})
	$(".preCollection").on("click","li",function(){
		if($.inArray($(this).text(),preList)==-1){
			$(".preList").append("<li class='fa fa-window-close'>"+$(this).text()+"</li>");
			preList.push($(this).text());
		}
		$(".preCollection").css("display","none");
	});
	
	
	$(".showEdit").on("click",".saveChange",function(){
		var num = $(".editTable").find("tr").eq(0).find("input").val();
		var url = "/server/changeRole";
		var data = {
				roleId: num,
				preList:preList,
				preListO:preListO,
		};
		var s_function = function(result){
			if(result.flag){
				preListO = [];
				$(".dataTable tbody").find("tr").each(function(){
					var serial = $(this).find("td").eq(0).text();
					if(serial == num){
						var ul = $(this).find("td").next().next().find(".preUl");
						ul.find("li").remove();
						for(var i=0;i<preList.length;i++){
							ul.append("<li>"+preList[i]+"</li>");
							preListO.push(preList[i]);
						}
					}
				});
				$(".showTab").css("display","block");
				$(".showEdit").css("display","none");
			}else{
				layer.msg($(".failure-i18n").val());
			}
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		$.arrayAjax(url,data,s_function,e_function);
	});
	
		
	
	
})