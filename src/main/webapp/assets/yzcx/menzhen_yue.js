$(document).ready(function() {
    $('#monthly').monthpicker({
        years: [2017],
        topOffset: 6,
        onMonthSelect: function(m, y) {
            var d1 = new Date()
            var d2=new Date(y,(m+1),1);
            //选择的日期要小于当前日期
            if(d1>d2){
                 var month=d2.getMonth().toString();
                 if(month.length==1){
                     month="0"+month;
                 }
                 var date=d2.getFullYear()+"-"+month+"-01";
                location.href="webyzcx/menzhen_yue?start="+date;
            }else{
                alert("必须选择前一个月日期！");
            }
        }
    })
    Highcharts.setOptions({ global: { useUTC: false } });
    ajaxRequest("webyzcx/menzhen_yueChart", {start:$('#monthly').val()}, function (data) {
        $('#container').highcharts(data.menzhenChart);
    });
})