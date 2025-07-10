var token = getCookie('jwt');
alert(token);

function userLogout() {
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
            window.location.href = '../page/login.html';
        },
        error: function(xhr, status, error) {

        }
    })
}