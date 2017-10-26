jQuery(document).ready(function () {
    initBackUpBT("backBTID");
    initAjaxForm("subform", "mainContainer",{paramurl:'permission_shiro/checkUserName'});
});