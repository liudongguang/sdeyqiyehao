var muithis =null;
var $infoID = $("#infoID");
$(document).ready(function () {
    initTapHandler();
    //主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();
    mui.init({
        pullRefresh : {
            container:"#pullrefresh",//待刷新区域标识，querySelector能定位的css选择器均可，比如：id、.class等
            up : {
                height:50,//可选.默认50.触发上拉加载拖动距离
                auto:true,//可选,默认false.自动上拉加载一次
                contentrefresh : "正在加载...",//可选，正在加载状态时，上拉加载控件上显示的标题内容
                contentnomore:'没有更多数据了',//可选，请求完毕若没有更多数据时显示的提醒内容；
                callback :function () {
                    var pageNumInputVal = $("#pageNumID").val();
                    if (!pageNumInputVal) {
                        pageNumInputVal = 1;
                    }
                    var ksNameIDVal=$("#ksNameID").val();
                    muithis=this;
                    ajaxRequest("webyzcxyjhlxx/pageinfo", {pageNum: parseInt(pageNumInputVal),"ksName":ksNameIDVal}, function (data) {
                        handlerData(data,false,muithis)
                    });
                } //必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
            }
        }
    });

});
function handlerData(data,clickState,muithis){
    if(clickState){
        $infoID.empty();
    }
    var list = data.list;
    var pageNum = data.pageNum;
    var pages = data.pages;
    $("#pageNumID").val(parseInt(pageNum) + 1);
    if (pageNum < pages) {
        //console.log("还有数据")

        muithis.endPullupToRefresh(false);
        muithis.enablePullupToRefresh();

    } else {
        //console.log("数据没有了")
        muithis.endPullupToRefresh(true);
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
        var appendStr = $("<tr class='content-box-cell'><td>" + brks + "</td><td>" + brxm + "</td><td>" + kssjDate + "</td><td>" + tzsjDate + "</td></tr>");
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
        appendStr.on("tap",function () {
            var $this = $(this);
            layer.open({
                type: 2,
                title: '手术详情',
                shadeClose: true,
                shade: 0.8,
                area: ['80%', '75%'],
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
        handlerData(data,true,muithis);
    });
}