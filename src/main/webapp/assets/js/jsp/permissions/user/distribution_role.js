jQuery(document).ready(function () {
    initBackUpBT("backBTID");
    initAjaxForm("subform", "mainContainer");
    var $permissionsContainer = $("#permissionsContainerID");
    var userid=$("input[name='userID']").val();
     newajaxRequest({
        paramurl: 'permission_shiro/getRoleList',
        dataType: 'json',
         data:{userid:userid},
        pushstate:false,
        successHandler: function (data) {
            var i=1;
            var allRole=data.allRole;
            for(var index in allRole){
                var obj=allRole[index];
                var rolename=obj.rolename;
                var uid=obj.uid;
                var addHTML = ' <div class="checkbox checkbox-primary checkbox-inline"><input type="checkbox" name="roleIDS"  value="{0}" id="inlineCheckbox{0}">   <label for="inlineCheckbox{0}">{1}</label></div>';
                addHTML=addHTML.format(uid,rolename);
                $permissionsContainer.append(addHTML);
                if (i % 5 == 0) {
                    $permissionsContainer.append("<br/>");
                }
                i++;
            }
            var ownRole=data.ownRole;
            for(var index in ownRole){
                var obj=ownRole[index];
                var uid=obj.uid;
                $("input[name='roleIDS'][value="+uid+"]").prop("checked",true);
            }
        }
    });
});