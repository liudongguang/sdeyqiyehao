$(document).ready(function() {
    var liAs = $("#navMenuID > ul > li > a");
    var navNum=$("#navNum").val();
    if(navNum){
        liAs.removeClass("active");
        var $lia=liAs.eq(navNum);
        $lia.addClass("active");
    }
});