jQuery(document).ready(function () {
    var pageNum = $("#pageNum").val();  //当前页数
    var pageSize = $("#pageSize").val();//一页上的条数
    var total = $("#total").val();//总条数
    var pages = $("#pages").val();//总页数
    var loadDataURL = $("#loadDataURL").val();//获取数据连接
    var searFormID = $("#searFormID").val();//有提交的表单
    var noAjaxPageVal = $("#noAjaxPage").val();
    var containerIDVal = $("#containerID").val();
    $("#pagesDIV").page({count: total, pageNo: pageNum, pageSize: pageSize, skipPart: true});
    //分页按钮点击事件
    $("#pagesDIV > div > ul > li ").click(function () {
        var num = $(this).attr("num");
        if ($(this).attr("class").indexOf("disabled") != -1) {
            return false;
        }
        if (num == 0 || num == (total + 1)) {

        } else {
            var subURL = loadDataURL + "?pageNum=" + num;
            if (searFormID && $("#" + searFormID).length != 0) {
                var formSerialize = $("#" + searFormID).serialize();
                subURL = subURL + "&" + formSerialize;
            }
            if (noAjaxPageVal == 1) {
                location.href = basePath + subURL;
            } else {
                pajaxRequest(subURL,'#' + containerIDVal);
            }
        }
    });
    $("#toPageNumID").keyup(function () {
        var val = $(this).val();
        $(this).val(val.replace(/[^\d]/g, ''));
        val = $(this).val();
        if (parseInt(val) > pages) {
            $(this).val(pages);
        }
    });
    /////跳转页面
    $("#pageRealBTID").click(function () {
        var num = $("#toPageNumID").val();
        if (!num) {
            layer.alert("请填写页码！");
            return false;
        }
        var subURL = loadDataURL + "?pageNum=" + num;
        if (searFormID) {
            var formSerialize = $("#" + searFormID).serialize();
            subURL = subURL + "&" + formSerialize;
        }
        if (noAjaxPageVal == 1) {
            location.href = basePath + subURL;
        } else {
            pajaxRequest(subURL,'#' + containerIDVal);
        }
    });
});
