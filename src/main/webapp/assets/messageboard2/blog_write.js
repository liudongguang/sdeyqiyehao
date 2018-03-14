var imgIdx = 1;
var loadIndex;
$(document).ready(function (e) {
    $("#content").keyup(function () {
        var jq_thisVal = $(this).val();
        if (jq_thisVal.length < 500) {
            var shengyushu = 500 - jq_thisVal.length;
            //$("#shengyu").text(shengyushu);
        } else {
            $("#content").val(jq_thisVal.substring(0, 500));
            alert("最多输入500字！");
        }
    })
    ajaxFormInitial($("#subformID"), function (data) {
        console.log(data)
    });
    $("#subBTID").click(function () {
        var lyuserid = $("#wxUserID").val();
        var content = $("#content").val();
        var title=$("#titleid").val();
        // if (!lyuserid) {
        //     layer.alert("未登录用户！");
        //     return false;
        // }
        if (!title) {
            layer.alert("未填写标题！");
            return false;
        }
        if (!content) {
            layer.alert("未填写留言内容！");
            return false;
        }
        $("#subformID").submit();
    });
})

function selectImg() {
    var len = $("#fileDiv input[type='file']").length;
    if (len >= 9) {
        alert('您最多上传9张图片');
        return;
    } else if (len == 0) {
        appendFile();
    }
    if ($.trim($("#file" + imgIdx).val()) == '') {
        $("#file" + imgIdx).click();
        return;
    }
    imgIdx++;
    appendFile();
    $("#file" + imgIdx).click();

}

function appendFile() {
    var fd = "<input type='file' name='file"
        + imgIdx
        + "' id='file"
        + imgIdx
        + "' accept='image/*' onchange='preview(this)' style='display:none'/>";
    $("#fileDiv").append(fd);
}

function appendPre() {
    var pv = "<img class='img_up' id='img_prev" + imgIdx
        + "' onclick='cleanImg(\"" + imgIdx + "\")'/>";
    $("#imgView").append(pv);
}
var $subform=$("#subform");
initPajaxFormRequestForClick("#contentID")
function sub() {
    if ($.trim($("#title").val()) == "") {
        alert('请输入标题');
        return;
    }
    if ($.trim($("#content").val()) == "") {
        alert('请输入内容');
        return;
    }

    $("#subbtn").val('执行中...');
    ajaxRequest("oarequest/checkSuggest", {
        "title": $("#title").val(),
        "content": $("#content").val()
    }, function (data) {
        if (data.errmsg) {
            $("#subbtn").val('提交');
            layer.alert(data.errmsg);
        }else{
            $subform.submit();
        }
    });

    $.post('checkSuggest', {
        "title": $("#title").val(),
        "content": $("#content").val()
    }, function (data) {
        if (data.errmsg) {
            layer.close(loadIndex);
            $("#subbtn").val('提交');
            layer.alert(data.errmsg);
        } else {

        }
    }, 'json');

}

function cleanImg(imgIdx) {
    if (confirm('您确认取消该图片?')) {
        $("#img_prev" + imgIdx).remove();
        $("#file" + imgIdx).remove();
    }
}

function preview(input) {
    if (input.files && input.files[0]) {
        if (!/\.(gif|jpg|jpeg|png|JPG|PNG)$/.test($(input).val())) {
            alert("图片类型只能是jpeg,jpg,png中的一种");
            $(input).val('');
            return;
        }
        appendPre();
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#img_prev' + imgIdx).attr('src', e.target.result).width(100)
                .height(150);
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        $('#img_prev' + imgIdx).remove();
    }
}