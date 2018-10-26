$(function(){
	
	jQuery.jsonAjax = function (p_url,p_data,s_function,e_function){
		$.ajax({
			type: "post",
			url: p_url,
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			data: p_data,
			success: function(data){
				s_function(data);
			},
			error: function(data){
				e_function(data);
			}
		});
	}
	jQuery.commonAjax = function (p_url,p_data,s_function,e_function){
		$.ajax({
			type: "post",
			url: p_url,
			dataType: "json",
			data: p_data,
			success: function(data){
				s_function(data);
			},
			error: function(data){
				e_function(data);
			}
		});
	}
	jQuery.arrayAjax = function (p_url,p_data,s_function,e_function){
		$.ajax({
			type: "post",
			url: p_url,
			dataType: "json",
			traditional: true,
			data: p_data,
			success: function(data){
				s_function(data);
			},
			error: function(data){
				e_function(data);
			}
		});
	}
});