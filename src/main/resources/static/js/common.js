function commonAjaxPost(async, url, data, success, err) {
    $.ajax({
        "async": async,
        "url": url,
        "type": "POST",
        "data": JSON.stringify(data),
        "dataType": "json",
        "contentType": "application/json",
        success: success || function (data) {
            // //console.log(data)
        },
        error: err || function (jqXHR, textStatus, errorThrown) {
            // alert(jqXHR);
            //  //console.log(jqXHR);
        },
    });
}

var cookiesPath = '/';
var cookiesTime = 3 / 24;

function refreshCookie() {
    var cookieData = $.cookie();
    $.each(cookieData, function (_key, _value) {
        $.cookie(_key, _value, {path: cookiesPath, expires: cookiesTime});
    });
}

function hasCookie(key) {
    return new RegExp('(^|;\\s*)${encodeURIComponent(key)}=').test(document.cookie);
}

function setCookie(key, value) {
    refreshCookie();
    $.cookie(key, value, {path: cookiesPath, expires: cookiesTime});
}

function getCookie(key) {
    return $.cookie(key);
}

function deleteCookie(key) {
    $.removeCookie(key, {path: cookiesPath});
}

function clearCookie() {
    var cookieData = $.cookie();
    $.each(cookieData, function (key, value) {
        deleteCookie(key);
    })
}

function clearCookieSingle(name) {
    setCookie(name, "", -1);
}

function isLoginFun() {
    var isLogin = getCookie('isLogin');
    if(isLogin != "1") {
        alert("用户未登陆！请先前往登陆！");
        window.location.href = '../page/login.html';
    }
}