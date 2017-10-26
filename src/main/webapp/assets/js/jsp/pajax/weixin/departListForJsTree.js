jQuery(document).ready(function () {
    var $jstree = $("#jstree_div");
    ///
    $.jstree.defaults.contextmenu.items = {
        "mcreate": {
            "label": "新建部门",
            "action": function (data) {
                var inst = jQuery.jstree.reference(data.reference), obj = inst
                    .get_node(data.reference);
                var newID = inst.create_node(obj, {
                    "type": "text",
                    "text": "新建部门"
                });
                inst.open_node(obj);
                var thisID = obj.id;
                var createNode = inst.get_node(newID);
                inst.edit(createNode, '', function (obj, isupdate) {
                    if (isupdate) {
                        //console.log("修改！")
                        ajaxRequest("pajaxwx/addDepartForTree", {parentid: thisID, name: obj.text}, function (data) {
                            var errcode = data.errcode;
                            var errmsg = data.errmsg;
                            if (0 == errcode) {
                                ajaxRequest("pajaxwx/getDepartForJsTree", {}, function (resposedata) {
                                    $jstree.jstree(true).settings.core.data = resposedata;//放入数据
                                    $jstree.jstree(true).refresh();//刷新数据
                                    layer.alert("创建部门成功！");
                                });
                            } else {
                                layer.alert(errmsg);
                            }
                        });

                    } else {
                        //console.log("未修改！")
                        inst.delete_node(obj);
                    }

                })
            }
        },
        "mdelete": {
            "label": "删除部门",
            "action": function (data) {
                var inst = jQuery.jstree.reference(data.reference), obj = inst
                    .get_node(data.reference);
                layer.confirm('删除部门?', {
                    icon: 3,
                    title: '请谨慎操作'
                }, function (index) {
                    // do something
                    /////
                    ajaxRequest("pajaxwx/delDepartForTree", {
                        "id": obj.id
                    }, function (data) {
                        var errcode = data.errcode;
                        var errmsg = data.errmsg;
                        if (errcode == 0) {
                            inst.delete_node(obj);
                        } else {
                            layer.alert(errmsg);
                        }
                        layer.close(index);
                    });
                    /////
                });
            }
        },
        "mrename": {
            "label": "重命名",
            "action": function (data) {
                //var ref = $('#jstreeID').jstree(true), sel = ref.get_selected(), obj = ref.get_node(sel);
                var inst = jQuery.jstree.reference(data.reference), obj = inst
                    .get_node(data.reference);
                var oldText = obj.text;
                inst.edit(obj, oldText, function (obj) {
                    //修改的时候触发修改
                    if (obj.text != oldText) {
                        ajaxRequest("pajaxwx/updateDepartForTree", {
                            "id": obj.id,
                            "name": obj.text,
                            "parentid": obj.parent
                        }, function (data) {
                            var errcode = data.errcode;
                            var errmsg = data.errmsg;
                            if (errcode == 0) {
                                layer.alert("修改完成！");
                            } else {
                                layer.alert(errmsg);
                            }
                        });
                    }
                });
            }
        },
        "departments": {
            "label": "部门员工",
            "action": function (data) {
                //var ref = $('#jstreeID').jstree(true), sel = ref.get_selected(), obj = ref.get_node(sel);
                var inst = jQuery.jstree.reference(data.reference), obj = inst
                    .get_node(data.reference);
                pajaxRequest('pajaxwx/getDepartMemberByDepartment_id',"#disFormTreediv",false,{"id": obj.id});
            }
        }
    };
    ///
    ajaxRequest("pajaxwx/getDepartForJsTree", {}, function (resposedata) {
        $jstree.jstree({
            'core': {
                'data': resposedata,
                "check_callback": true
            },
            "plugins": ["dnd", "unique", "changed", "contextmenu", "wholerow"]
        });

        $jstree
            .on("select_node.jstree", function (e, data) {
                console.log(e)
                console.log(data)
                //console.log(data.changed.selected); // newly selected
                //console.log(data.changed.deselected); // newly deselected
            })
        $jstree
            .on("move_node.jstree", function (e, data) {
                var currentParentid = data.parent;
                var id=data.node.id;
                ajaxRequest("pajaxwx/updateDepartForTree", {
                    "id": id,
                    "parentid": currentParentid
                }, function (data) {
                    var errcode = data.errcode;
                    var errmsg = data.errmsg;
                    if (errcode == 0) {
                        layer.alert("修改完成！");
                    } else {
                        layer.alert(errmsg);
                    }
                });
            })

    });

})
