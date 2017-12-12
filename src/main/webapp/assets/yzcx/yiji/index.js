$(document).ready(function () {
    var myChart = echarts.init(document.getElementById('container'));
    var myChart1 = echarts.init(document.getElementById('container1'));
    ajaxRequest("webyzcxyijixx/indexChart", null, function (data) {
        var yijiRenci=data.yijiRenci;
        var yijiHeji=data.yijiHeji;
        var yijiType=data.yijiType;
        var legendData=new Array();
        var legendData1=new Array();
        for(var index in yijiHeji){
            var obj=yijiHeji[index];
            legendData[index]=obj.name;
        }
        for(var index in yijiType){
            var obj=yijiType[index];
            legendData1[index]=obj.name;
        }
        option = {
            title : {
                text: '当日医技信息',
                subtext: '',
                x:'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c}<br/> ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data:legendData,
                top:20
            },
            series: [
                {
                    name:'医技人次',
                    type:'pie',
                    selectedMode: 'single',
                    radius: ['20%', '35%'],

                    label: {
                        normal: {
                            position: 'inner',
                            formatter: '{b}\n{d}%'
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:yijiRenci
                },
                {
                    name:'医技合计',
                    type:'pie',
                    radius: ['40%', '55%'],
                    selectedMode: 'single',
                    label: {
                        normal: {
                            formatter: '{b}\n{d}%',
                            backgroundColor: '#eee',
                            borderColor: '#aaa'
                        }
                    },
                    data:yijiHeji
                }
            ]
        };
        myChart.setOption(option);
        option1 = {
            title : {
                text: '医技类型',
                subtext: '',
                x:'center'
            },

            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series : [
                {
                    name: '医技类型',
                    type: 'pie',
                    radius: [0, '40%'],
                    center: ['60%', '60%'],
                    selectedMode: 'single',
                    label:{
                        normal: {
                            formatter: '{b}\n{d}%'
                        }
                    },
                    labelLine:{
                        normal:{
                            length:10,
                            length2:5
                        }
                    },
                    data: yijiType
                }
            ]
        };
        myChart1.setOption(option1);
    });


})