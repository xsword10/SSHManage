var token = getCookie('jwt');
var pages = 1;

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
            deleteCookie('username');
            deleteCookie('isLogin');
            deleteCookie('jwt');
            deleteCookie('nickname');
            deleteCookie('photo');
            alert(result.message);
            window.location.href = '../page/login.html';
        },
        error: function(xhr, status, error) {
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
            userLogout();
            window.location.href = '../page/login.html';
        },
    });
}

var machines;

function onload() {
    document.getElementById("nickname").innerText = getCookie('nickname');
    document.getElementById('username').innerText = getCookie('username');
    const img = document.getElementById('img')
    img.src = getCookie('photo');

    countMachines();
    listMachines();
}

function countMachines() {
    var da = {
        "username": getCookie('username')
    }
    $.ajax({
        "async": true,
        "url": "http://localhost:8080/machine/countMachines",
        "type": "POST",
        "data": JSON.stringify(da),
        "dataType": "json",
        "contentType": "application/json",
        "headers": {
            'Authorization': `Bearer ${token}`
        },
        success: function (result) {
            if(result.code == 200) {
                var count = result.data;
                setCookie('countMachine', count);
                var pages = Math.floor(count / 10);
                if(count % 10 !== 0)
                    pages++;
                setCookie('pages', pages);
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

function deleteMachine(event) {
    var rank = event.target.getAttribute('data-row');
    var da = {
        "id": machines[rank].machineId
    }
    $.ajax({
        "async": true,
        "url": "http://localhost:8080/machine/deleteMachine",
        "type": "POST",
        "data": JSON.stringify(da),
        "dataType": "json",
        "contentType": "application/json",
        "headers": {
            'Authorization': `Bearer ${token}`
        },
        success: function (result) {
            if(result.code == 200) {
                alert(result.message);
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
            alert("发生异常！请重新登陆！");
            userLogout();
            window.location.href = '../page/login.html';
        },
    });
}

function connectMachine() {
    var rank = event.target.getAttribute('data-row');
    var da = {
        "id": machines[rank].machineId
    }
    $.ajax({
        "async": true,
        "url": "http://localhost:8080/machine/connect",
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
                alert(result.message + data.ip + data.hostname + data.password + data.port);
                /*TODO
                ** 根据获取到的machine info，建立ssh连接
                ** 目前计划实现命令行交互及文件界面交互两种连接方式。
                 */
            }else {
                alert(result.message);
                userLogout();
                window.location.href = "../page/login.html";
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // alert(jqXHR);
            //  //console.log(jqXHR);
            alert("发生异常！请重新登陆！");
            userLogout();
            window.location.href = '../page/login.html';
        },
    });
}

function addMachine() {
    document.getElementById("add").style.display = "block";
    document.getElementById("bck").style.display = "block";
    document.getElementById("machines").style.display = "none";
    document.getElementById("table").style.display = "none";
}

function back() {
    window.location.href = '../page/machineManage.html';
}

function commitMachineInfo() {
    var ip = document.getElementById('ip');
    var hostname = document.getElementById('hostname');
    var port = document.getElementById('port');
    var password = document.getElementById('password');
    if (!ip.value) {
        alert("请先输入ip");
        host.focus();
        return;
    }
    if(!hostname.value) {
        alert("请输入hostname");
        user.focus();
        return;
    }
    if(port.value) {
        if(isNaN(Number(port.value))) {
            alert("port的值应为一个整数！");
            port.focus();
            return;
        }
    }
    if(!password.value) {
        alert("请输入密码");
        password.focus();
        return;
    }

    var ipText = $("#ip").val();
    var hostnameText = $("#hostname").val();
    var portText = 22;
    if(port.value) {
        portText = $("#port").val();
    }
    var passwordTest = $("#password").val();
    var contentText = $("#content").val();

    var da = {
        "username":getCookie('username'),
        "ip":ipText,
        "hostname":hostnameText,
        "port":portText,
        "password":passwordTest,
        "content":contentText
    };
    $.ajax({
        "async": true,
        "url": "http://localhost:8080/machine/addMachine",
        "type": "POST",
        "data": JSON.stringify(da),
        "dataType": "json",
        "contentType": "application/json",
        "headers": {
            'Authorization': `Bearer ${token}`
        },
        success: function (result) {
            if(result.code == 200) {
                alert(result.message);
                window.location.href = "../page/machineManage.html";
            }else {
                alert(result.message);
                window.location.href = "../page/machineManage.html";
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

function upPage() {
    if(pages > 1) {
        pages--;
        listMachines();
    }
}

function downPage() {
    if(pages < getCookie('pages')) {
        pages++;
        listMachines();
    }
}

function listMachines() {
    var da = {
        "username": getCookie('username'),
        "pageNum": pages,
        "pageSize": 10
    }
    $.ajax({
        "async": true,
        "url": "http://localhost:8080/machine/listMachine",
        "type": "POST",
        "data": JSON.stringify(da),
        "dataType": "json",
        "contentType": "application/json",
        "headers": {
            'Authorization': `Bearer ${token}`
        },
        success: function (result) {
            if(result.code == 200) {
                $('.table_node').empty();
                machines = result.data;
                $.each(machines, function(index, n) {
                    var child = `<tr><td>${machines[index].id}</td><td>${machines[index].ip}</td><td>${machines[index].hostname}</td><td>${machines[index].port}</td><td>${machines[index].content}</td><td> <button data-row="${index}"type="button" class="td-btn" onclick="connectMachine()">connect</button>
<button data-row="${index}"type="button" class="td-btn" onclick="deleteMachine(event)">delete</button></td>`;

                    $(".table_node").append(child)
                });
                document.getElementById("page").innerText = pages;
            }else {
                alert(result.message);
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