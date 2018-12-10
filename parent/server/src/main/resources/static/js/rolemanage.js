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
	});
	
	$(".dataTable").on("click",".edit",function(){
		$(".showTab").css("display","none");
		$(".showADD").css("display","none");
		var role = $(this).prev().prev().text();
		var permission = $(this).prev().text();
		var name = $(this).prev().prev().prev().text();
		var num = $(this).prev().prev().prev().prev().text();
		
		
		$(".editTable").find("tr").eq(0).find("input").val(num);
		$(".editTable").find("tr").eq(1).find("input").val(name);
		/*if(islock==$(".lock").val()){
			$(".editTable").find("tr").eq(2).find("input").attr("checked", true);
		}else{
			$(".editTable").find("tr").eq(2).find("input").removeAttr("checked");;
		}*/
		$(".showEdit").css("display","block");
	});
	$(".showEdit").on("click",".saveChange",function(){
		var num = $(".editTable").find("tr").eq(0).find("input").val();
		var name = $(".editTable").find("tr").eq(1).find("input").val();
		var islock = $(".editTable").find("tr").eq(2).find("input").is(':checked');
		if(islock){
			islock = '1';
		}else{
			islock = '0';
		}
		var url = "/server/changeUser";
		var data = {
				userId: num,
				userName: name,
				uIsLock: islock
		};
		var s_function = function(result){
			if(result.changeFlag){
				$(".dataTable tbody").find("tr").each(function(){
					var serial = $(this).find("td").eq(0).text();
					console.log(serial);
					if(serial == num){
						console.log($(this).find("td").eq(1).text());
						$(this).find("td").eq(1).text(name);
						if(islock=='1'){
							$(this).find("td").eq(2).text($(".lock").val());
						}else{
							$(this).find("td").eq(2).text("");
						}
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