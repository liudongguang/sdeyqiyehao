$(document).ready(function () {
    var myChart = echarts.init(document.getElementById('container'));
    ajaxRequest("webyzcxHuanzhexx/indexChart", null, function (data) {
          var ageStrList=data.ageStrList;
          var nanNum=data.nanNum;
          var nvNum=data.nvNum;
        for(var item in nvNum) {
            nvNum[item]=nvNum[item]*-1;
        }
        option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data:['女','男'],
                left: 'center',
                top:280
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
                    data : ageStrList
                },
                {
                    type : 'category',
                    axisTick : {show: false},
                    data : ageStrList
                }
            ],
            series : [
                {
                    name:'男',
                    type:'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true
                        }
                    },
                    data:nanNum
                },
                {
                    name:'女',
                    type:'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            formatter:function(data){
                                return Math.abs(data.data);
                            }
                        }
                    },
                    data:nvNum
                }
            ]
        };
        myChart.setOption(option);
    });


})