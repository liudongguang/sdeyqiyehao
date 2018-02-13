$(document).ready(function () {
    initTapHandler();
    var yue_rishouru = echarts.init(document.getElementById('yue-rishouru'), 'wonderland');
    var yue_zykssr = echarts.init(document.getElementById('yue-zykssr'), 'wonderland');
    var yue_mzkssr = echarts.init(document.getElementById('yue-mzkssr'), 'wonderland');
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
                    location.href = "webyzcxFeiYong/feiyong_yue_page?start=" + date;
                });
            }, false);
        });
    })(mui);
    //主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();
    ajaxRequest("webyzcxFeiYong/feiyong_yue_chart", {start: $('#result').text() + "-01"}, function (data) {
        var meitianChart=data.meitianChart;
        var kszhuyuan=data.kszhuyuan;
        var ksmenzhen=data.ksmenzhen;
        var option_everyday = {
            title: {
                text: ' ',
                subtext: ' 单位：万元',
                x: 'left'
            },
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
                    formatter: function (value) {
                        var val=value / 10000;
                        return val+'万';
                    },
                    interval: 0,
                    rotate: 30,
                }
            },
            yAxis: {
                type: 'category',
                data: meitianChart.categoriesDays
            },
            series: [
                {
                    name: '日收入',
                    type: 'bar',
                    stack: '总量',
                    barWidth: 15,
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight',
                            formatter: function (v) {
                                var val=v.value / 10000;
                                return val.toFixed(2)+'万';
                            }
                        }
                    },
                    markLine: {
                        data: [{
                            type: 'average',
                            label:wanyuanLabelFormatter,
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
                            label:wanyuanLabelFormatter,
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
                                label:wanyuanLabelFormatter,
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

                    data: meitianChart.series_barData
                }
            ]
        };
        yue_rishouru.setOption(option_everyday);
        /////
        // 配置项和数据（住院科室收入排名（前十名））
        var zy_keshi=kszhuyuan.zy_keshi;
        var zy_yiliao=kszhuyuan.zy_yiliao;
        var zy_yaofei=kszhuyuan.zy_yaofei;
        var zy_qita=kszhuyuan.zy_qita;
        var option_bar_departIncomeRank = {
            title: {
                text: ' ',
                subtext: ' 单位：万元',
                x: 'left'
            },
            tooltip: {
                trigger: 'axis',
                formatter: function (v) {
                    var val=v[0].value / 10000;
                    var val1=v[1].value / 10000;
                    var val2=v[2].value / 10000;
                    return v[0].name+" <br/> "+v[0].seriesName+":"+val.toFixed(2)+'万'+"<br/>"+" "+v[1].seriesName+":"+val1.toFixed(2)+'万'+"<br/>"+" "+v[2].seriesName+":"+val2.toFixed(2)+'万';
                },
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'line' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['住院药费', '住院医疗费', '住院其他费']
            },
            grid: {
                left: '3%',
                right: '5%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01],
                axisLabel: {
                    show: true,
                    formatter: function (value) {
                        var val=value / 10000;
                        return val+'万';
                    },
                    interval: 0,
                    rotate: 30,
                }
            },
            yAxis: {
                type: 'category',
                data: zy_keshi,
                axisTick: {
                    interval: 0
                }

            },
            series: [{
                name: '住院药费',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: false,
                        position: 'insideRight',
                        formatter: function (v) {
                            var val=v.value / 10000;
                            return val.toFixed(2)+'万';
                        }
                    }
                },
                markPoint: {
                    data: [{
                        type: 'max',
                        label:wanyuanLabelFormatter,
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
                            label:wanyuanLabelFormatter,
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
                markLine: {
                    label:wanyuanLabelFormatter,
                    data: [{
                        type: 'average',
                        name: '平均值'
                    }]
                },
                data: zy_yaofei
            },
                {
                    name: '住院医疗费',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'insideRight',
                            formatter: function (v) {
                                var val=v.value / 10000;
                                return val.toFixed(2)+'万';
                            }
                        }
                    },
                    markPoint: {
                        data: [{
                            type: 'max',
                            label:wanyuanLabelFormatter,
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
                                label:wanyuanLabelFormatter,
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
                    markLine: {
                        label:wanyuanLabelFormatter,
                        data: [{
                            type: 'average',
                            name: '平均值'
                        }]
                    },
                    data: zy_yiliao
                },
                {
                    name: '住院其他费',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'right',
                            formatter: function (v) {
                                if(v.value==0){
                                    return "";
                                }
                                var val=v.value / 10000;
                                return val.toFixed(2)+'万';
                            }
                        }
                    },
                    markPoint: {
                        data: [{
                            type: 'max',
                            label:wanyuanLabelFormatter,
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
                                label:wanyuanLabelFormatter,
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
                    markLine: {
                        label:wanyuanLabelFormatter,
                        data: [{
                            type: 'average',
                            name: '平均值'
                        }]
                    },
                    data: zy_qita
                }

            ]
        };

        // 配置项和数据（门诊科室收入排名（前十名））
        var mz_keshi=ksmenzhen.mz_keshi;
        var mz_yiliao=ksmenzhen.mz_yiliao;
        var mz_yaofei=ksmenzhen.mz_yaofei;
        var mz_qita=ksmenzhen.mz_qita;
        var option_bar_outPatientIncomeRank = {
            title: {
                text: ' ',
                subtext: ' 单位：万元',
                x: 'left'
            },
            tooltip: {
                trigger: 'axis',
                formatter: function (v) {
                    var val=v[0].value / 10000;
                    var val1=v[1].value / 10000;
                    var val2=v[2].value / 10000;
                    return v[0].name+" <br/> "+v[0].seriesName+":"+val.toFixed(2)+'万'+"<br/>"+" "+v[1].seriesName+":"+val1.toFixed(2)+'万'+"<br/>"+" "+v[2].seriesName+":"+val2.toFixed(2)+'万';
                },
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'line' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['住院药费', '住院医疗费', '住院其他费']
            },
            grid: {
                left: '3%',
                right: '5%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01],
                axisLabel: {
                    show: true,
                    formatter: function (value) {
                        var val=value / 10000;
                        return val+'万';
                    },
                    interval: 0,
                    rotate: 30,
                }
            },
            yAxis: {
                type: 'category',
                data: mz_keshi,
                axisTick: {
                    interval: 0
                }

            },
            series: [{
                name: '住院药费',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: false,
                        position: 'insideRight',
                        formatter: function (v) {
                            var val=v.value / 10000;
                            return val.toFixed(2)+'万';
                        }
                    }
                },
                markPoint: {
                    data: [{
                        type: 'max',
                        label:wanyuanLabelFormatter,
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
                            label:wanyuanLabelFormatter,
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
                markLine: {
                    label:wanyuanLabelFormatter,
                    data: [{
                        type: 'average',
                        name: '平均值'
                    }]
                },
                data: mz_yaofei
            },
                {
                    name: '住院医疗费',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'insideRight',
                            formatter: function (v) {
                                var val=v.value / 10000;
                                return val.toFixed(2)+'万';
                            }
                        }
                    },
                    markPoint: {
                        data: [{
                            type: 'max',
                            name: '最大值',
                            label:wanyuanLabelFormatter,
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
                                label:wanyuanLabelFormatter,
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
                    markLine: {
                        label:wanyuanLabelFormatter,
                        data: [{
                            type: 'average',
                            name: '平均值'
                        }]
                    },
                    data: mz_yiliao
                },
                {
                    name: '住院其他费',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'right',
                            formatter: function (v) {
                                if(v.value==0){
                                    return "";
                                }
                                var val=v.value / 10000;
                                return val.toFixed(2)+'万';
                            }
                        }
                    },
                    markPoint: {
                        data: [{
                            type: 'max',
                            label:wanyuanLabelFormatter,
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
                                label:wanyuanLabelFormatter,
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
                    markLine: {
                        label:wanyuanLabelFormatter,
                        data: [{
                            type: 'average',
                            name: '平均值'
                        }]
                    },
                    data: mz_qita
                }

            ]
        };
        yue_zykssr.setOption(option_bar_departIncomeRank);
        yue_mzkssr.setOption(option_bar_outPatientIncomeRank);
    });

})