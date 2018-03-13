$(document).ready(function(e) {
    //获取话题列表
    $("p[class=blog_main_p]").each(function (t) {
        var content=$(this).text();
        $(this).empty().append(replace_em(content))
    })

    mui.init({
        pullRefresh : {
            container:"refreshContainer",//待刷新区域标识，querySelector能定位的css选择器均可，比如：id、.class等
            up : {
                height:50,//可选.默认50.触发上拉加载拖动距离
                auto:true,//可选,默认false.自动上拉加载一次
                contentrefresh : "正在加载...",//可选，正在加载状态时，上拉加载控件上显示的标题内容
                contentnomore:'没有更多数据了',//可选，请求完毕若没有更多数据时显示的提醒内容；
                //必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
                callback :function () {
                    console.log(111111)
                }
            }
        }
    });
});
