$(document).ready(function () {
    initTapHandler();
    var yue_menzhenbar = echarts.init(document.getElementById('yue-menzhenbar'), 'wonderland');
    var yue_ksmenzhenbar = echarts.init(document.getElementById('yue-ksmenzhenbar'), 'wonderland');
    var yue_jbzhanbibar = echarts.init(document.getElementById('yue-jbzhanbibar'), 'wonderland');
    var yue_tongqi = echarts.init(document.getElementById('yue-tongqi'), 'wonderland');
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
                    location.href = "webyzcx/menzhen_yue?start=" + date;
                });
            }, false);
        });
    })(mui);
    //主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();

    ajaxRequest("webyzcx/menzhen_yueChart", {start: $('#result').text() + "-01"}, function (data) {
        var everyday_axis = data.everyDayOneMonthChart.axis;
        var everyday_jzdata = data.everyDayOneMonthChart.jzdata;
        var everyday_ptdata = data.everyDayOneMonthChart.ptdata;

        var option_everyday = {
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'line' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['普通门诊', '急诊']
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
                data: everyday_axis
            },
            series: [{
                name: '普通门诊',
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

                data: everyday_ptdata
            },
                {
                    name: '急诊',
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

                    data: everyday_jzdata
                }
            ]
        };
        yue_menzhenbar.setOption(option_everyday);
        ///////////////////////////////////////月科室门诊人次（前十名）
        var yueks_axis = data.menzhenChart.axis;
        var yueks_jzdata = data.menzhenChart.jzdata;
        var yueks_ptdata = data.menzhenChart.ptdata;

        var option_yueks = {
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'line' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['普通门诊', '急诊']
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
                data: yueks_axis,
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
            series: [{
                name: '普通门诊',
                type: 'bar',
                stack: '总量',
                barWidth: 15,
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },

                data: yueks_ptdata
            },
                {
                    name: '急诊',
                    type: 'bar',
                    stack: '总量',
                    barWidth: 15,
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },

                    data: yueks_jzdata
                }
            ]
        };
        yue_ksmenzhenbar.setOption(option_yueks);
        ////////////////////////////////////////////////////疾病
        var jibing_axis = data.jibingChart.axis;
        var jibing_data = data.jibingChart.numdata;
        var option_jibing = {
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
                data: jibing_axis,
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
                    name: '疾病',
                    type: 'bar',
                    stack: '总量',
                    barWidth: 15,
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: jibing_data
                }
            ]
        };
        yue_jbzhanbibar.setOption(option_jibing);
        //////////////////////////////////////////////同期
        var tongqi_axis = data.tongqimenzhenChart.axis;
        var tongqi_menzhenData = data.tongqimenzhenChart.menzhenData;
        var tongqi_jizhenData = data.tongqimenzhenChart.jizhenData;
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
                        if (value > 10000) {
                            value = value / 1000 + 'K';
                        }
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
                            if(v.value>10000){
                                v.value=v.value/1000+"K"
                            }
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
                                if(v.value>10000){
                                    v.value=v.value/1000+"K"
                                }
                                console.log(v)
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
        yue_tongqi.setOption(tongqi_option);
    });
});