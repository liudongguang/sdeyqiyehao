var $ssinfoID = $("#infoID");
var muithis=null;
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
                    ajaxRequest("webyzcxSsxx/shoushuList", {pageNum: parseInt(pageNumInputVal),"ksName":ksNameIDVal}, function (data) {
                        handlerData(data,false,muithis)
                    });
                } //必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
            }
        }
    });
});

function handlerData(data, clickState,muithis) {
    if (clickState) {
        $("#infoID tr").not(':eq(0)').empty();
    }
    var list = data.list;
    var pageNum = data.pageNum;
    var pages = data.pages;
    var total=data.total;
    $("#memcount").text(total);
    $("#pageNumID").val(parseInt(pageNum) + 1);
    if (pageNum < pages) {
        //console.log("还有数据 pageNum="+pageNum+"  pages="+pages)
        muithis.endPullupToRefresh(false);
        muithis.enablePullupToRefresh();
    } else {
        //console.log("数据没有了 pageNum="+pageNum+"  pages="+pages)
        muithis.endPullupToRefresh(true);
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
        var appendStr = $("<tr><td>" + brks + "</td><td>" + brxm + "</td><td>" + ssmc + "</td><td>" + ssys + "</td></tr>");
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
        appendStr.on("tap",function () {
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
        handlerData(data,true,muithis);
    });
}