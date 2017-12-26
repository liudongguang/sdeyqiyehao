$(document).ready(function () {
    initTapHandler();
    //主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();
    //日期选择
    (function ($) {
        $.init();
        var result = $('#result')[0];
        var btns = $('.btn');
        btns.each(function (i, btn) {
            btn.addEventListener('tap', function () {
                var optionsJson = this.getAttribute('data-options') || '{}';
                var options = JSON.parse(optionsJson);
                var id = this.getAttribute('id');
                /*
                 * 首次显示时实例化组件
                 * 示例为了简洁，将 options 放在了按钮的 dom 上
                 * 也可以直接通过代码声明 optinos 用于实例化 DtPicker
                 */
                var picker = new $.DtPicker(options);
                picker.show(function (rs) {
                    /*
                     * rs.value 拼合后的 value
                     * rs.text 拼合后的 text
                     * rs.y 年，可以通过 rs.y.vaue 和 rs.y.text 获取值和文本
                     * rs.m 月，用法同年
                     * rs.d 日，用法同年
                     * rs.h 时，用法同年
                     * rs.i 分（minutes 的第二个字母），用法同年
                     */
                    result.innerText = rs.text;
                    /*
                     * 返回 false 可以阻止选择框的关闭
                     * return false;
                     */
                    /*
                     * 释放组件资源，释放后将将不能再操作组件
                     * 通常情况下，不需要示放组件，new DtPicker(options) 后，可以一直使用。
                     * 当前示例，因为内容较多，如不进行资原释放，在某些设备上会较慢。
                     * 所以每次用完便立即调用 dispose 进行释放，下次用时再创建新实例。
                     */
                    picker.dispose();
                    var date = rs.text + "-01";
                    location.href = "webyzcxZyxx/zhuyuan_yue_page?start=" + date;
                });
            }, false);
        });
    })(mui);
    var yue_ryrc = echarts.init(document.getElementById('yue-ryrc'), 'wonderland');
    var yue_ksry = echarts.init(document.getElementById('yue-ksry'), 'wonderland');
    var yue_tongqi = echarts.init(document.getElementById('yue-tongqi'), 'wonderland');
    ajaxRequest("webyzcxZyxx/zhuyuan_yue_chart", {start: $('#result').text() + "-01"}, function (data) {
        var everyDayData = data.everyDayData;
        var ksryData = data.ksryData;
        var tongqiData = data.tongqiData;
        var option_everyday = {
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'line' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '8%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                name: '单位：人',
                nameLocation: 'center',
                nameTextStyle: {
                    padding: [30, 0, 0, 0]
                },
                axisLabel: {
                    show: true,
                    formatter: '{value}',
                    interval: 0, //横轴信息全部显示
                    rotate: 30 //-30度角倾斜显示
                }
            },
            yAxis: {
                type: 'category',
                data: everyDayData.category_rydays
            },
            series: [{
                name: '入院人数',
                type: 'bar',
                stack: '总量',
                barWidth: 15,
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                markLine: {
                    data: [{
                        type: 'average',
                        name: '平均值',
                        lineStyle: {
                            normal: {
                                shadowColor: 'rgba(0, 0, 0, 0.5)',
                                shadowBlur: 20,
                                shadowOffsetX: 1,
                                shadowOffsetY: 2
                            }
                        }
                    }]
                },
                markPoint: {
                    data: [{
                        type: 'max',
                        name: '最大值',
                        itemStyle: {
                            normal: {
                                shadowColor: 'rgba(0, 0, 0, 0.3)',
                                shadowBlur: 40,
                                shadowOffsetX: 2,
                                shadowOffsetY: 5
                            }
                        }
                    },
                        {
                            type: 'min',
                            name: '最小值',
                            itemStyle: {
                                normal: {
                                    shadowColor: 'rgba(0, 0, 0, 0.3)',
                                    shadowBlur: 40,
                                    shadowOffsetX: 2,
                                    shadowOffsetY: 5
                                }
                            }
                        }

                    ]
                },

                data: everyDayData.ksruyuanrs
            }
            ]
        };
        yue_ryrc.setOption(option_everyday);
        ////////////////////////////
        var option_ksruyuanopt = {
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'line' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '8%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                name: '单位：人',
                nameLocation: 'center',
                nameTextStyle: {
                    padding: [20, 0, 0, 0]
                },
                axisLabel: {
                    show: true,
                    formatter: '{value}',
                    interval: 0, //横轴信息全部显示
                    rotate: 30 //-30度角倾斜显示
                }
            },
            yAxis: {
                type: 'category',
                data: ksryData.category_ruyuanks,
                axisLabel: {
                    formatter: function (value, index) {
                        if (value.length > 6) {
                            var valuetemp = value.substring(0, 6);
                            valuetemp = valuetemp + "\n" + value.substring(6, value.length);
                            value = valuetemp;
                        }
                        return value;
                    }
                }
            },
            series: [
                {
                    name: '入院数',
                    type: 'bar',
                    stack: '总量',
                    barWidth: 15,
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: ksryData.ruyuanData
                }
            ]
        };
        yue_ksry.setOption(option_ksruyuanopt);
        /////////////////////////////////
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
                data: tongqiData.category_ruyuanks_tongqi
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
                    data: tongqiData.ruyuanData_tongqi_qunian,
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
        yue_tongqi.setOption(tongqi_option);
    });
})