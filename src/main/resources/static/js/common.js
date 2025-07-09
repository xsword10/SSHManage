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