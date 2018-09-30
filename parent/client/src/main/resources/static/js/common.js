$(function(){
	//加载尾部
	
	
	function changeLanguage(lan){
		$.Ajax({
			type:"post",
			url:"/i18n/selectLanguage",
			contentType:"application/json;charset=utf-8",
			dataType:"json",
			data:{"language":lan},
			success:function(data){
				location.reload();
			}
		});
	}
});