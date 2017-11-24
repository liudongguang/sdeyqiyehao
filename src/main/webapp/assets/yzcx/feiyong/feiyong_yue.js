$(document).ready(function() {
    $('#monthly').monthpicker({
        years: [2017],
        topOffset: 6,
        onMonthSelect: function(m, y) {
            var d1 = new Date()
            var d2=new Date(y,m,1);
            //选择的日期要小于当前日期
            if(d1>d2){
                 var month=d2.getMonth().toString();
                 month=parseInt(month)+1;
                 if(month.toString().length==1){
                     month="0"+month;
                 }
                 var date=d2.getFullYear()+"-"+month+"-01";
                 var url="webyzcxFeiYong/feiyong_yue_page?start="+date;
                 location.href=url;
            }else{
                alert("不能大于本月！");
            }
        }
    })
    Highcharts.setOptions({ global: { useUTC: false } });
    ajaxRequest("webyzcxFeiYong/feiyong_yue_chart", {start:$('#monthly').val()+"-01"}, function (data) {
        $('#container').highcharts(data.meitianChart);
        $('#container1').highcharts(data.kszhuyuanpaimingChart);
        $('#container2').highcharts(data.ksmenzhenpaimingChart);
    });
})