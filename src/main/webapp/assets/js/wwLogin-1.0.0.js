!function (a, b, c) {
    function d(a) {
        var c = b.createElement("iframe"),
            d = "https://open.work.weixin.qq.com/wwopen/sso/qrConnect?appid=" + a.appid + "&agentid=" + a.agentid + "&redirect_uri=" + a.redirect_uri + "&state=" + a.state + "&login_type=jssdk";
        d += a.style ? "&style=" + a.style : "", d += a.href ? "&href=" + a.href : "", c.src = d, c.frameBorder = "0", c.allowTransparency = "true", c.scrolling = "no", c.width = "300px", c.height = "400px";
        var e = b.getElementById(a.id);
        e.innerHTML = "", e.appendChild(c)
    }

    a.WwLogin = d
}(window, document);