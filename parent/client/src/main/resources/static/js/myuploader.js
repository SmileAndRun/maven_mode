// 图片上传demo
$(function() {
    var $ = jQuery,
        $list = $('#imgBuffList'),
        // 优化retina, 在retina下这个值是2
        ratio = window.devicePixelRatio || 1,

        // 缩略图大小
        thumbnailWidth = 100 * ratio,
        thumbnailHeight = 100 * ratio,

        // Web Uploader实例
        uploader;
    // 初始化Web Uploader
    uploader = WebUploader.create({

        // 自动上传。
        auto: false,

        // swf文件路径
        //swf: BASE_URL + '/js/Uploader.swf',

        // 文件接收服务端。
        server: '/user/upload',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
        	id: '#picker',
        	multiple: true
        },
        thumb: {
            type: 'image/jpg,jpeg,png'
         },
        // 只允许选择文件，可选。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        },
        compress: false,//不启用压缩
        resize: false,
        prepareNextFile: true,
        method: 'POST',
        paste: document.body,
        fileSingleSizeLimit: 10*1024*1024,
        duplicate: true
    });
    
    uploader.on('uploadAccept',function(file,data){
    	if(data.uploadFlag){
    		layer.msg($(".successfulUpload-i18n").val());
    		$(".uploadDiv").css({
    			display: "none",
    		});
    	}else{
    		layer.msg($(".uploadFailed-i18n").val());
    	}
    });
    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
    	
    	var $li = $(
                '<dl id="' + file.id + '" class="file-item thumbnail">' +
                    '<dd><img></dd>' +
                '</dl>'
                ),
            $img = $li.find('img');


        // $list为容器jQuery实例
        $list.append( $li );

        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith("<span>"+$(".cantPreview-i18n").val()+"</span>");
                return;
            }
            

            $img.attr( 'src', src );
        }, thumbnailWidth, thumbnailHeight );
    });
    
    uploader.on( 'uploadProgress', function( file, percentage ) {
    	 var $li = $( '#'+file.id ),
         $percent = $li.find('.progress .progress-bar');

     // 避免重复创建
     if ( !$percent.length ) {
         $percent = $('<div class="progress progress-striped active">' +
           '<div class="progress-bar" role="progressbar" style="width: 0%">' +
           '</div>' +
         '</div>').appendTo( $li ).find('.progress-bar');
     }
     $percent.css( 'width', percentage * 100 + '%' );
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on( 'uploadSuccess', function( file ) {
        $( '#'+file.id ).addClass('upload-state-done');
        var $li = $( '#'+file.id ),
        $ele = $li.find('p');
        $ele.text($(".successfulUpload-i18n").val());
    });

    // 文件上传失败，显示上传出错。
    uploader.on( 'uploadError', function( file ) {
        var $li = $( '#'+file.id ),
            $error = $li.find('div.error');

        // 避免重复创建
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $li );
        }

        $error.text($(".uploadFailed-i18n").val());
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').remove();
    });
    
  //实现对外接口
  jQuery.EexternalInterface = function(){
		uploader.upload();
	}
});