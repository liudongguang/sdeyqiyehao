jQuery(document).ready(function () {
    ajaxRequest('oauth2/getUserInfoByCode',{code:$("#codeID").val()},function (data) {
        if(data.errcode==0){
             $("#avatarID").attr("src",data.avatar);
            $("#nameID").text(data.name);
            $("#mobileID").text(data.mobile);
            $("#emailID").text(data.email);
        }else{
            layer.alert(data.errmsg);
        }
    })
})