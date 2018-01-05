jQuery(document).ready(function () {
    var stringPatten=/^[a-zA-Z]*$/;
    var jq_disDIV = $("#disRSDIVID");
    var jq_addDataUL = $("#addULID");
    $("[fangbaidu_searurl]").each(function () {
        var jq_Input = $(this);//输入框
        var searchURL=jq_Input.attr("fangbaidu_searurl");//查询url
        jq_Input.keyup(
            function () {
                var getVal = $(this).val();
                if (getVal && getVal.length >= 1) {
                    var type=getSearchType(getVal);
                    if(type==2){
                        getVal=getVal.toUpperCase();
                    }
                    ajaxRequest(searchURL, {"searVal": getVal,"searchType":type}, function (data) {
                        var mdata = data.data;
                        jq_addDataUL.empty();
                        for (var index in mdata) {
                            createLI(mdata[index],jq_Input);
                        }
                        jq_disDIV.show();
                    });
                } else {
                    jq_disDIV.hide();
                    if(typeof(clickHandler)!='undefined'){
                        clickHandler("");
                    }
                    muithis.endPullupToRefresh(false);
                }
            });
    });
    function getSearchType(val){
       if(stringPatten.test(val)){
           return 2;
       }
        return 3;
    }
    function createLI(data,jq_Input) {
        var jq_li = $("<li  class='mui-table-view-cell'" + ">" + data.ksname + "</li>");
        jq_li.on("tap",function () {
            var jq_this = $(this);
            var val = jq_this.text();
            jq_Input.val(val);
            jq_disDIV.hide();
            if(typeof(clickHandler)!='undefined'){
                clickHandler(val);
            }
        })
        jq_addDataUL.append(jq_li);
    }

})