var share = [];
var s_width = $(window).width();
var s_height = $(window).height();
$(function(){
	share.initPage();
	
})
share.initPage = function(){
	var i = 0;
    var img_array = $(".bgImg img");
    img_array.fadeOut(0);
    img_array.eq(0).fadeIn(2000);
    setInterval(function () {
        if (i < img_array.length - 1) {
            img_array.eq(i).fadeOut(2000);
            img_array.eq(i + 1).fadeIn(2000);
            i++;
        } else {
            img_array.eq(i).fadeOut(2000);
            img_array.eq(0).fadeIn(2000);
            i = 0;
        }
    }, 4000);
}