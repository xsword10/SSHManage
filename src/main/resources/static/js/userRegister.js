function userRegister() {
    var username = document.getElementById('username');
    var password = document.getElementById('password');
    var code = document.getElementById('code');
    if (!username.value) {
        alert("请先输入用户名");
        username.focus();
        return;
    }
    if(!password.value) {
        alert("请输入密码");
        password.focus();
        return;
    }
    if(!code.value) {
        alert("请输入验证码");
        code.focus();
        return;
    }

    var usernameText = $("#username").val();
    var passwordTest = $("#password").val();
    var codeText = $("#code").val();
    //alert(usernameText + " " + passwordTest);

    var da = {
        "username":usernameText,
        "password":passwordTest,
        "code":codeText
    };
    commonAjaxPost(true, "http://localhost:8080/user/register", da, registerSuccess)
}

function registerSuccess(result) {
    if(result.code == 200) {
        alert(result.message);
        window.location.href = "../page/login.html";
    }else {
        alert(result.message);
        window.location.href = "../page/register.html";
    }
}
