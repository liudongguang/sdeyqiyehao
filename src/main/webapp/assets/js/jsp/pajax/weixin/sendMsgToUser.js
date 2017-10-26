jQuery(document).ready(function () {
    function handlerSuccess(data, statusText) {
        var errcode=data.errcode;
        var errmsg=data.errmsg;
        if(0!=errcode){
            layer.alert(errmsg)
        }
    }
    ajaxFormInitial($('form[ajaxform]'), handlerSuccess);
})
