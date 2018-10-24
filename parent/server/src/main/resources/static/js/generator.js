$(function(){
	$(".addTable").on("click",".addButton",function(){
		 
		 layer.open({
			  type: 1 //Page层类型
			  //,area: ['500px', '300px']
			  ,title: $(".createTable").val()
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: $(".table")
			});
	});
})