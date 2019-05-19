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
		$(".addName").val("");
		$(".addPwd").val("");
		$(".showADD").css("display","block");
		
	});
	$(".addTable,.editTable").on("click",".return",function(){
		$(".showTab").css("display","block");
		$(".showADD").css("display","none");
		$(".showEdit").css("display","none");
	});
	$(".addTable").on("click",".save",function(){
		
		$(".showADD").css("display","none");
		var name = $(".addName").val();
		var pwd = $(".addPwd").val();
		if(name !="" && pwd !=""){
			var url = "/server/addUser";
			var data = {
					userName: name,
					userPwd: pwd,
					roleUl: null
			};
			var s_function = function(result){
				if(result.addFlag){
					var id = $(".dataTable tbody tr").length+1;
					$(".dataTable tbody").append("<tr>" +
					"<td>"+id+"</td>" +
					"<td>"+name+"</td>" +
					"<td></td>" +
					"<td></td>" +
					"<td><ul class='roleUl'><li>General</li></ul></td>" +
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
	var roleList;
	var roleListO;
	$(".dataTable").on("click",".edit",function(){
		$(".showTab").css("display","none");
		$(".showADD").css("display","none");
		var islock = $(this).prev().prev().prev().text();
		var name = $(this).prev().prev().prev().prev().text();
		var num = $(this).prev().prev().prev().prev().prev().text();
		
		
		
		roleList = [];
		roleListO = [];
		var roleUl = $(this).prev().find("ul li");
		//var name = $(this).prev().prev().text();
		//var num = $(this).prev().prev().prev().text();
		
		roleUl.each(function(){
			$(".roleList").append("<li class='fa fa-window-close' value='"+$(this).val()+"'>"+$(this).text()+"</li>");
			roleList.push($(this).text());
			roleListO.push($(this).text());
		});
		
		
		$(".editTable").find("tr").eq(0).find("input").val(num);
		$(".editTable").find("tr").eq(1).find("input").val(name);
		if(islock==$(".lock").val()){
			$(".editTable").find("tr").eq(2).find("input").attr("checked", true);
		}else{
			$(".editTable").find("tr").eq(2).find("input").removeAttr("checked");;
		}
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
				uIsLock: islock,
				roleList: roleList,
				roleListO :roleList
		};
		var s_function = function(result){
			if(result.changeFlag){
				$(".dataTable tbody").find("tr").each(function(){
					var serial = $(this).find("td").eq(0).text();
					console.log(serial);
					if(serial == num){
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
		$.arrayAjax(url,data,s_function,e_function);
	})
	$(this).on("click",".roleTd",function(e){
		$(".roleCollection").css({
			   top: e.pageY,
			   left: e.pageX,
			   display:"block"
			  });
	});
	$(".roleList").on("click","li",function(){
		$(this).remove();
		roleList.splice(roleList.indexOf($(this).text()), 1);
	})
	$(".roleCollection").on("click","li",function(){
		if($.inArray($(this).text(),roleList)==-1){
			$(".roleList").append("<li class='fa fa-window-close'>"+$(this).text()+"</li>");
			roleList.push($(this).text());
		}
		$(".roleCollection").css("display","none");
	});
})