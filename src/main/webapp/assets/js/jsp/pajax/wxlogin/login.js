jQuery(document).ready(function () {
    var url=encodeURIComponent("http://www.astelaya.cn/ldgwxqiyehao/pajaxwxlogin/enter");
    window.WwLogin({
        "id": "wx_reg",
        "appid": "wxb84c4a0aae33b149",
        "agentid": "1000004",
        "redirect_uri": url,
        "state": "xxx",
        "href": ""
    });
});