$(document).ready(function () {


    var qianribfbVal = $("#qianribfbID").val();
    if (qianribfbVal > 0) {
        $("#dyhlvimgID").removeClass().addClass("mui-icon mui-icon-arrowthinup");
    } else {
        $("#dyhlvimgID").removeClass().addClass("mui-icon mui-icon-arrowthindown");
    }
    $("#dyhlvID").text(Math.abs((qianribfbVal * 100)).toFixed(2));


    ajaxRequest("webyzcxFeiYong/indexChart", null, function (data) {
        var zhuyuan = data.zhuyuan;
        var menzhen = data.menzhen;
        var kszhuyuan = data.kszhuyuan;
        var ksmenzhen = data.ksmenzhen;
        var zhuyuanPie_mzzybi = data.zhuyuanPie_mzzybi;
        var zhuyuanPie_ylypqt = data.zhuyuanPie_ylypqt;
        var dataNum = data.dataNum;
        if (dataNum) {
            $("#menzhenID").text(dataNum.menzhenzong.toFixed(2));
            $("#zhuyuanID").text(dataNum.zhuyuanzong.toFixed(2));
            $("#menzhenID2").text(dataNum.menzhenzong.toFixed(2));
            $("#zhuyuanID2").text(dataNum.zhuyuanzong.toFixed(2));
            $("#zongfeiID").text((dataNum.zhuyuanzong + dataNum.menzhenzong).toFixed(2));
            /////
            $("#yiliaoID").text(dataNum.yiliao.toFixed(2));
            $("#yaopinID").text(dataNum.yaopin.toFixed(2));
            $("#qitaID").text(dataNum.qita.toFixed(2));
        }


        //////////////////////////////////////////////////
        // 基于准备好的dom，初始化echarts实例
        var myChart_bar_inHospiTI = echarts.init(document.getElementById('bar-inHospiTI'), 'walden');
        var myChart_bar_outPatientTI = echarts.init(document.getElementById('bar-outPatientTI'), 'walden');
        var myChart_pie_hospiTI = echarts.init(document.getElementById('pie-hospiTI'), 'walden');
        var myChart_pie_incomeAssort = echarts.init(document.getElementById('pie-incomeAssort'), 'walden');
        var myChart_bar_departIncomeRank = echarts.init(document.getElementById('bar-departIncomeRank'), 'wonderland');
        var myChart_bar_outPatientIncomeRank = echarts.init(document.getElementById('bar-outPatientIncomeRank'), 'wonderland');
        // 配置项和数据（住院总收入）
        var option_bar_inHospiTI = {
            title: {
                text: ' ',
                subtext: '  单位：万元'
            },
            legend: {
                data: ['住院收入']
            },
            tooltip: {
                trigger: 'axis',
                formatter: function (v) {
                    var val=v[0].value / 10000;
                    return val.toFixed(2)+'万';
                },
                axisPointer: {
                    type: 'line'
                }
            },
            legend: {
                data: ['住院收入']
            },
            grid: {
                left: '4%',
                right: '8%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                axisLabel: {
                    show: true,
                    formatter: function (value) {
                        var val=value / 10000;
                        return val.toFixed(0)+'万';
                    },
                    interval: 0, //横轴信息全部显示
                    rotate: 30 //30度角倾斜显示
                },
                boundaryGap: [0, 0.1]
            },
            yAxis: {
                type: 'category',
                data: ['住院医疗费', '住院药费', '住院其他费']
            },
            series: [{
                name: '住院收入',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'right',
                        formatter: function (v) {
                            var val=v.value / 10000;
                            return val.toFixed(2)+'万';
                        }
                    }
                },
                data: zhuyuan,

                markLine: {
                    label: {
                        normal: {
                            formatter: function (v) {
                                var val=v.value / 10000;
                                return val.toFixed(2)+'万';
                            }
                        }
                    },
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

        // 配置项和数据（门诊总收入）
        var option_bar_outPatientTI = {
            title: {
                text: ' ',
                subtext: '  单位：万元'
            },
            tooltip: {
                trigger: 'axis',
                formatter: function (v) {
                    var val=v[0].value / 10000;
                    return val.toFixed(2)+'万';
                },
                axisPointer: {
                    type: 'line'
                }
            },
            legend: {
                data: ['门诊收入']
            },
            grid: {
                left: '4%',
                right: '10%',
                bottom: '5%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                axisLabel: {
                    show: true,
                    interval: 0, //横轴信息全部显示
                    rotate: 30, //-30度角倾斜显示
                    formatter: function (value) {
                        var val=value / 10000;
                        return val+'万';
                    }
                },
                boundaryGap: [0, 0.1]
            },
            yAxis: {
                type: 'category',
                data: ['门诊医疗费', '门诊药费', '门诊其他费']
            },
            series: [{
                name: '门诊收入',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'right',
                        formatter: function (v) {
                            var val=v.value / 10000;
                            return val.toFixed(2)+'万';
                        }
                    }
                },
                data: menzhen,
                markLine: {
                    label: {
                        normal: {
                            formatter: function (v) {
                                var val=v.value / 10000;
                                return val.toFixed(2)+'万';
                            }
                        }
                    },
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

        // 配置项和数据（全院总收入）
        var option_pie_hospiTI = {
            title: {
                text: ' ',
                subtext: '  单位：元'
            },
            legend: {
                x: 'center',
                y: 'top',
                data: ['门诊', '住院']

            },
            tooltip: {
                trigger: 'item',
                formatter: function(v){
                    var seriesName=v.seriesName;
                    var value=v.value;
                    var name=v.name;
                    var percent=v.percent;
                    var dis=seriesName+"<br/>"+name+" "+value.toFixed(2)+"("+percent+"%)"
                    return dis;
                }
            },
            series: [{
                name: '全院总收入',
                selectedMode: 'single',
                type: 'pie',
                center: ['50%', '55%'],
                data: zhuyuanPie_mzzybi,
                label: {
                    normal: {
                        formatter: '{b}\n {d}%',
                    }
                }
            }]
        };
        // 配置项和数据（全院收入类别占比）
        var option_pie_incomeAssort = {

            title: {
                text: ' ',
                subtext: '  单位：元'
            },
            legend: {
                x: 'center',
                y: 'top',
                data: ['药品', '医疗', '其他']

            },
            tooltip: {
                trigger: 'item',
                formatter: function(v){
                    var seriesName=v.seriesName;
                    var value=v.value;
                    var name=v.name;
                    var percent=v.percent;
                    var dis=seriesName+"<br/>"+name+" "+value.toFixed(2)+"("+percent+"%)"
                    return dis;
                }
            },
            series: [{
                name: '全院收入类别占比',
                selectedMode: 'single',
                type: 'pie',
                radius: '70%',
                center: ['50%', '55%'],
                data: zhuyuanPie_ylypqt,
                label: {
                    normal: {
                        formatter: '{b}\n {d}%',

                    }
                }
            }]
        };

        // 配置项和数据（住院科室收入排名（前十名））
        var option_bar_departIncomeRank = {
            title: {
                text: ' ',
                subtext: ' 单位：元',
                x: 'left'
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{a0}:{c0}k<br/>{a1}:{c1}k<br/>{a2}:{c2}k',
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
                    formatter: '{value}k',
                    interval: 0,
                    rotate: 30,
                }
            },
            yAxis: {
                type: 'category',
                data: ['血液内科', '呼吸内科', '创伤骨科', '产科', '脊柱外科', '关节外科', '干部保健/\n老年医学科', '心血管外科', '心血管内科', '康复科'],
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
                data: [320, 302, 301, 390, 330, 320, 320, 302, 301, 334]
            },
                {
                    name: '住院医疗费',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
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
                    data: [120, 132, 101, 90, 230, 210, 120, 132, 101, 101]
                },
                {
                    name: '住院其他费',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
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
                    data: [90, 230, 210, 120, 132, 101, 134, 90, 230, 90]
                }

            ]
        };

        // 配置项和数据（门诊科室收入排名（前十名））
        var option_bar_outPatientIncomeRank = {
            title: {
                text: ' ',
                subtext: ' 单位：元',
                x: 'left'
            },
            tooltip: {
                trigger: 'axis',
                formatter: '{a0}:{c0}k<br/>{a1}:{c1}k<br/>{a2}:{c2}k',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'line' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['住院药费', '住院医疗费', '住院其他费'],
                textStyle: {
                    fontSize: 12,
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
                boundaryGap: [0, 0.01],
                axisLabel: {
                    show: true,
                    formatter: '{value}k',
                    interval: 0,
                    rotate: 30,
                }
            },
            yAxis: {
                type: 'category',
                data: ['肿瘤防治中心', '心血管内科', '肾移植科', '消化内科', '神经内科', '产科', '急诊科', '健康管理科', '妇科', '儿内科'],
                name: ' '
            },
            series: [{
                name: '住院药费',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: false,
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
                data: [320, 302, 301, 390, 330, 320, 320, 302, 301, 334]
            },
                {
                    name: '住院医疗费',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
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
                    data: [120, 132, 101, 90, 230, 210, 120, 132, 101, 101]
                },
                {
                    name: '住院其他费',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
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
                    data: [90, 230, 210, 120, 132, 101, 134, 90, 230, 90]
                }

            ]
        };

        // 将图表显示出来
        myChart_bar_inHospiTI.setOption(option_bar_inHospiTI);
        myChart_bar_outPatientTI.setOption(option_bar_outPatientTI);
        myChart_pie_hospiTI.setOption(option_pie_hospiTI);
        myChart_pie_incomeAssort.setOption(option_pie_incomeAssort);
        myChart_bar_departIncomeRank.setOption(option_bar_departIncomeRank);
        myChart_bar_outPatientIncomeRank.setOption(option_bar_outPatientIncomeRank);
    });

    //主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();
})