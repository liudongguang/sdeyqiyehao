$(document).ready(function() {
    $('#monthly').monthpicker({
        years: [2017],
        topOffset: 6,
        onMonthSelect: function(m, y) {
            var d1 = new Date()
            var d2=new Date(y,(m+1),1);
            //选择的日期要小于当前日期
            if(d1>d2){

            }else{
                alert("必须选择前一个月日期！");
            }
        }
    })

    Highcharts.setOptions({ global: { useUTC: false } });
    ajaxRequest("webyzcx/menzhen_yueChart", null, function (data) {
        $('#container').highcharts(data.menzhen);
    });
})