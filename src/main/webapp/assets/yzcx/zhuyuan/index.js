$(document).ready(function () {
    initTapHandler();
    //主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();
    var ri_ryrc = echarts.init(document.getElementById('ri-ryrc'), 'walden');
    var ri_ryrs = echarts.init(document.getElementById('ri-ryrs'), 'wonderland');
    var ri_cwsy = echarts.init(document.getElementById('ri-cwsy'), 'wonderland');
    ajaxRequest("webyzcxZyxx/indexChart", null, function (data) {
        var rybd = data.rybd; //入院波动
        var ksry = data.ksry;//科室入院情况
        var chuangwei = data.chuangwei;//床位
        // 指定图表的配置项和数据
        var option_line = {
            title: {
                text: ' ',
                subtext: ' '
            },
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '3%',
                right: '6%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: rybd.category

            },
            yAxis: {
                type: 'value',
                name: '单位（人）'
            },
            series: [
                {
                    name: '入院人数',
                    type: 'line',
                    line: '总量',
                    data: rybd.zhexianNum,
                    markPoint: {
                        data: [{
                            type: 'max',
                            name: '最大值'
                        },
                            {
                                type: 'min',
                                name: '最小值'
                            }
                        ]
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
                    }

                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        ri_ryrc.setOption(option_line);
        ///////////////////////////
        // 指定图表的配置项和数据
        var option_bar_ruyuan = {
            title: {
                text: ' ',
                subtext: '  单位：人'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'line'
                }
            },

            grid: {
                left: '2%',
                right: '8%',
                bottom: '0%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01],
                axisLabel: {
                    interval: 0, //横轴信息全部显示
                    rotate: 30, //-30度角倾斜显示
                }
            },
            yAxis: {
                type: 'category',
                data: ksry.category
            },
            series: [{
                name: '入院数',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'right'
                    }
                },
                data: ksry.ruyuanData,
                barWidth: 15,
                markLine: {
                    lineStyle: {
                        normal: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0,
                                    color: '#626f91' // 0% 处的颜色
                                }, {
                                    offset: 1,
                                    color: '#5e6a89' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            },
                        }
                    },
                    data: [{
                        type: 'average',
                        name: '平均值'
                    }]
                }

            }]
        };
        ri_ryrs.setOption(option_bar_ruyuan);
        ////////////////////////////////////////
        // 指定图表的配置项和数据
        var option_chuangwei = {
            title: {
                text: ' ',
                subtext: '  单位：人'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                data: ['实占床位','开放床位']
            },
            grid: {
                left: '2%',
                right: '5%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                axisLabel: {
                    interval: 0, //横轴信息全部显示
                    rotate: 30, //-30度角倾斜显示
                },
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: chuangwei.category
            },
            series: [{
                name: '实占床位',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
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
                markLine: {
                    data: [{
                        type: 'average',
                        name: '平均值'
                    }]
                },
                data: chuangwei.ksshizhan
            },
                {
                    name: '开放床位',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
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
                    markLine: {
                        data: [{
                            type: 'average',
                            name: '平均值'
                        }]
                    },
                    data: chuangwei.kskaifang
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        ri_cwsy.setOption(option_chuangwei);
    });
})