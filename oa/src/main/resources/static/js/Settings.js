$(function(){
	$(".title").on("click",".search",function(){
		var content = $(".title input").val();
		var type = $(".type").val();
		if(content != ""){
			var url = "/settings/search";
			var data = {
					content: content,
					type: type
			};
			var s_function = function(result){
				if(result.flag){
					$(".dataTable tbody").empty();
					for(var i=0;i<result.systemModel.length;i++){
						
						var id;
						if(type == "1"){
							id = result.systemModel[i].uId;
						}else{
							id = result.users[i].userId;
						}
						var ul 
						$(".dataTable tbody").append("<tr>" +
								"<td>"+id+"</td>" +
								"<td>"+result.users[i].userName+"</td>" +
								"<td>"+temp+"</td>" +
								"<td>"+result.users[i].time+"</td>" +
								"<td><ul class='roleUl'></ul></td>" +
								"<td class='edit'>"+$(".edit").val()+"</td>" +
								"</tr>");
						for(var j=0;j<result.users[i].roleList.length;j++){
							$(".roleUl").append("<li>"+result.users[i].roleList[j].role+"</li>");
						}
						
					}
				}
			}
			var e_function = function(){
				layer.msg("The server is error!!!");
			}
			$.commonAjax(url,data,s_function,e_function);
		}
	});
	$(".title").on("click",".add",function(){
		$(".showTab").css("display","none");
		$(".addLocalAddress").val("");
		$(".addRepoAddress").val("");
		$(".showADD").css("display","block");
		
	});
	$(".addTable,.editTable").on("click",".return",function(){
		$(".showTab").css("display","inline-block");
		$(".showADD").css("display","none");
		$(".showEdit").css("display","none");
	});
	$(".addTable").on("click",".save",function(){
		
		$(".showADD").css("display","none");
		var local = $(".addLocalAddress").val();
		var repo = $(".addRepoAddress").val();
		if(local !="" && repo !=""){
			var url = "/addSettins";
			var data = {
					localAddress: local,
					repoAddress: repo,
			};
			var s_function = function(result){
				if(result.addFlag){
					var id = $(".dataTable tbody tr").length+1;
					$(".dataTable tbody").append("<tr>" +
					"<td>"+id+"</td>" +
					"<td>"+local+"</td>" +
					"<td>"+repo+"</td>" +
					"<td class='edit'>"+$(".edit").val()+"</td>" +
					"</tr>");
				}
			}
			var e_function = function(){
				layer.msg("The server is error!!!");
			}
			$.commonAjax(url,data,s_function,e_function);
			$(".showTab").css("display","block");
		}
	});
	$(".dataTable").on("click",".edit",function(){
		$(".showTab").css("display","none");
		$(".showADD").css("display","none");
		var repo = $(this).prev().text();
		var local = $(this).prev().prev().text();
		var num = $(this).prev().prev().prev().text();
		$(".editTable").find("tr").eq(0).find("input").val(num);
		$(".editTable").find("tr").eq(1).find("input").val(local);
		$(".editTable").find("tr").eq(2).find("input").val(repo);
		$(".showEdit").css("display","block");
	});
	$(".showEdit").on("click",".saveChange",function(){
		var num = $(".editTable").find("tr").eq(0).find("input").val();
		var local = $(".editTable").find("tr").eq(1).find("input").val();
		var repo = $(".editTable").find("tr").eq(2).find("input").val();
		
		var url = "/changeSettings";
		var data = {
				num: num,
				local: local,
				repo: repo,
		};
		var s_function = function(result){
			if(result.changeFlag){
				$(".dataTable tbody").find("tr").each(function(){
					var serial = $(this).find("td").eq(0).text();
					if(serial == num){
						$(this).find("td").eq(1).text(local);
						$(this).find("td").eq(2).text(repo);
					}
				});
				$(".showTab").css("display","block");
				$(".showADD").css("display","none");
				$(".showEdit").css("display","none");
			}
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		$.commonAjax(url,data,s_function,e_function);
	})

})