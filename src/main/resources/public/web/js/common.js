/**
 * Created by liuqi on 2017/06/27.
 */
var version = 1.0;

function logout() {
    delAllCookie();
    window.location.href = "buildings.html"
}


function delAllCookie() {
    var myDate = new Date();
    myDate.setTime(-1000);
    var data = document.cookie;
    var dataArray = data.split("; ");
    for (var i = 0; i < dataArray.length; i++) {
        var varName = dataArray[i].split("=");
        document.cookie = varName[0] + "=''; expires=" + myDate.toGMTString();
    }

}
