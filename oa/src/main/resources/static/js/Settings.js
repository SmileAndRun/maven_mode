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
					for(var i=0;i<result.settings.systemModel.length;i++){
						
			
						$(".dataTable tbody").append("<tr>" +
								"<td>"+result.settings.systemModel[i].num+"</td>" +
								"<td>"+result.settings.systemModel[i].localRepo+"</td>" +
								"<td>"+result.settings.systemModel[i].remoteRepo+"</td>" +
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
			var url = "/addSystemSet";
			var data = {
					localRepo: local,
					remoteRepo: repo,
			};
			var s_function = function(result){
				if(result.flag){
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
		
		var url = "/updateSystemSet";
		var data = {
				num: num,
				localRepo: local,
				remoteRepo: repo,
		};
		var s_function = function(result){
			if(result.flag){
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