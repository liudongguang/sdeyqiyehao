var loadIndex;
var openLayerWindowID;
$(document).on('pjax:beforeSend', function () {
    NProgress.start();
})
$(document).on('pjax:send', function () {
    NProgress.set(0.3);
    loadIndex = layer.load(0, {
        shade: [0.8, '#fff']
    });
})
$(document).on('pjax:complete', function (xhr, textStatus) {
    NProgress.set(0.6);
    layer.close(loadIndex);
})
$(document).on('pjax:end', function () {
    NProgress.done();
})
$(document).on('pjax:error', function (xhr, textStatus, errorThrown) {
    NProgress.done();
    if (textStatus.status == 404) {
        layer.alert("无此页面！");
    } else if (textStatus.status == 0) {
        layer.alert("服务器已关闭！");
    } else {
        layer.alert("服务器错误！ 错误码：" + textStatus.status);
    }
})
//post方式这里不生效
$(document).on('pjax:timeout', function (event) {
    // Prevent default timeout redirection behavior
    NProgress.done();
    layer.alert("请求超时！");
    event.preventDefault();
})
/////////////////////////////////////////////////////
function pajaxRequest(url, continerID, pushstate, pushdata) {
    var push = true;
    if (!pushstate) {
        push = pushstate;
    }
    var data = {};
    if (pushdata) {
        data = pushdata;
    }
    //post不用设置超时时间
    $.pjax({url: url, container: continerID, type: "post", push: push, data: data})
}
////////普通ajax提交
function ajaxRequest(url, requestData, handlerFunction, datatype) {
    var dataType = "json";
    if (datatype) {
        dataType = datatype;
    }
    $.ajax({
        url: url,
        data: requestData,
        type: "post",
        dataType: dataType,
        success: handlerFunction,
        beforeSend: function () {
            loadIndex = layer.load(0, {
                shade: [0.8, '#fff']
            });
        },
        complete: function () {
            layer.close(loadIndex);
        },
        error: function (xhr, textStatus, errorThrown) {
            if ("timeout" == textStatus) {
                layer.alert("请求超时错误！");
            } else if ("notmodified" == textStatus) {
                layer.alert("未定义错误！");
            } else if ("parsererror" == textStatus) {
                layer.alert("解析错误！");
            } else if ("error" == textStatus) {
                layer.alert("服务器错误！");
            } else {
                layer.alert("不知道什么错反正出错了");
            }
        }
    })
}
//////pajax提交
function initPajaxRequestForClick(continerID) {
    var $click = $("[pajax-data]");
    $click.click(function () {
        var href = $(this).attr("href");
        var del = $(this).attr("del");
        if (href) {
            if (del||del=="") {
                layer.confirm('请谨慎操作！', {icon: 3, title: '删除条目'}, function (index) {
                    //do something
                    pajaxRequest(href, continerID);
                    layer.close(index);
                });
            } else {
                pajaxRequest(href, continerID);
            }
        } else {
            layer.alert("缺少请求地址！");
        }
        return false;
    });
}
////初始化异步提交form表单，使用pajax提交表单
function initPajaxFormRequestForClick(continerID) {
    var $form = $("[pajax-form]");
    //$.pjax.submit(event, '#pjax-container')
    $form.submit(function (event) {
        var action = $(this).attr("action");
        var checkurl = $(this).attr("checkurl");
        if (checkurl) {
            var data = {};
            //要检查的属性
            $(this).find("[checkparam]").each(function () {
                var $checkParam = $(this);
                var pname = $checkParam.attr("name");
                var pvalue = $checkParam.val();
                data[pname] = pvalue;
            })
            ajaxRequest(checkurl, data, function (data) {
                if (data.errcode == 1) {
                    layer.alert(data.errmsg);
                } else {
                    if (action) {
                        $.pjax.submit(event, continerID)
                    } else {
                        layer.alert("表单没有提交路径！");
                    }
                }
            });
        } else {
            if (action) {
                $.pjax.submit(event, continerID)
            } else {
                layer.alert("表单没有提交路径！");
            }
        }
        return false;
    })
}
///////////初始化异步提交form表单
function ajaxFormInitial($form, successFun) {
    $form.on('submit', function (e) {
        e.preventDefault(); // prevent native submit
        var checkurl = $(this).attr("checkurl");
        var action = $(this).attr("action");
        var $thisform = $(this);
        if (checkurl) {
            var data = {};
            //要检查的属性
            $(this).find("[checkparam]").each(function () {
                var $checkParam = $(this);
                var pname = $checkParam.attr("name");
                var pvalue = $checkParam.val();
                data[pname] = pvalue;
            })
            ajaxRequest(checkurl, data, function (data) {
                if (data.errcode == 1) {
                    layer.alert(data.errmsg);
                } else {
                    //检查通过
                    if (action) {
                        $thisform.ajaxSubmit({
                            beforeSubmit: function () {
                                loadIndex = layer.load(0, {
                                    shade: [0.8, '#fff']
                                });
                            },
                            success: function (data, status) {
                                layer.close(loadIndex);
                                layer.close(openLayerWindowID);
                                successFun(data, status);
                            },
                            error: function () {
                                layer.close(loadIndex);
                                layer.alert("请求出错！")
                            }
                        })
                    } else {
                        layer.alert("表单没有提交路径！");
                    }
                }
            });
            return false;
        } else {
            $thisform.ajaxSubmit({
                beforeSubmit: function () {
                    loadIndex = layer.load(0, {
                        shade: [0.8, '#fff']
                    });
                },
                success: function (data, status) {
                    layer.close(loadIndex);
                    layer.close(openLayerWindowID);
                    successFun(data, status);
                },
                error: function () {
                    layer.close(loadIndex);
                    layer.alert("请求出错！")
                }
            })
        }
    });
}
//字符串替换函数
String.prototype.format = function () {
    if (arguments.length == 0) return this;
    for (var s = this, i = 0; i < arguments.length; i++)
        s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
    return s;
};
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
function initTapHandler() {
   $("span[url]").click(function () {
       var href=$(this).attr("url");
       mui.openWindow({ url:href, id:'detail' });
   });
}


var wanyuanLabelFormatter={
    normal: {
        formatter: function (v) {
            var val=v.value / 10000;
            return val.toFixed(2)+'万';
        }
    }
};