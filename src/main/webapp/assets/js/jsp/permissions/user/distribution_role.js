jQuery(document).ready(function () {
    initPajaxFormRequestForClick("#pajaxMainContainer");
    initPajaxRequestForClick("#pajaxMainContainer");
    var $rolesContainerID = $("#rolesContainerID");
    var userid=$("input[name='userID']").val();
    ajaxRequest("permission_shiro/getRoleList", {userid:userid}, function (data) {
        var allRole = data.allRole;
        var i = 1;
        for (var index in allRole) {
            var obj = allRole[index];
            var rolename = obj.rolename;
            var uid = obj.uid;
            var addHTML = '<div class="form-check form-check-inline"><label class="form-check-label"><input class="form-check-input" type="checkbox" name="roleIDS" id="inlineCheckbox{0}" value="{0}"> {1} </label></div>';
            addHTML = addHTML.format(uid, rolename);
            $rolesContainerID.append(addHTML);
            if (i % 5 == 0) {
                $rolesContainerID.append("<br/>");
            }
            i++;
        }
        var ownRole=data.ownRole;
        for(var index in ownRole){
            var obj=ownRole[index];
            var uid=obj.uid;
            $("input[name='roleIDS'][value="+uid+"]").prop("checked",true);
        }
    });
});