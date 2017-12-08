$(document).ready(function () {
    var $ssinfoID = $("#ssinfoID");
    var miniRefresh = new MiniRefresh({
        container: '#minirefresh',
        down: {
            isLock: true,
            isAuto: false,
            callback: function () {
                // 下拉事件
                //console.log("下拉事件")
                setTimeout(function () {
                    // 每次下拉刷新后，上拉的状态会被自动重置
                    miniRefresh.endDownLoading(true);
                }, 600);
            }
        },
        up: {
            isAuto: true,
            callback: function () {
                // 上拉事件
                //console.log("上拉事件")
                // 每次下拉刷新后，上拉的状态会被自动重置
                var pageNumInputVal = $("#pageNumID").val();
                if (!pageNumInputVal) {
                    pageNumInputVal = 1;
                }
                ajaxRequest("webyzcxSsxx/shoushuList", {pageNum: parseInt(pageNumInputVal)}, function (data) {
                    var list = data.list;
                    var pageNum = data.pageNum;
                    var pages = data.pages;
                    $("#pageNumID").val(parseInt(pageNum) + 1);
                    if (pageNum < pages) {
                        miniRefresh.endUpLoading(false);
                    } else {
                        miniRefresh.endUpLoading(true);
                    }
                    for (var i in list) {
                        var obj = list[i];
                        var brks = obj.brks;//病人科室
                        var brxm = obj.brxm;//病人姓名
                        var ssmc = obj.ssmc;//手术名称
                        var ssys = obj.ssys;//手术医生
                        var appendStr = "<tr><td>"+brks+"</td><td>"+brxm+"</td><td>"+ssmc+"</td><td>"+ssys+"</td></tr>";
                        $ssinfoID.append(appendStr);
                    }
                });
            }
        }
    });

});