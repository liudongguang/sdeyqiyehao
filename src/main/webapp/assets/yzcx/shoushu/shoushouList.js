var $ssinfoID = $("#ssinfoID");
var miniRefresh = null;
$(document).ready(function () {
    initTapHandler();
//主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();
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
                var ksNameIDVal = $("#ksNameID").val();
                ajaxRequest("webyzcxSsxx/shoushuList", {
                    pageNum: parseInt(pageNumInputVal),
                    "ksName": ksNameIDVal
                }, function (data) {
                    handlerData(data);
                });
            }
        }
    });
});

function handlerData(data, clickState) {
    if (clickState) {
        $ssinfoID.empty();
    }
    var list = data.list;
    var pageNum = data.pageNum;
    var pages = data.pages;
    $("#pageNumID").val(parseInt(pageNum) + 1);
    //console.log(pageNum + "    " + pages);
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

        var ssrq = obj.ssrq;
        var ssfj = obj.ssfj;//手术分级
        var brnl = obj.brnl;//病人年龄
        var brxb = obj.brxb;//病人性别
        var ssrqDate = new Date(ssrq);
        var ssrqStr = ssrqDate.Format("yyyy-MM-dd hh:mm:ss");
        var appendStr = $("<tr><td>" + brks + "</td><td>" + brxm + "</td><td>" + ssmc + "</td><td>" + ssys + "</td><td><span class=\"mui-icon iconfont icon-xiangqing1\"></span></td></tr>");
        appendStr.attr({
            brks: brks,
            brxm: brxm,
            ssmc: ssmc,
            ssys: ssys,
            ssrq: ssrqStr,
            ssfj: ssfj,
            brnl: brnl,
            brxb: brxb
        });
        appendStr.click(function () {
            var $this = $(this);
            layer.open({
                type: 2,
                title: '手术详情',
                shadeClose: true,
                shade: 0.8,
                area: ['80%', '70%'],
                content: 'yzcx/shoushu/shoushou_disOne.jsp?brks='+$this.attr("brks")+"&brxm="+$this.attr("brxm")+"&ssmc="+$this.attr("ssmc")+"&ssys="+$this.attr("ssys")+"&ssrq="+$this.attr("ssrq")+"&ssfj="+$this.attr("ssfj")+"&brnl="+$this.attr("brnl")+"&brxb="+$this.attr("brxb") //iframe的url
            });
            // layer.load(0, {
            //     shade: [0.8, '#fff']
            // });
           // mui.openWindow({url: 'yzcx/shoushu/shoushou_disOne.jsp?brks='+$this.attr("brks")+"&brxm="+$this.attr("brxm")+"&ssmc="+$this.attr("ssmc")+"&ssys="+$this.attr("ssys")+"&ssrq="+$this.attr("ssrq")+"&ssfj="+$this.attr("ssfj")+"&brnl="+$this.attr("brnl")+"&brxb="+$this.attr("brxb"), id: 'detail'});
        });

        $ssinfoID.append(appendStr);

    }
}

function clickHandler(ksname) {
    $("#ksNameID").val(ksname);
    ajaxRequest("webyzcxSsxx/shoushuList", {pageNum: 1, "ksName": ksname}, function (data) {
        handlerData(data, true);
    });
}