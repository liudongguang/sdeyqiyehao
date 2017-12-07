$(document).ready(function () {
    var miniRefresh = new MiniRefresh({
        container: '#minirefresh',
        down: {
            isLock:true,
            isAuto:false,
            callback: function() {
                // 下拉事件
                console.log("下拉事件")
                setTimeout(function() {
                    // 每次下拉刷新后，上拉的状态会被自动重置
                    miniRefresh.endDownLoading(true);
                }, 600);
            }
        },
        up: {
            isAuto:false,
            callback: function() {
                // 上拉事件
                console.log("上拉事件")
                setTimeout(function() {
                    // 每次下拉刷新后，上拉的状态会被自动重置
                    miniRefresh.endUpLoading(true);
                }, 600);
            }
        }
    });
    // ajaxRequest("webyzcxSsxx/shoushuList", null, function (data) {
    //
    // });
});