var miniRefresh =null;
var $infoID = $("#infoID");
$(document).ready(function () {
    miniRefresh = new MiniRefresh({
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
                var ksNameIDVal=$("#ksNameID").val();
                ajaxRequest("webyzcxyjhlxx/pageinfo", {pageNum: parseInt(pageNumInputVal),"ksName":ksNameIDVal}, function (data) {
                    handlerData(data);
                });
            }
        }
    });

});
function handlerData(data,clickState){
    if(clickState){
        $infoID.empty();
    }
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
        var zyhm = obj.zyhm;//住院号码
        var brxm = obj.brxm;//病人姓名
        var brxb = obj.brxb;//病人性别
        var brks = obj.brks;//病人科室
        var brbq = obj.brbq;//病人病区
        var kssj = obj.kssj;//护理开始时间
        var tzsj = obj.tzsj;//护理停止时间
        var yzmc = obj.yzmc;//名称

        var kssjDate = new Date(kssj);
        var kssjDate = kssjDate.Format("yyyy-MM-dd hh:mm:ss");
        var tzsjDate = "";
        if (tzsj) {
            var tzsjDate = new Date(tzsj);
            var tzsjDate = tzsjDate.Format("yyyy-MM-dd hh:mm:ss");
        }
        var appendStr = $("<tr><td>" + brks + "</td><td>" + brxm + "</td><td>" + kssjDate + "</td><td>" + tzsjDate + "</td></tr>");
        appendStr.attr({
            zyhm: zyhm,
            brxm: brxm,
            brxb: brxb,
            brks: brks,
            brbq: brbq,
            kssj: kssjDate,
            tzsj: tzsjDate,
            yzmc: yzmc
        });
        appendStr.click(function () {
            var $this = $(this);
            layer.open({
                type: 2,
                title: '手术详情',
                shadeClose: true,
                shade: 0.8,
                area: ['80%', '70%'],
                content: 'yzcx/yijihuli/disOne.jsp?zyhm=' +
                $this.attr("zyhm") +
                "&brxm=" + $this.attr("brxm")
                + "&brxb=" + $this.attr("brxb")
                + "&brks=" + $this.attr("brks")
                + "&brbq=" + $this.attr("brbq") +
                "&kssj=" + $this.attr("kssj") +
                "&tzsj=" + $this.attr("tzsj") +
                "&yzmc=" + $this.attr("yzmc") //iframe的url
            });
        });
        $infoID.append(appendStr);

    }
}
function clickHandler(ksname) {
    $("#ksNameID").val(ksname);
    ajaxRequest("webyzcxyjhlxx/pageinfo", {pageNum:1,"ksName":ksname}, function (data) {
        handlerData(data,true);
    });
}