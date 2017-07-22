////////////////////////////////////////////////////////////////////////
// ログイン処理
////////////////////////////////////////////////////////////////////////
$(document).ready(function(){
    $("#button_login").mousedown(function(){
        login();
    });
});
function login(){
    var postData = {
        "username": $("#inputUsername").val(),
        "password": $("#inputPassword").val()
    };
    var outData=JSON.stringify(postData);
    $.ajax({
        type: "post",
        url : "/smartglass/managerlogin",
        dataType:'json',
        headers : {
            'version':"1.0",
            'Content-Type':'application/json;charset=UTF-8'
        },
        data: outData,
        success: function(json){
            if (0 == json.statusCode) {
                setCookie("name", json.content.name);
                setCookie("nickname", json.content.nickname);
                setCookie("token", json.content.token);
                window.location.href ="/web/devices.html";
            } else {
                $("#alert_message").text("ユーザー名またはパスワードが正しくありません。");
            }
        },
        error: function (json) {
            alert("error");
            window.location.href ="/web/buildings.html";
        }
    });
    function setCookie(name, data)
    {
        var expires=new Date();
        expires.setTime(expires.getTime()+60*60*1000);
        document.cookie = name + " = " + data + "; expires = " + expires.toGMTString() + "; path=/";
    }
}
