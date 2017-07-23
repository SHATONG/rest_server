var ajaxUtil = ajaxUtil || {};
ajaxUtil.ajaxUtil = ajaxUtil.ajaxUtil || function () {

    return {
        ajaxPost: function (url, param, async, successCallBack, ajaxFailCallBack) {
            $.ajax({
                type: "post",
                url: url,
                data: param,
                datatype: "json",
                async: async,
                success: function (data) {
                        successCallBack(data);
                },
                fail: function (failResult) {
                    var reObj = $.parseJSON(failResult.responseText);
                    if (reObj.excepitonFlag == "1") {
                        window.location.href = reObj.url;
                    } else {
                        ajaxFailCallBack;
                    }
                },
            });
        },
        ajaxGet: function (url, successCallBack, ajaxFailCallBack) {
            $.ajax({
                type: "get",
                url: url,
                success: function (data) {
                    if (data['isAuth'])
                        successCallBack(data);
                    else
                        window.location.href = '/logins';
                },
                fail: function (failResult) {
                    var reObj = $.parseJSON(failResult.responseText);
                    if (reObj.excepitonFlag == "1") {
                        window.location.href = reObj.url;
                    } else {
                        ajaxFailCallBack;
                    }
                },
            });
        }
    }
}();



