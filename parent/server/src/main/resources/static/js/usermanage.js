$(function(){
	$(".title").on("click",".search",function(){
		var content = $(".title input").val();
		var type = $(".type").val();
		if(content != ""){
			var url = "/server/user/search";
			var data = {
					content: content,
					type: type
			};
			var s_function = function(result){
				if(result.users != null){
					$(".dataTable tbody").empty();
					for(var i=0;i<result.users.length;i++){
						var temp = "";
						if(result.users[i].flag){
							temp = $(".lock").val();
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
								"<td>"+temp+"</td>" +
								"<td>"+result.users[i].time+"</td>" +
								"<td>"+$(".edit").val()+"</td>" +
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
		$(".addName").val("");
		$(".addPwd").val("");
		$(".showADD").css("display","block");
		
	});
	$(".addTable").on("click",".return",function(){
		$(".showTab").css("display","block");
		$(".showADD").css("display","none");
	});
	$(".addTable").on("click",".save",function(){
		
		$(".showADD").css("display","none");
		var name = $(".addName").val();
		var pwd = $(".addPwd").val();
		if(name !="" && pwd !=""){
			var url = "/server/addUser";
			var data = {
					userName: name,
					userPwd: pwd
			};
			var s_function = function(result){
				if(result.addFlag){
					var id = $(".dataTable tbody tr").length+1;
					$(".dataTable tbody").append("<tr>" +
					"<td>"+id+"</td>" +
					"<td>"+name+"</td>" +
					"<td></td>" +
					"<td></td>" +
					"<td>"+$(".edit").val()+"</td>" +
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
})