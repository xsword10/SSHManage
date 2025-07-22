var token = getCookie('jwt');

function userLogout() {
    isLoginFun();
    $.ajax({
        "async": true,
        type: 'POST',
        url: "http://localhost:8080/user/logout",
        headers: {
            'Authorization': `Bearer ${token}`
        },
        data: JSON.stringify(null),
        success: function(result) {
            deleteCookie('userName');
            deleteCookie('isLogin');
            deleteCookie('jwt');
            deleteCookie('nickname');
            deleteCookie('photo');
            alert(result.message);
            window.location.href = '../page/login.html';
        },
        error: function(xhr, status, error) {
            alert("发生异常！请重新登陆！")
            window.location.href = '../page/login.html';
        }
    })
}

function gotoUpdateUserInfo() {
    window.location.href = '../page/updateUserInfo.html';
}

function updateUserInfo() {
    var nickname = document.getElementById('nickname');
    var password = document.getElementById('password');
    var password_verify = document.getElementById('password_verify');
    if (!nickname.value) {
        alert("请先输入昵称");
        nickname.focus();
        return;
    }
    if(!password.value) {
        alert("请输入密码");
        password.focus();
        return;
    }
    if(!password_verify.value) {
        alert("请再次输入密码");
        password.focus();
        return;
    }

    var nicknameText = $("#nickname").val();
    var passwordTest = $("#password").val();
    var passwordVerifyTest = $("#password").val();

    if(passwordTest != passwordVerifyTest) {
        alert("两次密码输入不一致！")
        password_verify.focus();
        return;
    }

    var da = {
        "nickname":nicknameText,
        "password":passwordTest,
        "username":getCookie('username')
    };
    $.ajax({
        "async": true,
        "url": "http://localhost:8080/user/updateUser",
        "type": "POST",
        "data": JSON.stringify(da),
        "dataType": "json",
        "contentType": "application/json",
        "headers": {
            'Authorization': `Bearer ${token}`
        },
        success: function (result) {
            if(result.code == 200) {
                var data = result.data;
                alert(result.message);
                setCookie('nickname', data.nickname);
                window.location.href = "../page/machineManage.html";
            }else {
                alert(result.message);
                userLogout();
                window.location.href = "../page/login.html";
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // alert(jqXHR);
            //  //console.log(jqXHR);
            alert("发生异常！请重新登陆！")
            window.location.href = '../page/login.html';
        },
    });
}