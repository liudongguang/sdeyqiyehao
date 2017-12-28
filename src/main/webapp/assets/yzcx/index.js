$(document).ready(function() {
    // 基于准备好的dom，初始化echarts实例
    var myChart_pie_outPatient = echarts.init(document.getElementById('pie-outPatient'), 'walden');
    var myChart_bar_recipel = echarts.init(document.getElementById('bar-recipel'), 'walden');
    var myChart_bar_inHospital = echarts.init(document.getElementById('bar-inHospital'), 'walden');
    var myChart_pie_income = echarts.init(document.getElementById('pie-income'), 'walden');
    // 配置项和数据（门诊、急诊）
    var option_pie_outPatient = {

        title: {
            text: ' ',
            subtext: '  单位：人'
        },
        legend: {
            x: 'center',
            y: 'top',
            data: ['门诊', '急诊']

        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
            name: '全院门急诊',
            type: 'pie',
            radius: '70%',
            selectedMode: 'single',
            center: ['50%', '55%'],
            data: [{
                value: $("#menzhensumID").val(),
                name: '门诊'
            },
                {
                    value: $("#jizhensumID").val(),
                    name: '急诊'
                }
            ],
            label: {
                normal: {
                    show: true,
                    formatter:'{b}\n{d}%'
                },
                emphasis: {
                    show: true
                }
            },
            labelLine: {
                normal: {
                    smooth: 0.1,
                    length: 8,
                    length2: 10
                }
            },
            itemStyle: {
                normal: {
                    shadowBlur: 40,
                    shadowColor: 'rgba(0, 0, 0, 0.1)'
                }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function(idx) {
                return Math.random() * 200;
            }
        }]
    };

    // 配置项和数据（处方）
    var option_bar_recipel = {
        title: {
            text: ' ',
            subtext: '  单位：元',
            x: 'left',
            align: 'left'
        },
        legend: {
            data: ['最大处方金额', '平均处方金额','最小处方金额']
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (v) {
                var val=parseFloat(v[0].value).toFixed(2);
                return v[0].name+" <br/> "+v[0].seriesName+":"+val;
            },
            axisPointer: {
                type: 'line'
            }
        },
        grid: {
            left: '5%',
            right: '5%',
            bottom: '13%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                interval: 0, //横轴信息全部显示
                rotate: 30 //30度角倾斜显示
            },
            boundaryGap: [0, 0.1]
        },
        yAxis: {
            type: 'category',
            data: ['最大处方金额', '平均处方金额','最小处方金额']
        },
        series: [{
            name: '处方金额',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'right',
                    formatter: function (v) {
                        var val=parseFloat(v.value);
                        return val.toFixed(2);
                    }
                }
            },

            data: [{
                value: $("#maxchufangID").val(),
                name: '最大处方金额'
            },
                {
                    value: $("#pjchufangID").val(),
                    name: '平均处方金额'
                },
                {
                    value: $("#minchufangID").val(),
                    name: '最小处方金额'
                }],

        }]
    };

    // 配置项和数据（在院人数）
    var option_bar_inHospital = {
        title: {
            text: ' ',
            subtext: '  单位：个',
            x: 'left',
            align: 'left'
        },
        legend: {
            data: ['床位']
        },
        tooltip: {
            trigger: 'axis',
            formatter: '{b0}:{c0}',
            axisPointer: {
                type: 'line'
            }
        },
        grid: {
            left: '5%',
            right: '5%',
            bottom: '13%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                interval: 0, //横轴信息全部显示
                rotate: 30 //30度角倾斜显示
            },
            boundaryGap: [0, 0.1]
        },
        yAxis: {
            type: 'category',
            data: ['开放床位', '使用床位']
        },
        series: [{
            name: '床位',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },

            data: [{
                value: $("#kaifangID").val(),
                name: '开放床位'
            },
                {
                    value: $("#shizhanID").val(),
                    name: '使用床位'
                }],

        }]
    };

    // 配置项和数据（收入）
    var option_pie_income = {

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
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
            name: '全院昨日收入',
            type: 'pie',
            radius: '70%',
            selectedMode: 'single',
            center: ['50%', '55%'],
            label: {
                normal: {
                    show: true,
                    formatter:'{b}\n{d}%'
                },
                emphasis: {
                    show: true
                }
            },
            data: [{
                value: $("#yaoID").val(),
                name: '药品'
            },
                {
                    value: $("#yiLiaoID").val(),
                    name: '医疗'
                },
                {
                    value: $("#qiTaID").val(),
                    name: '其他'
                }
            ].sort(function(a, b) {
                return a.value - b.value;
            }),


            labelLine: {
                normal: {
                    smooth: 0.1,
                    length: 8,
                    length2: 10
                }
            },
            itemStyle: {
                normal: {
                    shadowBlur: 40,
                    shadowColor: 'rgba(0, 0, 0, 0.1)'
                }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function(idx) {
                return Math.random() * 200;
            }
        }]
    };

    // 将图表显示出来
    myChart_pie_outPatient.setOption(option_pie_outPatient);
    myChart_bar_recipel.setOption(option_bar_recipel);
    myChart_bar_inHospital.setOption(option_bar_inHospital);
    myChart_pie_income.setOption(option_pie_income);

    //主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();
})