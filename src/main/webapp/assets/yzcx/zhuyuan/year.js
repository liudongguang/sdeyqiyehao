$(document).ready(function () {
    initTapHandler();
    //主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();
    //年份选择
    (function ($, doc) {
        $.init();
        $.ready(function () {
            //普通示例
            var userPicker = new $.PopPicker();
            userPicker.setData([{

                text: '2017'
            }, {

                text: '2016'
            }, {

                text: '2015'
            }]);
            var showUserPickerButton = doc.getElementById('showUserPicker');
            var userResult = doc.getElementById('result');
            showUserPickerButton.addEventListener('tap', function (event) {
                userPicker.show(function (items) {
                    userResult.innerText = items[0].text;
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                    location.href = "webyzcxZyxx/zhuyuan_year?start=" + items[0].text + "-01-01";
                });
            }, false);
        });
    })(mui, document);
    var year_ruyuanbar = echarts.init(document.getElementById('year-ruyuanbar'), 'wonderland');
    var year_tongqi = echarts.init(document.getElementById('year-tongqi'), 'wonderland');
    ajaxRequest("webyzcxZyxx/zhuyuan_year_chart", {start: $('#result').text() + "-01-01"}, function (data) {
        if(!data){
            return false;
        }
        var ruyuanMonth = data.ruyuanMonth;
        var tongqi = data.tongqi;
        var yearoption = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ruyuanMonth.category_ruyuan_months,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '入院人次',
                    type: 'bar',
                    stack: '总人数',
                    label: {
                        normal: {
                            show: false,
                            position: 'insideRight'
                        }
                    },
                    data: ruyuanMonth.monthNum
                }
            ]
        };
        year_ruyuanbar.setOption(yearoption);
        ///////////////////////////////////////////////
        var tongqi_option = {
            title: {
                text: ' ',
                subtext: ' '
            },
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '4%',
                right: '5%',
                bottom: '3%',
                containLabel: true
            },
            calculable: true,
            xAxis: [{
                type: 'category',
                data: tongqi.category_ruyuan_tongqi
            }],
            yAxis: [{
                type: 'value',
                name: '单位：人',
                axisLabel: {
                    show: true,
                    formatter: function (value, index) {
                        if (value > 10000) {
                            value = value / 1000 + 'K';
                        }
                        return value;
                    }
                },
            }],
            series: [
                {
                    name: '入院数',
                    type: 'bar',
                    data: tongqi.ruyuanData_tongqi,
                    label: {
                        normal: {
                            show: true,
                            position: 'top',
                            formatter: function(v){
                                if(v.value>10000){
                                    v.value=v.value/1000+"K"
                                }
                                console.log(v)
                                return v.value
                            }
                        },
                    }
                }
            ]
        };
        year_tongqi.setOption(tongqi_option);
    });
});