$(function(){
		var height = $(window).height();
		$(".swf").css("height",height);
		//检查当前浏览器中的cookie
		var name = $.cookie('SESSION_USERNAME');
		var pwd = $.cookie('SESSION_PASSWORD');
		if(name != null && pwd != null){
			var url = "/user/validate";
			var data = {
				userName: name,
				userPwd: pwd,
				isCookie:true
				};
			var rs_function = function(result){
				if(result){
					window.location.href = "/user/index";
				}else{
					layer.msg($(".tip3").text());
				}
			}
			var re_function = function(result){
				layer.msg($(".tip3").text());
			}
			$.commonAjax(url,data,rs_function,re_function);
			
		}else{
			layer.msg($(".tip3").text());
		}
		
		$("#login_bt").click(function(){
			var userName = $("#userName").val();
			var userPwd = $("#userPwd").val();
			if(userName != "" && userPwd != ""){
				var isRememberMe = $(".remember").is(":checked");
				
				var p_url = "/user/validate";
				var p_data = {
					userName:userName,
					userPwd:userPwd,
					isRememberMe: isRememberMe, 
					isCookie:false
					};
				var s_function = function(data){
					if(data.flag){
						window.location.href = "/user/index";
					}else{
						layer.msg($(".tip2").text());
					}
				}
				var e_function = function (data){
					layer.msg($(".tip2").text());
				}
				$.commonAjax(p_url,p_data,s_function,e_function);
				
			}else{
				layer.msg($(".tip1").text());
			}
			
		});
	})