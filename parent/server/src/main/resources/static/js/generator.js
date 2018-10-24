$(function(){
	$(".addTable").on("click",".addButton",function(){
		var data ="<div class=''>"
		var table = "<table class='table table-bordered ' style='overflow: auto;'>";
		 title="<thead><tr><td>"+$(".clumnName").val()+"</td><td>"+$(".type").val()+"</td></tr></thead>";
		 layer.open({
			  type: 1 //Page层类型
			  ,title: $(".createTable").val()
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: ""
			});
	});
})