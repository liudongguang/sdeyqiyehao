$(document).ready(function () {
    var myChart = echarts.init(document.getElementById('container'));
    ajaxRequest("webyzcxHuizhen/indexChart", null, function (data) {
        var legendData=data.legendData;
       var shenqing=data.shenqing;
       var jieshou=data.jieshou;
        for(var item in shenqing) {
            shenqing[item]=shenqing[item]*-1;
        }
        option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                },
                formatter: function (obj,b,c) {
                    var axisValue=obj[0].axisValue;
                    var ss=axisValue+"<br/>";
                    for(var item in obj) {
                        var it=obj[item];
                        var value=it.value;
                        ss+=it.marker+" "+it.seriesName+":"+Math.abs(value)+"<br/>";
                    }
                    return ss;
                }
            },
            legend: {
                data: ['会诊申请','会诊接受'],
                left: 'center',
                top:450
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '8%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'value',
                    axisLabel:{
                        formatter:function(data){
                            return Math.abs(data);
                        }
                    }
                }
            ],
            yAxis : [
                {
                    type : 'category',
                    axisTick : {show: false},
                    data : legendData
                }
            ],
            series : [
                {
                    name:'会诊申请',
                    type:'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position:'left',
                            formatter:function(data){
                                return Math.abs(data.data);
                            }
                        }
                    },
                    data:shenqing
                },
                {
                    name:'会诊接受',
                    type:'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            formatter:function(data){
                                return Math.abs(data.data);
                            },
                            position:'right'
                        }
                    },
                    data:jieshou
                }
            ]
        };
        myChart.setOption(option);
    });


})