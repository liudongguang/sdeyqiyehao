$(document).ready(function () {
    var ri_menzhenLine = echarts.init(document.getElementById('ri-menzhenLine'), 'walden');
    var ri_yuyuebar = echarts.init(document.getElementById('ri-yuyuebar'), 'wonderland');
    var ri_jibingbar = echarts.init(document.getElementById('ri-jibingbar'), 'wonderland');

    ajaxRequest("webyzcx/menzhenChart", null, function (data) {
        var xAxisData = data.menzhen.xAxis;
        var ptdata = data.menzhen.ptdata;
        var jzdata = data.menzhen.jzdata;
        // 指定图表的配置项和数据
        var option_line = {
            title: {
                text: ' ',
                subtext: ' '
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['普通门诊', '急诊']

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
                data: xAxisData

            },
            yAxis: {
                type: 'value',
                name: '单位（人）'
            },
            series: [{
                name: '普通门诊',
                type: 'line',
                line: '总量',
                data: ptdata,
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
                        name: '平均值',

                    }],
                }

            },
                {
                    name: '急诊',
                    type: 'line',
                    line: '总量',
                    data: jzdata,
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
        ri_menzhenLine.setOption(option_line);
        //////////////////////////////
        var yuyueks = data.yuyue.ksList;
        var yuyueksData = data.yuyue.yydata;
        // 指定图表的配置项和数据
        var option_bar_yuyue = {
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
            legend: {
                data: ['预约']
            },
            grid: {
                left: '2%',
                right: '3%',
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
                data: yuyueks
            },
            series: [{
                name: '预约',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'right'
                    }
                },
                data: yuyueksData,
                barWidth:15,
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
       // console.log(JSON.stringify(option_bar_yuyue))
        ri_yuyuebar.setOption(option_bar_yuyue);
        ////////////////////////////////////////
        var jbList = data.jibing.jbList;
        var jbdata = data.jibing.jbdata;
        var option_bar_jibing = {
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
            legend: {
                data: ['预约']
            },
            grid: {
                left: '2%',
                right: '3%',
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
                data: jbList,axisLabel:{
                    formatter:function(value,index){
                        if(value.length>6){
                            value=value.substring(0,6);
                            value+="\n"+value.substring(6,value.length);
                            console.log(value);
                        }
                        return value;
                    }
                }
            },
            series: [{
                name: '预约',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'right'
                    }
                },
                data: jbdata,
                barWidth:15,
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
        }
        ri_jibingbar.setOption(option_bar_jibing);
    });
})

