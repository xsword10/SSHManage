function userLogin() {
    var username = document.getElementById('username');
    var password = document.getElementById('password');
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

    var usernameText = $("#username").val();
    var passwordTest = $("#password").val();
    //alert(usernameText + " " + passwordTest);

    var da = {
        "username":usernameText,
        "password":passwordTest
    };
    commonAjaxPost(true, "http://localhost:8080/user/login", da, loginSuccess)
}

function loginSuccess(result) {
    if(result.code == 200) {
        var data = result.data;
        alert(result.message);
        setCookie('jwt', data.jwt);
        setCookie('username', data.username);
        setCookie('nickname', data.nickname);
        setCookie('photo', data.photo);
        window.location.href = "../page/machineManage.html";
    }else {
        alert(result.message);
        window.location.href = "../page/login.html";
    }
}
