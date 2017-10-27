jQuery(document).ready(function () {
    initPajaxFormRequestForClick("#pajaxMainContainer");
    initPajaxRequestForClick("#pajaxMainContainer");
    var $permissionsContainer = $("#permissionsContainerID");
    var roleid = $("input[name='roleID']").val();
    ajaxRequest("permission_shiro/getPermissionList", {roleid: roleid}, function (data) {
        var allPermission = data.allPermission;
        var i = 1;
        for (var index in allPermission) {
            var obj = allPermission[index];
            var permissionname = obj.permissionname;
            var uid = obj.uid;
            var description = obj.description;
            var addHTML = '<div class="form-check form-check-inline"><label class="form-check-label"><input class="form-check-input" type="checkbox" name="permissionIDS" id="inlineCheckbox{0}" value="{0}"> {1} </label></div>';
            addHTML = addHTML.format(uid, permissionname);
            $permissionsContainer.append(addHTML);
            if (i % 5 == 0) {
                $permissionsContainer.append("<br/>");
            }
            i++;
        }
        var ownPermission = data.ownPermission;
        for (var index in ownPermission) {
            var obj = ownPermission[index];
            var uid = obj.uid;
            $("input[name='permissionIDS'][value=" + uid + "]").prop("checked", true);
        }
    });
});