$(document).ready(function () {
    Highcharts.setOptions({global: {useUTC: false}});
    ajaxRequest("webyzcxFeiYong/indexChart", null, function (data) {
        $('#container').highcharts(data.zhuyuan);
        $('#container1').highcharts(data.menzhen);
        $('#container2').highcharts(data.kszhuyuan);
        $('#container3').highcharts(data.ksmenzhen);
        $('#container4').highcharts(data.zhuyuanPie_mzzybi);
        $('#container5').highcharts(data.zhuyuanPie_ylypqt);
        var dataNum = data.dataNum;
        $("#menzhenID").text(dataNum.menzhenzong.toFixed(2));
        $("#zhuyuanID").text(dataNum.zhuyuanzong.toFixed(2));
        $("#zongfeiID").text((dataNum.zhuyuanzong + dataNum.menzhenzong).toFixed(2));
        $("#yiliaoID").text(dataNum.yiliao.toFixed(2));
        $("#yaopinID").text(dataNum.yaopin.toFixed(2));
        $("#qitaID").text(dataNum.qita.toFixed(2));
    });
    var qianribfbVal = $("#qianribfbID").val();
    var zzStr = "";
    if (qianribfbVal > 0) {
        $("#dyhlvimgID").attr("src", "assets/yzcx/image/up.png");
    } else {
        $("#dyhlvimgID").attr("src", "assets/yzcx/image/down.png");
    }
    zzStr += (Math.abs((qianribfbVal * 100)).toFixed(2)) + "%";
    $("#dyhlvID").text(zzStr);
})