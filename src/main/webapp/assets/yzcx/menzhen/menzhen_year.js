$(document).ready(function () {
    var year_menzhenbar = echarts.init(document.getElementById('year-menzhenbar'), 'wonderland');
    var year_tongqi = echarts.init(document.getElementById('year-tongqi'), 'wonderland');
    initTapHandler();
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
                });
            }, false);

        });
    })(mui, document);

    //主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();
    ajaxRequest("webyzcx/menzhen_year_chart", null, function (data) {
        var yearaxis = data.menzhe_year.axis;
        var yearjzdata = data.menzhe_year.jzdata;
        var yearptdata = data.menzhe_year.ptdata;
        var yearoption = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['门诊', '急诊']
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
                    data: yearaxis,
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
                    name: '门诊',
                    type: 'bar',
                    stack: '总人数',
                    label: {
                        normal: {
                            show: false,
                            position: 'insideRight'
                        }
                    },
                    data: yearptdata
                }, {
                    name: '急诊',
                    type: 'bar',
                    stack: '总人数',
                    label: {
                        normal: {
                            show: false,
                            position: 'insideRight'
                        }
                    },
                    data: yearjzdata
                }
            ]
        };
        year_menzhenbar.setOption(yearoption);
        ///////////////////////////////////////////////
        var tongqi_axis = data.menzheTongqi_year.axis;
        var tongqi_menzhenData = data.menzheTongqi_year.menzhenData;
        var tongqi_jizhenData = data.menzheTongqi_year.jizhenData;
        var tongqi_option = {
            title: {
                text: ' ',
                subtext: ' '
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['门诊量', '急诊量']
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
                data: tongqi_axis
            }],
            yAxis: [{
                type: 'value',
                name: '单位：人',
                axisLabel: {
                    show: true,
                    formatter: function (value, index) {
                            value = value / 1000 + 'K';
                        return value;
                    }
                },
            }],
            series: [{
                name: '门诊量',
                type: 'bar',
                data: tongqi_menzhenData,
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter: function(v){
                            v.value=v.value/1000+"K"
                            return v.value
                        }
                    },
                },

                markLine: {
                    label: {
                        normal: {
                            show: false,
                            position: 'middle'
                        }
                    },
                    data: [{
                        type: 'average',
                        name: '平均值'
                    }]
                },
            },
                {
                    name: '急诊量',
                    type: 'bar',
                    data: tongqi_jizhenData,
                    label: {
                        normal: {
                            show: true,
                            position: 'top',
                            formatter: function(v){
                                v.value=v.value/1000+"K";
                                return v.value
                            }
                        },
                    },
                    markLine: {
                        label: {
                            normal: {
                                show: false,
                                position: 'middle'
                            }
                        },
                        data: [{
                            type: 'average',
                            name: '平均值'
                        }]
                    },
                }
            ]
        };
        year_tongqi.setOption(tongqi_option);

    });
})