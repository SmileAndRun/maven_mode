$(function(){
	$(".data").on("click",".fa-eye",function(){
		var dataValue = "<div style='margin:10px;color:black;'>";
		var versionId = $(this).parent().prev().prev().text();
		var data = {
				versionId:versionId,
			};
		var url = "/getZipDatails";
		var s_function = function(data){
			dataValue += "----------[CODE UPDATE INFO]----------";
			dataValue += data.versionModel.updateInfo+"<br/>----------[MAVEN PACKAGE INFO]----------<br/>"+data.versionModel.packageInfo+"</div>";
			layer.open({
				  title: "DATAILS",
				  content: dataValue,
				  area: ['700px', '400px'],
				  type: 1,
				  maxmin: true,            //最大化按钮
			  	  anim:3,                    //动画
			  	  shade: [0.8, '#393D49'],//遮罩层
			  	  btn: [$(".confirm-i18n").val()],
			  	  yes: function(index, layero){
			  		layer.close(index);
				  },
			  	  cancel: function(index, layero){ 
			  	    layer.close(index);
			  	  }
				});
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		$.commonAjax(url,data,s_function,e_function);
		
		
	})
	//download
	$(".data").on("click",".fa-arrow-circle-o-down",function(){
		var versionId = $(this).parent().prev().prev().text();
	
		window.location.href="/downZip?versionId="+versionId;
	});
	$(".title").on("click",".search",function(){
		var versionId = $(".versionId").val();
		if(""==versionId){
			window.location.href="/html/versionInfo";
			return ;
		}
		var data = {
				versionId:versionId,
			};
		var url = "/searchVersion";
		var s_function = function(data){
			$(".data .dataTable tbody").empty();
			if(data.versionModel != null){
				$(".data .dataTable tbody").append("<tr><td>1</td><td>"+data.versionModel.versionId+"</td><td>"
						+data.versionModel.createTime+"</td><td>&nbsp;<i class='fa fa-eye' aria-hidden='true'></i>&nbsp;&nbsp;&nbsp;<i class='fa fa-arrow-circle-o-down' aria-hidden='true'></i></td></tr>")
			}
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		$.commonAjax(url,data,s_function,e_function);
	})
});